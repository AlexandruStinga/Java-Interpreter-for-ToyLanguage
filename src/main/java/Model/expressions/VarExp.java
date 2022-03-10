package Model.expressions;

import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.MyException;
import Model.types.IType;
import Model.values.IValue;

public class VarExp extends Exp{

    String id;

    public VarExp(String i)
    {
        id = i;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws Exception {
        return symTable.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

    public Exp deepCopy()
    {
        return new VarExp(id);
    }
}
