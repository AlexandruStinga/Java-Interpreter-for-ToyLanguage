package Model;

import Model.adt.*;
import Model.exceptions.MyException;
import Model.stmt.IStmt;
import Model.values.IValue;
import Model.values.StringValue;
import java.io.BufferedReader;
import java.util.List;

public class PrgState {
    IStack<IStmt> exeStack;
    IDict<String, IValue> symTable;
    IList<IValue> out;
    IDict<StringValue, BufferedReader> fileTable;
    IHeap<IValue> heap;
    ISemaphoreTable<Integer, Pair<Integer, List<Integer>>> semaphoreTable;
    IStmt originalProgram; //optional field, but good to have
    public static int lastID = 0;
    public int stateID;


    public PrgState(IStack<IStmt> stk, IDict<String, IValue> symtbl, IList<IValue>
            ot, IStmt prg,IDict<StringValue, BufferedReader> fTble, IHeap<IValue> hp, ISemaphoreTable<Integer, Pair<Integer, List<Integer>>> semaphoreT){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        fileTable = fTble;
        heap = hp;
//        originalProgram=deepCopy(prg);//recreate the entire original prg
        semaphoreTable = semaphoreT;
        stateID = getNewPrgStateID();
        stk.push(prg);
    }

    public IStack<IStmt> getStk() {
        return exeStack;
    }

    public IDict<String, IValue> getSymTable() {
        return symTable;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public IDict<StringValue, BufferedReader> getFileTable(){
        return fileTable;
    }

    public void addOut(IValue eval) {
        out.add(eval);
    }

    public IHeap<IValue> getHeap()
    {
        return heap;
    }

    public String toString()
    {
        return "ID:" + stateID + "\n" + "Execution stack: " + exeStack.toString() + "\n" + "Symbol Table" + symTable.toString() + '\n' + "Output: " + out.toString() + "\n" + "File Table: " + fileTable.toString() + "\n" + "Heap: " + heap.toString() + "\nSemaphore table: " + semaphoreTable;
    }

    public Boolean isNotCompleted()
    {
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStep() throws Exception {
        if(exeStack.isEmpty()) {
            throw new MyException("prgstate stack is empty");
        }
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public synchronized int getNewPrgStateID()
    {
        ++lastID;
        return lastID;
    }

    public int getStateID()
    {
        return stateID;
    }

    public ISemaphoreTable<Integer, Pair<Integer, List<Integer>>> getSemaphoreTable() {
        return semaphoreTable;
    }
}
