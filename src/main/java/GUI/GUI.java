package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("Enter file path: ");
//        String filePath = keyboard.next();
//
//        IRepo repo1 = new Repository(filePath);
//        Controller c1 = new Controller(repo1);
//
//        IRepo repo2 = new Repository(filePath);
//        Controller c2 = new Controller(repo2);
//
//        IRepo repo3 = new Repository(filePath);
//        Controller c3 = new Controller(repo3);
//
//        IRepo repo4 = new Repository(filePath);
//        Controller c4 = new Controller(repo4);
//
//        IRepo repo5 = new Repository(filePath);
//        Controller c5 = new Controller(repo5);
//
//        IRepo repo6 = new Repository(filePath);
//        Controller c6 = new Controller(repo6);
//
//        IRepo repo7 = new Repository(filePath);
//        Controller c7 = new Controller(repo7);
//
//        IRepo repo8 = new Repository(filePath);
//        Controller c8 = new Controller(repo8);
//
//        IRepo repo9 = new Repository(filePath);
//        Controller c9 = new Controller(repo9);
//
//        IRepo repo10 = new Repository(filePath);
//        Controller c10 = new Controller(repo10);
//
//        IRepo repo11 = new Repository(filePath);
//        Controller c11 = new Controller(repo11);
//
//        IRepo repo12 = new Repository(filePath);
//        Controller c12 = new Controller(repo12);
//
//
//
//
//        ///example 1
//        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
//                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
//                        VarExp("v"))));
//
//        IStack<IStmt> executionStack1 = new MyStack<>();
//        IDict<String, IValue> symTable1 = new MyDict<>();
//        IList<IValue> outputList1 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable1 = new MyDict<>();
//        IHeap<IValue> heap1 = new MyHeap<>();
//        IDict<String, IType> typeEnv1 = new MyDict<>();
//
//        ex1.typecheck(typeEnv1);
//        PrgState crtPrgState1 = new PrgState(executionStack1, symTable1, outputList1, ex1, fileTable1,heap1);
//        c1.addState(crtPrgState1);
//
//
//        ///example 2
//        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
//                new CompStmt(new VarDeclStmt("b", new IntType()),
//                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
//                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
//                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
//                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
//
//        IStack<IStmt> executionStack2 = new MyStack<>();
//        IDict<String, IValue> symTable2 = new MyDict<>();
//        IList<IValue> outputList2 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable2 = new MyDict<>();
//        IHeap<IValue> heap2 = new MyHeap<>();
//        IDict<String, IType> typeEnv2 = new MyDict<>();
//
//        ex2.typecheck(typeEnv2);
//        PrgState crtPrgState2 = new PrgState(executionStack2, symTable2, outputList2, ex2, fileTable2,heap2);
//        c2.addState(crtPrgState2);
//
//
//        ///example 3
//        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
//                new CompStmt(new VarDeclStmt("v", new IntType()),
//                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
//                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
//                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
//                                        VarExp("v"))))));
//
//        IStack<IStmt> executionStack3 = new MyStack<>();
//        IDict<String, IValue> symTable3 = new MyDict<>();
//        IList<IValue> outputList3 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable3 = new MyDict<>();
//        IHeap<IValue> heap3 = new MyHeap<>();
//        IDict<String, IType> typeEnv3 = new MyDict<>();
//
//        ex3.typecheck(typeEnv3);
//        PrgState crtPrgState3 = new PrgState(executionStack3, symTable3, outputList3, ex3, fileTable3,heap3);
//        c3.addState(crtPrgState3);
//
//
//        ///example4
//        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
//                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
//                        new CompStmt(new openRFile(new ValueExp(new StringValue("varf"))),
//                                new CompStmt(new VarDeclStmt("varc", new IntType()),
//                                        new CompStmt(
//                                                new CompStmt(new readFile(new ValueExp(new StringValue("varf")), "varc"), new PrintStmt(new VarExp("varc"))),
//                                                new CompStmt(
//                                                        new CompStmt(new readFile(new ValueExp(new StringValue("varf")), "varc"), new PrintStmt(new VarExp("varc"))),
//                                                        new closeRFile(new ValueExp(new StringValue("varf")))))))));
//
//        IStack<IStmt> executionStack4 = new MyStack<>();
//        IDict<String, IValue> symTable4 = new MyDict<>();
//        IList<IValue> outputList4 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable4 = new MyDict<>();
//        IHeap<IValue> heap4 = new MyHeap<>();
//        IDict<String, IType> typeEnv4 = new MyDict<>();
//
//        ex4.typecheck(typeEnv4);
//        PrgState crtPrgState4 = new PrgState(executionStack4, symTable4, outputList4, ex4, fileTable4,heap4);
//        c4.addState(crtPrgState4);
//
//
//        ///example5
//
//        IStmt ex5 = new CompStmt(new VarDeclStmt("a",new IntType()),
//                new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(10))),
//                        new CompStmt(new VarDeclStmt("b",new IntType()),
//                                new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(15))),
//                                        new CompStmt(new IfStmt(new RelExp(new VarExp("a"),new VarExp("b"),"<"),new PrintStmt(new VarExp("a")),new NopStmt()),new NopStmt())))));
//
//        IStack<IStmt> executionStack5 = new MyStack<>();
//        IDict<String, IValue> symTable5 = new MyDict<>();
//        IList<IValue> outputList5 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable5 = new MyDict<>();
//        IHeap<IValue> heap5 = new MyHeap<>();
//        IDict<String, IType> typeEnv5 = new MyDict<>();
//
//        ex5.typecheck(typeEnv5);
//        PrgState crtPrgState5 = new PrgState(executionStack5, symTable5, outputList5, ex5, fileTable5,heap5);
//        c5.addState(crtPrgState5);
//        ///example6
//        IStmt ex6 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
//                new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(20))),
//                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
//                                new CompStmt(new HeapAllocation("a",new VarExp("v")),new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("v")))))));
//
//        IStack<IStmt> executionStack6 = new MyStack<>();
//        IDict<String, IValue> symTable6 = new MyDict<>();
//        IList<IValue> outputList6 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable6 = new MyDict<>();
//        IHeap<IValue> heap6 = new MyHeap<>();
//        IDict<String, IType> typeEnv6 = new MyDict<>();
//
//        ex6.typecheck(typeEnv6);
//        PrgState crtPrgState6 = new PrgState(executionStack6, symTable6, outputList6, ex6, fileTable6,heap6);
//        c6.addState(crtPrgState6);
//
//        ///example7
//        IStmt ex7 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
//                new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(20))),
//                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
//                                new CompStmt(new HeapAllocation("a",new VarExp("v")),
//                                        new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))),new PrintStmt(new ArithExp('+',new HeapReading(new HeapReading(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));
//
//        IStack<IStmt> executionStack7 = new MyStack<>();
//        IDict<String, IValue> symTable7 = new MyDict<>();
//        IList<IValue> outputList7 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable7 = new MyDict<>();
//        IHeap<IValue> heap7 = new MyHeap<>();
//        IDict<String, IType> typeEnv7 = new MyDict<>();
//
//        ex7.typecheck(typeEnv7);
//        PrgState crtPrgState7 = new PrgState(executionStack7, symTable7, outputList7, ex7, fileTable7,heap7);
//        c7.addState(crtPrgState7);
//
//        ///example8
//
//        IStmt ex8 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
//                new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(20))),
//                        new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))),
//                                new CompStmt(new HeapWriting("v",new ValueExp(new IntValue(30))),
//                                        new PrintStmt(new ArithExp('+',new HeapReading(new VarExp("v")),new ValueExp(new IntValue(5))))))));
//
//        IStack<IStmt> executionStack8 = new MyStack<>();
//        IDict<String, IValue> symTable8 = new MyDict<>();
//        IList<IValue> outputList8 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable8 = new MyDict<>();
//        IHeap<IValue> heap8 = new MyHeap<>();
//        IDict<String, IType> typeEnv8 = new MyDict<>();
//
//        ex8.typecheck(typeEnv8);
//        PrgState crtPrgState8 = new PrgState(executionStack8, symTable8, outputList8, ex8, fileTable8,heap8);
//        c8.addState(crtPrgState8);
//
//
//
//        ///example9
//        IStmt ex9 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
//                new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(20))),
//                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
//                                new CompStmt(new HeapAllocation("a",new VarExp("v")),
//                                        new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(30))),new PrintStmt(new HeapReading(new HeapReading(new VarExp("a")))))))));
//
//        IStack<IStmt> executionStack9 = new MyStack<>();
//        IDict<String, IValue> symTable9 = new MyDict<>();
//        IList<IValue> outputList9 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable9 = new MyDict<>();
//        IHeap<IValue> heap9 = new MyHeap<>();
//        IDict<String, IType> typeEnv9 = new MyDict<>();
//
//        ex9.typecheck(typeEnv9);
//        PrgState crtPrgState9 = new PrgState(executionStack9, symTable9, outputList9, ex9, fileTable9,heap9);
//        c9.addState(crtPrgState9);
//
//        ///example10
////        new RelExp(new VarExp("v"),new ValueExp(new IntValue(0)),">")
////        new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))
////
//        IStmt ex10 = new CompStmt(new VarDeclStmt("v",new IntType()),
//                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
//                        new CompStmt(new WhileStmt(new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1))))),new RelExp(new VarExp("v"),new ValueExp(new IntValue(0)),">")),
//                                new PrintStmt(new VarExp("v")))));
//
//
//        IStack<IStmt> executionStack10 = new MyStack<>();
//        IDict<String, IValue> symTable10 = new MyDict<>();
//        IList<IValue> outputList10 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable10 = new MyDict<>();
//        IHeap<IValue> heap10 = new MyHeap<>();
//        IDict<String, IType> typeEnv10 = new MyDict<>();
//
//        ex10.typecheck(typeEnv10);
//        PrgState crtPrgState10 = new PrgState(executionStack10, symTable10, outputList10, ex10, fileTable10,heap10);
//        c10.addState(crtPrgState10);
//
//
//        ///example11
//
//        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
//                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
//                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
//                                new CompStmt(new HeapAllocation("a", new ValueExp(new IntValue(22))),
//                                        new CompStmt(new ForkStmt(new CompStmt(new HeapWriting("a", new ValueExp(new IntValue(30))),
//                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
//                                                        new CompStmt(new PrintStmt(new VarExp("v")),
//                                                                new PrintStmt(new HeapReading(new VarExp("a"))))))),
//                                                new CompStmt(new PrintStmt(new VarExp("v")),
//                                                        new PrintStmt(new HeapReading(new VarExp("a")))))))));
//
//
//        IStack<IStmt> executionStack11 = new MyStack<>();
//        IDict<String, IValue> symTable11 = new MyDict<>();
//        IList<IValue> outputList11 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable11 = new MyDict<>();
//        IHeap<IValue> heap11 = new MyHeap<>();
//        IDict<String, IType> typeEnv11 = new MyDict<>();
//
//        ex11.typecheck(typeEnv11);
//        PrgState crtPrgState11 = new PrgState(executionStack11, symTable11, outputList11, ex11, fileTable11,heap11);
//        c11.addState(crtPrgState11);
//
//        //example12
//
//        IStmt ex12 = new CompStmt(new VarDeclStmt("v", new IntType()),
//                new CompStmt(new AssignStmt("v", new ValueExp(new BoolValue(true))), new PrintStmt(new
//                        VarExp("v"))));
//
//
//        IStack<IStmt> executionStack12 = new MyStack<>();
//        IDict<String, IValue> symTable12 = new MyDict<>();
//        IList<IValue> outputList12 = new MyList<>();
//        IDict<StringValue, BufferedReader> fileTable12 = new MyDict<>();
//        IHeap<IValue> heap12 = new MyHeap<>();
//        IDict<String, IType> typeEnv12 = new MyDict<>();
//
//        try{
//            ex12.typecheck(typeEnv12);
//            PrgState crtPrgState12 = new PrgState(executionStack12, symTable12, outputList12, ex12, fileTable12,heap12);
//            c12.addState(crtPrgState12);
//        }
//        catch(MyException e)
//        {
//            System.out.println("Example 12: " + e.getMessage());
//        }
//

        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("GUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Interpretor!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);


//        TextMenu menu = new TextMenu();
//        menu.addCommand(new ExitCommand("0", "exit"));
//        menu.addCommand(new RunExample("1",ex1.toString(),c1));
//        menu.addCommand(new RunExample("2",ex2.toString(),c2));
//        menu.addCommand(new RunExample("3",ex3.toString(),c3));
//        menu.addCommand(new RunExample("4",ex4.toString(),c4));
//        menu.addCommand(new RunExample("5",ex5.toString(),c5));
//        menu.addCommand(new RunExample("6",ex6.toString(),c6));
//        menu.addCommand(new RunExample("7",ex7.toString(),c7));
//        menu.addCommand(new RunExample("8",ex8.toString(),c8));
//        menu.addCommand(new RunExample("9",ex9.toString(),c9));
//        menu.addCommand(new RunExample("10",ex10.toString(),c10));
//        menu.addCommand(new RunExample("11",ex11.toString(),c11));
//        menu.addCommand(new RunExample("12",ex12.toString(),c12));



//        menu.show();
    }
}