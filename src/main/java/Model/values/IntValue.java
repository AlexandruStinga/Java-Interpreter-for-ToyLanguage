package Model.values;

import Model.types.IType;
import Model.types.IntType;

public class IntValue implements IValue{

    int value;

    public IntValue(int v)
    {
        value = v;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(this.value);
    }

    public int getValue()
    {
        return this.value;
    }

    public String toString(){
        return Integer.toString(this.value);
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass())
            return false;
        IntValue o_value = (IntValue) o;
        return o_value.value == this.value;
    }
}
