package Model.expressions;

import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.MyException;
import Model.types.IType;
import Model.values.IValue;

import java.lang.reflect.Type;

public class ValueExp extends Exp{
    IValue e;

    public ValueExp(IValue value)
    {
        e = value;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws Exception {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        return e.getType();
    }


    public Exp deepCopy()
    {
        return new ValueExp(e);
    }
}
