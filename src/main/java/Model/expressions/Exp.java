package Model.expressions;

import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.MyException;
import Model.types.IType;
import Model.values.IValue;


public abstract class Exp {
    public abstract IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws Exception;
    public abstract String toString();
    public abstract IType typecheck(IDict<String, IType> typeEnv) throws MyException;
}
