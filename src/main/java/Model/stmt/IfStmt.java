package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.values.BoolValue;
import Model.values.IValue;

import java.util.Objects;

public class IfStmt implements IStmt{
    Exp expression;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el)
    {
        expression = e;
        thenS = t;
        elseS = el;
    }

    public String toString()
    {
        return "(IF("+ expression.toString()+") THEN(" +thenS.toString()
                +")ELSE("+elseS.toString()+"))";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IValue value;
        IStack<IStmt> stack = state.getStk();
        IDict<String,IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();
        value = expression.eval(symTable,heap);
        if(!Objects.equals(value.getType(), new BoolType()))
        {
            throw new MyException("conditional expr is not a boolean");
        }
        BoolValue BoolVal = (BoolValue) value;
        if(BoolVal.getValue())
        {
            stack.push(thenS);
        }
        else stack.push(elseS);

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typexp=expression.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.clone_dict());
            elseS.typecheck(typeEnv.clone_dict());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF is not a boolean");
    }
}
