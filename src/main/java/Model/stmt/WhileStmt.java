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

public class WhileStmt implements IStmt{
    IStmt stmt;
    Exp exp;

    public WhileStmt(IStmt s, Exp e)
    {
        stmt = s;
        exp = e;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stack = state.getStk();
        IDict<String,IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();
        IValue expValue = exp.eval(symTable,heap);

        if(expValue.getType() instanceof BoolType)
        {
            BoolValue expBoolVal = (BoolValue) expValue;
            if(expBoolVal.getValue())
            {
                stack.push(new WhileStmt(stmt,exp));
                stack.push(stmt);
            }
        }
        else throw new MyException("Not boolean type");

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type_expression = exp.typecheck(typeEnv);
        IDict<String, IType> copy = typeEnv.clone_dict();

        if(type_expression.equals(new BoolType())) {
            stmt.typecheck(copy);
            return typeEnv;
        }
        else {
            throw new MyException("The condition of WHILE is not a boolean");
        }
    }

    @Override
    public String toString() {
        return "While(" + exp +
                ")" + stmt;
    }
}
