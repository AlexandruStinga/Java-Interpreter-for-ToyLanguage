package Model.types;

import Model.values.IValue;
import Model.values.StringValue;

public class StringType implements IType{

    public boolean equals(Object o)
    {
        return o != null && o.getClass() == this.getClass();
    }


    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    public String toString()
    {
        return "string";
    }

    @Override
    public IType deepCopy() {
        return new StringType();
    }
}
