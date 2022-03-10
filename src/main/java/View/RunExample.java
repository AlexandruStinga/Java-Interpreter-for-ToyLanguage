package View;

import Controller.Controller;
import Model.exceptions.MyException;

import java.util.Arrays;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() throws Exception {
        try{
            ctr.allStep(); }
        catch (MyException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } //here you must treat the exceptions that can not be solved in the controller
    }
}