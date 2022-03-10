package Model.expressions;

import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.MyException;
import Model.types.BoolType;
import Model.types.IType;
import Model.types.IntType;
import Model.values.BoolValue;
import Model.values.IValue;

public class LogicExp extends Exp{
    Exp e1;
    Exp e2;
    char op;

    public LogicExp(Exp exp1, Exp exp2, char operator){
        op = operator;
        e2 = exp2;
        e1 = exp1;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable,IHeap<IValue> heap) throws Exception {
        IValue v1, v2;
        v1 = e1.eval(symTable,heap);
        if(v1.getType().equals(new BoolType()))
        {
            v2 = e2.eval(symTable,heap);
            if(v2.getType().equals(new BoolType()))
            {
                BoolValue b1 = (BoolValue)v1;
                BoolValue b2 = (BoolValue)v2;
                boolean n1,n2;
                n1=b1.getValue();
                n2=b2.getValue();
                if(op == '&')
                {
                    return new BoolValue(n1&&n2);
                }
                if(op == '|')
                {
                    return new BoolValue(n1||n2);
                }
            }
            else throw new MyException("op2 not boolean");
        }
        else throw new MyException("op1 not boolean");
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

        if (type1.equals(new BoolType()))
        {
            if(type2.equals(new BoolType()))
                return new BoolType();
            else throw new MyException("second operand is not a boolean");
        }
        else throw new MyException("first operand is not a boolean");
    }

    public Exp deepCopy()
    {
        return new LogicExp(e1,e2,op);
    }
}
