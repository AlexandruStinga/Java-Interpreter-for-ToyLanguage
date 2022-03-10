package Model.stmt;

import Model.PrgState;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.adt.IDict;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.types.IType;
import Model.values.IValue;

public class AssignStmt implements IStmt{
    String id;
    Exp expression;

    public AssignStmt(String i, Exp e)
    {
        id = i;
        expression = e;
    }

    public String toString(){ return id+"="+ expression.toString();}

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stack=state.getStk();
        IDict<String, IValue> symbolTable= state.getSymTable();
        IHeap<IValue> heap = state.getHeap();

        if(symbolTable.isDefined(id))
        {
            IValue val1 = expression.eval(symbolTable,heap);
            IType type = symbolTable.lookup(id).getType();
            if(val1.getType().equals(type))
            {
                symbolTable.update(id,val1);
            }
            else throw new MyException("Type of expression and type of variable do not match");
        }
        else throw new MyException("Variable Id is not declared");

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.lookup(id);
        IType typexp = expression.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }
}
