package Repository;

import Model.PrgState;
import Model.exceptions.MyException;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    PrgState getCrtPrg();
    void addState(PrgState prg);
//    void logPrgStateExec() throws MyException;
    void logPrgStateExec(PrgState prg) throws MyException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> list);
    public PrgState getPrgById(Integer id);
}
