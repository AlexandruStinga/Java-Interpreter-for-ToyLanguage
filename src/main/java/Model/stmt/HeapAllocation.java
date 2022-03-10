package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.types.IType;
import Model.types.RefType;
import Model.values.IValue;
import Model.values.RefValue;

import java.util.Objects;

public class HeapAllocation implements IStmt{

    String var_name;
    Exp exp;
    public HeapAllocation(String v, Exp e)
    {
        var_name = v;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String,IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();

        if(symTable.isDefined(var_name))
        {
            IValue v = symTable.lookup(var_name);
            if(v.getType() instanceof RefType)
            {
                IValue expVal = exp.eval(symTable,heap);
                IType type_var_name = ((RefValue)symTable.lookup(var_name)).getLocationType();
                if(expVal.getType().equals(type_var_name))
                {
                    Integer assoc_key;
                    assoc_key = heap.add(expVal);
                    RefValue new_ref_value = new RefValue(assoc_key,expVal.getType());
                    symTable.update(var_name, new_ref_value);
                }
                else throw new MyException("Not the same type");
            }
            else throw new MyException("Not a reference type");
        }
        else throw new MyException("Variable undefined(HeapAlloc)");

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.lookup(var_name);
        IType typeexp = exp.typecheck(typeEnv);
        if(typevar.equals(new RefType(typeexp)))
        {
            return typeEnv;
        }
        else throw new MyException("NEW Stmt: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return "new(" + var_name + ", " + exp + ')';
    }
}
