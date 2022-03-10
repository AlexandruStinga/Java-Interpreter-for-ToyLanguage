package Model.expressions;

import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.MyException;
import Model.types.BoolType;
import Model.types.IType;
import Model.types.IntType;
import Model.values.BoolValue;
import Model.values.IValue;
import Model.values.IntValue;

import java.util.Objects;

public class RelExp extends Exp{
    String op;
    Exp e1,e2;

    public RelExp(Exp exp1, Exp exp2, String operator)
    {
        e1 = exp1;
        e2 = exp2;
        op = operator;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws Exception {
        IValue v1, v2;
        v1 = e1.eval(symTable,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTable,heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();

                if(Objects.equals(op, "<"))
                {
                    return new BoolValue(n1<n2);
                }
                if(Objects.equals(op, "<="))
                {
                    return new BoolValue(n1 <= n2);
                }
                if(Objects.equals(op, "=="))
                {
                    return new BoolValue(n1 == n2);
                }
                if(Objects.equals(op, "!="))
                {
                    return new BoolValue(n1 != n2);
                }
                if(Objects.equals(op, ">"))
                {
                    return new BoolValue(n1 > n2);
                }
                if(Objects.equals(op, ">="))
                {
                    return new BoolValue(n1 >= n2);
                }

            }
            else throw new MyException("Second operand is not int");
        }
        else throw new MyException("First operand is not int");

        return null;
    }

    @Override
    public String toString() {
        return e1.toString() + op + e2.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type1,type2;
        type1 = e1.typecheck(typeEnv);
        type2 = e2.typecheck(typeEnv);

        if (type1.equals(new IntType()))
        {
            if(type2.equals(new IntType()))
                return new BoolType();
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
    }

    public Exp deepCopy()
    {
        return new RelExp(e1,e2,op);
    }
}
