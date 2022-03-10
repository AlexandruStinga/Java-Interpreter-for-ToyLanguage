package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.adt.MyStack;
import Model.exceptions.MyException;
import Model.types.IType;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;

    public CompStmt(IStmt f, IStmt s)
    {
        first = f;
        second = s;
    }

    public String toString()
    {
        return "("+first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStmt> stk = state.getStk();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
