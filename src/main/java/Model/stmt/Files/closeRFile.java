package Model.stmt.Files;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.stmt.IStmt;
import Model.types.IType;
import Model.types.StringType;
import Model.values.IValue;
import Model.values.StringValue;

import java.io.BufferedReader;
import java.util.Objects;

public class closeRFile implements IStmt {
    Exp expression;

    public closeRFile(Exp e)
    {
        expression = e;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stack = state.getStk();
        IDict<String, IValue> symbolTable= state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IHeap<IValue> heap = state.getHeap();

        IValue val = expression.eval(symbolTable,heap);
        if(!Objects.equals(val.getType(),new StringType()))
            throw new MyException("Not a string(closeRFile)");
        StringValue valString = (StringValue) val;

        if(!fileTable.isDefined(valString))
            throw new MyException("Buffer not defined (closeRFile)");

        BufferedReader buff = fileTable.lookup(valString);
        buff.close();

        fileTable.remove(valString);

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type_expression = expression.typecheck(typeEnv);

        if(type_expression.equals(new StringType())) {
            return typeEnv;
        }

        else {
            throw new MyException("close file statement: not a string type");
        }
    }

    @Override
    public String toString() {
        return "closeRFile(" + expression +
                ')';
    }
}
