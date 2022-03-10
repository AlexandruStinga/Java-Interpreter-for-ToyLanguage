package Model.expressions;

import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.MyException;
import Model.types.IType;
import Model.types.RefType;
import Model.values.IValue;
import Model.values.RefValue;

public class HeapReading extends Exp{
    Exp exp;

    public HeapReading(Exp e)
    {
        exp = e;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws Exception {
        IValue Val;

        Val = exp.eval(symTable, heap);
        if (Val.getType() instanceof RefType)
        {
            RefValue refValue;
            refValue = (RefValue) Val;
            int address = refValue.getAddress();
            return heap.getValue(address);
        }
        else throw new MyException("Not a ref value");

    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type = exp.typecheck(typeEnv);
        if(type instanceof RefType reft)
        {
            return reft.getInner();
        }
        else throw new MyException("the rH argument is not a Ref Type");
    }
}
