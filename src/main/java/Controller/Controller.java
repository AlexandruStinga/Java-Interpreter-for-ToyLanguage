package Controller;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.adt.MyDict;
import Model.exceptions.MyException;
import Model.stmt.IStmt;
import Model.types.IType;
import Model.values.IValue;
import Model.values.RefValue;
import Repository.IRepo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    public IRepo r;
    public ExecutorService executor;

    public Controller(IRepo repo) {
        r = repo;
    }

//    PrgState oneStep(PrgState state) throws Exception {
//        IStack<IStmt> stk = state.getStk();
//        if(stk.isEmpty())
//            throw new MyException("program state stack is empty");
//        IStmt crtStmt = stk.pop();
//        return crtStmt.execute(state);
//    }


//    public void allStep() throws Exception {
//        PrgState prg = r.getCrtPrg();
//        System.out.println(prg+"\n");
//        r.logPrgStateExec(prg);
//        while(!prg.getStk().isEmpty())
//        {
//            prg.oneStep();
//            System.out.println(prg+"\n");
//            prg.getHeap().setContent(garbage_collector(get_used_addresses(prg.getSymTable().getContent().values(),prg.getHeap().getContent().values()),prg.getHeap().getContent()));
//            r.logPrgStateExec(prg);
//        }
//    }


    public void oneStepForAllPrg(List<PrgState> prgList) throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        prgList.forEach(prg -> r.logPrgStateExec(prg));
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());


        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> { try { return future.get();}
                catch(Exception e) {
                    System.out.println(e.getMessage());
                    throw new MyException(e.getMessage());
                }
//                return null;
                }).filter(p -> p!=null).collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg ->r.logPrgStateExec(prg));
        prgList.forEach(prg->System.out.println(prg+"\n"));
        r.setPrgList(prgList);
    }

    public void executeOneStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> programStates = removeCompletedPrg(r.getPrgList());
//        List<PrgState> programStates = r.getPrgList();
        if(programStates.size() > 0)
        {
            r.getPrgList().forEach(prg -> prg.getHeap().setContent(garbage_collector(get_used_addresses(prg.getSymTable().getContent().values(),prg.getHeap().getContent().values()),prg.getHeap().getContent())));
            oneStepForAllPrg(programStates);
            programStates.forEach(e -> {
                r.logPrgStateExec(e);
            });
            executor.shutdownNow();
//            programStates = removeCompletedPrg(r.getPrgList());
        }
//        programStates = removeCompletedPrg(r.getPrgList());
        r.setPrgList(programStates);
    }

    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrg(r.getPrgList());
        while(prgList.size() > 0){
//            //HERE you can call conservativeGarbageCollector

            r.getPrgList().stream().forEach(prg -> prg.getHeap().setContent(garbage_collector(get_used_addresses(prg.getSymTable().getContent().values(),prg.getHeap().getContent().values()),prg.getHeap().getContent())));

            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList=removeCompletedPrg(r.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        r.setPrgList(prgList);
    }

    public void addState(PrgState state) {
        r.addState(state);
    }

    private List<Integer> get_used_addresses(Collection<IValue> symbols_table_values, Collection<IValue> heap_table_values) {
        List<Integer> symbols_table_addresses = symbols_table_values.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {
                    RefValue value2 = (RefValue) value;
                    return value2.getAddress();
                })
                .collect(Collectors.toList());

        List<Integer> heap_table_addresses = heap_table_values.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {
                    RefValue value2 = (RefValue) value;
                    return value2.getAddress();
                })
                .collect(Collectors.toList());

        symbols_table_addresses.addAll(heap_table_addresses);
        return symbols_table_addresses;
    }

    private Map<Integer, IValue> garbage_collector(List<Integer> used_addresses, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> used_addresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }
}
