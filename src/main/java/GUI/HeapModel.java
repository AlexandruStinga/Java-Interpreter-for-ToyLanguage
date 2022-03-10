package GUI;

import Model.values.IValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class HeapModel {
    public SimpleIntegerProperty address;
    public SimpleObjectProperty<IValue> value;

    public HeapModel(Integer add, IValue val)
    {
        address = new SimpleIntegerProperty(add);
        value = new SimpleObjectProperty<IValue>(val);
    }

    public Integer getAddress()
    {
        return address.get();
    }

    public IValue getValue()
    {
        return value.get();
    }

    public void setAddress(Integer address) {
        this.address = new SimpleIntegerProperty(address);
    }

    public void setValue(IValue value) {
        this.value = new SimpleObjectProperty<IValue>(value);
    }
}
