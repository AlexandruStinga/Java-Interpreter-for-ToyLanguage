package Model.values;

import Model.types.IType;
import Model.types.RefType;

public class RefValue implements IValue{
    int address;
    IType locationType;

    public RefValue(int i, IType inner) {
        address = i;
        locationType = inner;
    }

    public int getAddress()
    {
        return this.address;
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    public IType getLocationType()
    {
        return locationType;
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(address,locationType);
    }

    @Override
    public String toString() {
        return "(" + address + "," + locationType.toString() + ")";
    }
}
