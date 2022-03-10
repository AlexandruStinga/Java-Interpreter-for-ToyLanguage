package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.exceptions.MyException;
import Model.types.IType;

public interface IStmt {
    PrgState execute(PrgState state) throws Exception;
    IDict<String, IType> typecheck(IDict<String,IType> typeEnv) throws
            MyException;
}
