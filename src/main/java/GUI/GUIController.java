package GUI;

import Controller.Controller;
import Model.Pair;
import Model.PrgState;
import Model.adt.*;
import Model.expressions.*;
import Model.stmt.*;
import Model.stmt.Files.closeRFile;
import Model.stmt.Files.openRFile;
import Model.stmt.Files.readFile;
import Model.types.*;
import Model.values.BoolValue;
import Model.values.IValue;
import Model.values.IntValue;
import Model.values.StringValue;
import Repository.IRepo;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    @FXML
    private ListView<IStmt> listView;

    ObservableList<IStmt> prgStateObservableList;


    @FXML
    protected void onButtonClick() throws IOException {
        IStmt selected = listView.getSelectionModel().getSelectedItem();
        IRepo r = new Repository("log.txt");
        Controller c = new Controller(r);

        IStack<IStmt> executionStack1 = new MyStack<>();
        IDict<String, IValue> symTable1 = new MyDict<>();
        IList<IValue> outputList1 = new MyList<>();
        IDict<StringValue, BufferedReader> fileTable1 = new MyDict<>();
        IHeap<IValue> heap1 = new MyHeap<>();
        IDict<String, IType> typeEnv1 = new MyDict<>();
        ISemaphoreTable<Integer, Pair<Integer, List<Integer>>> semaphoreTable = new SemaphoreTable<>();

        try {
            selected.typecheck(typeEnv1);
            PrgState crtPrgState1 = new PrgState(executionStack1, symTable1, outputList1, selected, fileTable1, heap1, semaphoreTable);
            c.addState(crtPrgState1);

            FXMLLoader fxmlLoader = new FXMLLoader(ProgramController.class.getResource("ProgramGUI.fxml"));
            ProgramController programController = new ProgramController(c);
            fxmlLoader.setController(programController);

            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage newWindow = new Stage();
            newWindow.setTitle("Program GUI");
            newWindow.setScene(scene);

            newWindow.show();

            Scene currentScene = listView.getScene();
            Stage stg = (Stage) currentScene.getWindow();
            stg.close();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setContentText(e.getMessage());
            a.show();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prgStateObservableList = FXCollections.observableArrayList();

        ///example 1
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new BoolType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));


        ///example 2
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('/', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(0))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));

//        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
//                new CompStmt(new VarDeclStmt("b", new IntType()),
//                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
//                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
//                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
//                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));


        ///example 3
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));


        ///example4
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new ValueExp(new StringValue("varf"))),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(
                                                new CompStmt(new readFile(new ValueExp(new StringValue("varf")), "varc"), new PrintStmt(new VarExp("varc"))),
                                                new CompStmt(
                                                        new CompStmt(new readFile(new ValueExp(new StringValue("varf")), "varc"), new PrintStmt(new VarExp("varc"))),
                                                        new closeRFile(new ValueExp(new StringValue("varf")))))))));


        ///example5

        IStmt ex5 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(10))),
                        new CompStmt(new VarDeclStmt("b", new IntType()),
                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(15))),
                                        new CompStmt(new IfStmt(new RelExp(new VarExp("a"), new VarExp("b"), "<"), new PrintStmt(new VarExp("a")), new NopStmt()), new NopStmt())))));

        ///example6
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocation("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("v")))))));


        ///example7
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocation("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))), new PrintStmt(new ArithExp('+', new HeapReading(new HeapReading(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));

        ///example8

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))),
                                new CompStmt(new HeapWriting("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new HeapReading(new VarExp("v")), new ValueExp(new IntValue(5))))))));


        ///example9
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocation("a", new VarExp("v")),
                                        new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(30))), new PrintStmt(new HeapReading(new HeapReading(new VarExp("a")))))))));

        ///example10
//        new RelExp(new VarExp("v"),new ValueExp(new IntValue(0)),">")
//        new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))
//
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))), new RelExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">")),
                                new PrintStmt(new VarExp("v")))));


        ///example11

        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocation("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new HeapWriting("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new HeapReading(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new HeapReading(new VarExp("a")))))))));

        //example12

        IStmt ex12 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new BoolValue(true))), new PrintStmt(new
                        VarExp("v"))));

        IStmt ex14 =
                new CompStmt(new VarDeclStmt("cnt", new IntType()),
                        new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                                new CompStmt(
                                        new HeapAllocation("v1", new ValueExp(new IntValue(1))), new CompStmt(
                                        new createSemaphore("cnt", new HeapReading(new VarExp("v1"))), new CompStmt(
                                        new ForkStmt(
                                                new CompStmt(
                                                        new AcquireStmt("cnt"), new CompStmt(
                                                        new HeapWriting("v1", new ArithExp('*', new HeapReading(new VarExp("v1")), new ValueExp(new IntValue(10)))), new CompStmt(
                                                        new PrintStmt(new HeapReading(new VarExp("v1"))), new ReleaseStmt("cnt")
                                                )
                                                )
                                                )
                                        ), new CompStmt(
                                        new ForkStmt(
                                                new CompStmt(
                                                        new AcquireStmt("cnt"), new CompStmt(
                                                        new HeapWriting("v1", new ArithExp('*', new HeapReading(new VarExp("v1")), new ValueExp(new IntValue(10)))), new CompStmt(
                                                        new HeapWriting("v1", new ArithExp('*', new HeapReading(new VarExp("v1")), new ValueExp(new IntValue(2)))), new CompStmt(
                                                        new PrintStmt(new HeapReading(new VarExp("v1"))), new ReleaseStmt("cnt")
                                                )
                                                )
                                                )
                                                )
                                        ), new CompStmt(
                                        new AcquireStmt("cnt"), new CompStmt(
                                        new PrintStmt(new ArithExp('-', new HeapReading(new VarExp("v1")), new ValueExp(new IntValue(1)))), new ReleaseStmt("cnt")
                                )
                                )
                                )
                                )
                                )
                                )));

        prgStateObservableList.add(ex1);
        prgStateObservableList.add(ex2);
        prgStateObservableList.add(ex3);
        prgStateObservableList.add(ex4);
        prgStateObservableList.add(ex5);
        prgStateObservableList.add(ex6);
        prgStateObservableList.add(ex7);
        prgStateObservableList.add(ex8);
        prgStateObservableList.add(ex9);
        prgStateObservableList.add(ex10);
        prgStateObservableList.add(ex11);
        prgStateObservableList.add(ex14);

        listView.setItems(prgStateObservableList);

    }
}