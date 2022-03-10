package Model.stmt.Files;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.stmt.IStmt;
import Model.types.IType;
import Model.types.IntType;
import Model.types.StringType;
import Model.values.IValue;
import Model.values.IntValue;
import Model.values.StringValue;

import java.io.BufferedReader;
import java.util.Objects;

public class readFile implements IStmt {
    Exp expression;
    String var_name;

    public readFile(Exp e, String v)
    {
        expression = e;
        var_name = v;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stack = state.getStk();
        IDict<String, IValue> symbolTable= state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IHeap<IValue> heap = state.getHeap();

        if(symbolTable.isDefined(var_name))
        {
            IValue val = symbolTable.lookup(var_name);
            if(Objects.equals(val.getType(), new IntType()))
            {
                IValue valExp = expression.eval(symbolTable,heap);
                if(Objects.equals(valExp.getType(),new StringType()))
                {
                    StringValue valExpString = (StringValue) valExp;
                    if(!fileTable.isDefined(valExpString))
                        throw new MyException("Buffer doesnt exists (readFile)");
                    BufferedReader buf = fileTable.lookup(valExpString);
                    String line = buf.readLine();
                    if(line == null)
                    {
                        IValue zero = new IntValue(0);
                        symbolTable.update(var_name,zero);
                    }
                    else
                    {
                        IValue new_value = new IntValue(Integer.parseInt(line));
                        symbolTable.update(var_name,new_value);
                    }


                }
                else throw new MyException("ValueExp is not a string(readFile)");
            }
            else throw new MyException("Variable is not an int (readFile)");
        }
        else throw new MyException("Variable not declared (readFile)");

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type_expression = expression.typecheck(typeEnv);

        if(type_expression.equals(new StringType())) {
            return typeEnv;
        }

        else {
            throw new MyException("read file statement: not a string type");
        }
    }

    @Override
    public String toString() {
        return "readFile("+this.expression+","+this.var_name+")";
    }
}
