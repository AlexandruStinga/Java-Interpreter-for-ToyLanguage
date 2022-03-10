package Model.values;

import Model.types.BoolType;
import Model.types.IType;

public class BoolValue implements IValue{
    boolean value;

    public BoolValue(boolean v)
    {
        value = v;
    }
    @Override
    public IType getType() {
        return new BoolType();
    }

    public boolean getValue()
    {
        return value;
    }

    @Override
    public IValue deepCopy() {
        return new BoolValue(this.value);
    }

    public String toString()
    {
        return this.value ? "true" : "false";
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass())
            return false;
        BoolValue o_value = (BoolValue) o;
        return o_value.value == this.value;
    }
}
