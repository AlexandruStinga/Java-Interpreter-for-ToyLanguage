package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.adt.MyStack;
import Model.exceptions.MyException;
import Model.types.IType;
import Model.values.IValue;

public class ForkStmt implements IStmt{

    IStmt stmt;

    public ForkStmt(IStmt s)
    {
        stmt = s;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stack = new MyStack<>();
        return new PrgState(stack,state.getSymTable().clone_dict(),state.getOut(),this.stmt,state.getFileTable(),state.getHeap(),state.getSemaphoreTable());
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IDict<String,IType> clone = typeEnv.clone_dict();
        stmt.typecheck(clone);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Fork(" + stmt +
                ')';
    }
}
