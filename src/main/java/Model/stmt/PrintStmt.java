package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IList;
import Model.adt.IStack;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.expressions.VarExp;
import Model.types.IType;
import Model.values.IValue;

public class PrintStmt implements IStmt{

    Exp expression;

    public PrintStmt(Exp ex) {
        expression = ex;
    }

    public String toString()
    {
        return "print(" + expression.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        state.addOut(expression.eval(state.getSymTable(),state.getHeap()));

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }
}
