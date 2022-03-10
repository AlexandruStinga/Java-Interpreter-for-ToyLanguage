package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exceptions.MyException;
import Model.expressions.Exp;
import Model.types.IType;
import Model.types.RefType;
import Model.values.IValue;
import Model.values.RefValue;

public class HeapWriting implements IStmt{
    String var_name;
    Exp exp;

    public HeapWriting(String var, Exp e)
    {
        var_name = var;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();

        if(symTable.isDefined(var_name))
        {
            IValue varValue = symTable.lookup(var_name);
            if(varValue.getType() instanceof RefType)
            {
                RefValue refVarValue = (RefValue) varValue;
                int address = refVarValue.getAddress();
                if(heap.isDefined(address))
                {
                    IValue expValue = exp.eval(symTable,heap);
                    if(expValue.getType().equals(refVarValue.getLocationType()))
                    {
                        heap.update(address,expValue);
                    }
                    else throw new MyException("Expression doesnt have the same type as the var");
                }
                else throw new MyException("Address not defined in heap");
            }
            else throw new MyException("Variable not a reference type");
        }
        else throw new MyException("Variable not defined");

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        IType type_var = typeEnv.lookup(var_name);
        IType type_expression = exp.typecheck(typeEnv);

        if(type_var.equals(new RefType(type_expression))) {
            return typeEnv;
        }
        else {
            throw new MyException("write heap statement: not reference type");
        }
    }

    @Override
    public String toString() {
        return "wH(" + var_name + ',' + exp +
                ')';
    }
}
