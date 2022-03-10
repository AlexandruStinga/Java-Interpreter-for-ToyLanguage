package Model.stmt.Files;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.stmt.IStmt;
import Model.types.IType;
import Model.values.IValue;
import Model.values.StringValue;
import Model.types.StringType;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class openRFile implements IStmt {

    Exp expression;

    public openRFile(Exp e)
    {
        expression = e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
       IStack<IStmt> stack = state.getStk();
       IDict<String, IValue> symbolTable= state.getSymTable();
       IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
       IHeap<IValue> heap = state.getHeap();

       IValue val1 = expression.eval(symbolTable,heap);

//        if(val1.getType().equals(new StringType()))
        if(Objects.equals(val1.getType(), new StringType()))
        {
            StringValue value = (StringValue) val1;
            StringValue fileName = (StringValue) symbolTable.lookup(val1.toString());
            if(!fileTable.isDefined(value))
            {
               try
               {
                   BufferedReader buff = new BufferedReader(new FileReader(fileName.getValue()));
                   fileTable.add(value,buff);
               }
               catch(IOException e) {
                   throw new MyException(e.toString());
               }

            }
            else throw new MyException("File already open");
        }
        else throw new MyException("Expression is not a string (openRFile)");

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type_expression = expression.typecheck(typeEnv);

        if(type_expression.equals(new StringType())) {
            return typeEnv;
        }

        else {
            throw new MyException("open file statement: not a string type");
        }
    }

    @Override
    public String toString() {
        return "(open " + expression.toString() +")";
    }
}
