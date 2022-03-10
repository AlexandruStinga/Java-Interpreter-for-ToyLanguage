package Model.stmt;

import Model.Pair;
import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.adt.ISemaphoreTable;
import Model.adt.IStack;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.types.IType;
import Model.types.IntType;
import Model.values.IValue;
import Model.values.IntValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class createSemaphore implements IStmt{
    String var;
    Exp exp;
    ReentrantLock lock = new ReentrantLock();

    public createSemaphore(String v, Exp e)
    {
        this.var = v;
        this.exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        lock.lock();
        IStack<IStmt> stack = state.getStk();
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();
        ISemaphoreTable<Integer, Pair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable();

        IValue expVal = exp.eval(symTable,heap);
        if(expVal.getType().equals(new IntType()))
        {
            Integer number1 = ((IntValue) expVal).getValue();
            Integer freeLocation = semaphoreTable.getFreeLocation();
            semaphoreTable.add(semaphoreTable.getFreeLocation(),new Pair<>(number1,new ArrayList<>()));
            if(symTable.isDefined(var))
            {
                IValue varValue = symTable.lookup(var);
                if(varValue.getType().equals(new IntType()))
                {
                    symTable.update(var,new IntValue(freeLocation));
                }
                else throw new MyException("var not int (createSemaphore)");
            }
            else throw new MyException("var not in symtable (createSemaphore)");

        }
        else throw new MyException("Exp is not an int (createSemaphore)");

        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type_expression = exp.typecheck(typeEnv);
        IType type_var = typeEnv.lookup(var);

        if(type_var.equals(new IntType()))
        {
            if(type_expression.equals(new IntType()))
            {
                return typeEnv;
            }
            else throw new MyException("not int type");
        }
        else throw new MyException("Not int type");
    }

    @Override
    public String toString() {
        return "createSemaphore(" + var + "," + exp + ")";
    }
}
