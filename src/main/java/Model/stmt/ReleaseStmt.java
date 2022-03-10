package Model.stmt;

import Model.Pair;
import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.adt.ISemaphoreTable;
import Model.adt.IStack;
import Model.exceptions.MyException;
import Model.types.IType;
import Model.types.IntType;
import Model.values.IValue;
import Model.values.IntValue;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStmt implements IStmt {
    String var;
    ReentrantLock lock = new ReentrantLock();

    public ReleaseStmt(String v)
    {
        this.var = v;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        lock.lock();
        IStack<IStmt> stack = state.getStk();
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();
        ISemaphoreTable<Integer, Pair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable();

        if(symTable.isDefined(var))
        {
            IValue varValue = symTable.lookup(var);
            if (varValue.getType().equals(new IntType()))
            {
                Integer foundIndex = ((IntValue) varValue).getValue();
                if(semaphoreTable.contains(foundIndex))
                {
                    Pair<Integer,List<Integer>> pair = semaphoreTable.get(foundIndex);
                    if(pair.getSecond().contains(state.getStateID()))
                    {
                        pair.getSecond().remove((Object) state.getStateID());
                    }
                }
                else throw new MyException("semaphore table doesnt contain foundindex (release)");
            }
            else throw new MyException("var not an int (release)");
        }
        else throw new MyException("var not in symtable (release)");

        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type_var = typeEnv.lookup(var);
        if(type_var.equals(new IntType()))
        {
            return typeEnv;
        }
        else throw new MyException("var not an int (acquire)");
    }

    @Override
    public String toString() {
        return "release(" + var + ")";
    }
}
