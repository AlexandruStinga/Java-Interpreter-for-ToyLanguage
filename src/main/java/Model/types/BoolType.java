package Model.types;

import Model.values.BoolValue;
import Model.values.IValue;

public class BoolType implements IType{

    public boolean equals(Object o)
    {
        return o != null && o.getClass() == this.getClass();
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public IType deepCopy() {
        return new BoolType();
    }

    public String toString()
    {
        return "bool";
    }
}
