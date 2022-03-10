package Repository;

import Model.PrgState;
import Model.adt.IList;
import Model.exceptions.MyException;
import Model.stmt.IStmt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepo {

    public List<PrgState> prgStateList;
    String logFilePath;
    private boolean firstFileCall;

    public Repository(String filePath) {
        prgStateList = new ArrayList<PrgState>();
        logFilePath = filePath;
        firstFileCall = true;
    }

    public Repository() {
        prgStateList = new ArrayList<PrgState>();
        logFilePath = "";
        firstFileCall = true;
    }

    @Override
    public PrgState getCrtPrg() {
        return prgStateList.get(prgStateList.size() - 1);
    }

    @Override
    public void addState(PrgState prg) {
        prgStateList.add(prg);
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException {
        try
        {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(prg.toString() + "\n\n");
            logFile.close();
        }
        catch(IOException e)
        {
            e.getStackTrace();
        }
    }

//    @Override
//    public void logPrgStateExec() throws MyException {
//        try{
//            if (this.firstFileCall)
//            {
//                PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
//                logFile.write("");
//                logFile.close();
//                this.firstFileCall = false;
//            }
//            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
////            System.out.println(this.prgStateList.get(0).toString());
//            logFile.write(this.prgStateList.get(0).toString() + "\n\n");
//
//            logFile.close();
//
//        }
//        catch(IOException e){
//            e.getStackTrace();
//        }
//    }

    @Override
    public List<PrgState> getPrgList() {
        return prgStateList;
    }

    @Override
    public void setPrgList(List<PrgState> list) {
        prgStateList = list;
    }

    public PrgState getPrgById(Integer id)
    {
        for(PrgState prg: prgStateList)
        {
            if(prg.getStateID() == id)
                return prg;
        }
        return null;
    }
}
