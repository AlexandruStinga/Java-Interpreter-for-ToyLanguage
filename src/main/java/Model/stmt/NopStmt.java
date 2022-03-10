package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.MyException;
import Model.types.IType;

public class NopStmt implements IStmt {

    public PrgState execute(PrgState state){
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString(){
        return "no operation";
    }
}
