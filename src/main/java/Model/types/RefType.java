package Model.types;

import Model.values.IValue;
import Model.values.RefValue;

public class RefType implements IType {
    IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return inner;
    }

    public boolean equals(Object another) {
        return another != null && another.getClass() == this.getClass();
    }

    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    public IValue defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public IType deepCopy() {
        return new RefType(inner);
    }
}