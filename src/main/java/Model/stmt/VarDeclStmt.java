package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.MyException;
import Model.types.IType;
import Model.values.IValue;

public class VarDeclStmt implements IStmt{

    String name;
    IType type;

    public VarDeclStmt(String name, IType type)
    {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        IDict<String, IValue> symTable = state.getSymTable();
        if(symTable.isDefined(name))
            throw new MyException("variable is already declared");

        symTable.add(name,type.defaultValue());

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        typeEnv.add(name,type);
        return typeEnv;
    }

    public String toString()
    {
        return type.toString() + " " + name;
    }
}
