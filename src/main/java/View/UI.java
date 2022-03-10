//package View;
//
//import Controller.Controller;
//import Model.PrgState;
//import Model.adt.*;
//import Model.expressions.ArithExp;
//import Model.expressions.RelExp;
//import Model.expressions.ValueExp;
//import Model.expressions.VarExp;
//import Model.stmt.*;
//import Model.stmt.Files.closeRFile;
//import Model.stmt.Files.openRFile;
//import Model.stmt.Files.readFile;
//import Model.types.BoolType;
//import Model.types.IntType;
//import Model.types.StringType;
//import Model.values.BoolValue;
//import Model.values.IValue;
//import Model.values.IntValue;
//import Model.values.StringValue;
//
//import java.io.BufferedReader;
//import java.util.Scanner;
//
//public class UI {
//    Controller c;
//
//    public UI(Controller controller) {
//        c = controller;
//    }
//
//    public void start() throws Exception {
//        System.out.println("1. Run program 1");
//        System.out.println("2. Run program 2");
//        System.out.println("3. Run program 3");
//        System.out.println("4. Run program 4");
//        System.out.println("5. Run program 5");
//
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("Option: ");
//        int option = keyboard.nextInt();
//
//        switch (option) {
//            case 1 -> example1();
//            case 2 -> example2();
//            case 3 -> example3();
//            case 4 -> example4();
//            case 5 -> example5();
//        }
//
//    }
//
//    public void example1() throws Exception {
//        IStmt ex = new CompStmt(new VarDeclStmt("v", new IntType()),
//                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
//                        VarExp("v"))));
//
//        IStack<IStmt> executionStack = new MyStack<>();
//        IDict<String, IValue> symTable = new MyDict<>();
//        IList<IValue> outputList = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable = new MyDict<>();
//
//        PrgState crtPrgState = new PrgState(executionStack, symTable, outputList, ex, fileTable);
//        c.addState(crtPrgState);
//
//        c.allStep();
//
//    }
//
//    public void example2() throws Exception {
//        IStmt ex = new CompStmt(new VarDeclStmt("a", new IntType()),
//                new CompStmt(new VarDeclStmt("b", new IntType()),
//                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
//                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
//                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
//                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
//
//        IStack<IStmt> executionStack = new MyStack<>();
//        IDict<String, IValue> symTable = new MyDict<>();
//        IList<IValue> outputList = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable = new MyDict<>();
//
//        PrgState crtPrgState = new PrgState(executionStack, symTable, outputList, ex, fileTable);
//        c.addState(crtPrgState);
//
//        c.allStep();
//    }
//
//    public void example3() throws Exception {
//        IStmt ex = new CompStmt(new VarDeclStmt("a", new BoolType()),
//                new CompStmt(new VarDeclStmt("v", new IntType()),
//                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
//                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
//                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
//                                        VarExp("v"))))));
//
//        IStack<IStmt> executionStack = new MyStack<>();
//        IDict<String, IValue> symTable = new MyDict<>();
//        IList<IValue> outputList = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable = new MyDict<>();
//
//        PrgState crtPrgState = new PrgState(executionStack, symTable, outputList, ex, fileTable);
//        c.addState(crtPrgState);
//
//        c.allStep();
//    }
//
//    public void example4() throws Exception {
//        IStmt ex2 = new CompStmt(new VarDeclStmt("varf", new StringType()),
//                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
//                        new CompStmt(new openRFile(new ValueExp(new StringValue("varf"))),
//                                new CompStmt(new VarDeclStmt("varc", new IntType()),
//                                        new CompStmt(
//                                                new CompStmt(new readFile(new ValueExp(new StringValue("varf")), "varc"), new PrintStmt(new VarExp("varc"))),
//                                                new CompStmt(
//                                                        new CompStmt(new readFile(new ValueExp(new StringValue("varf")), "varc"), new PrintStmt(new VarExp("varc"))),
//                                                        new closeRFile(new ValueExp(new StringValue("varf")))))))));
//
//        IStack<IStmt> executionStack = new MyStack<>();
//        IDict<String, IValue> symTable = new MyDict<>();
//        IList<IValue> outputList = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable = new MyDict<>();
//
//        PrgState crtPrgState = new PrgState(executionStack, symTable, outputList, ex2, fileTable);
//        c.addState(crtPrgState);
//
//        c.allStep();
//    }
//
//    public void example5() throws Exception {
//        IStmt ex = new CompStmt(new VarDeclStmt("a",new IntType()),
//                new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(10))),
//                new CompStmt(new VarDeclStmt("b",new IntType()),
//                new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(15))),
//                new CompStmt(new IfStmt(new RelExp(new VarExp("a"),new VarExp("b"),"<"),new PrintStmt(new VarExp("a")),new NopStmt()),new NopStmt())))));
//
//        IStack<IStmt> executionStack = new MyStack<>();
//        IDict<String, IValue> symTable = new MyDict<>();
//        IList<IValue> outputList = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable = new MyDict<>();
//
//        PrgState crtPrgState = new PrgState(executionStack, symTable, outputList, ex, fileTable);
//        c.addState(crtPrgState);
//
//        c.allStep();
//
//    }
//}
