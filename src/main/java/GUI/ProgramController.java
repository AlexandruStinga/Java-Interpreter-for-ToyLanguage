package GUI;

import Controller.Controller;
import Model.Pair;
import Model.PrgState;
import Model.adt.*;
import Model.stmt.IStmt;
import Model.values.IValue;
import Model.values.IntValue;
import Model.values.StringValue;
import Repository.Repository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;

public class ProgramController implements Initializable {
    public Controller controller;
    private int stopFlag = 0;

    @FXML
    TextField textField1;

    @FXML
    TableView<Map.Entry<Integer,IValue>> heapTable;

    @FXML
    TableColumn<Map.Entry<Integer,IValue>,Integer> addressColumn;

    @FXML
    TableColumn<Map.Entry<Integer,IValue>,IValue> valueColumn;

    @FXML
    ListView<IValue> outListView;

    @FXML
    ListView<Integer> programStateIdList;

    @FXML
    ListView<Map.Entry<StringValue, BufferedReader>> fileTableView;

    @FXML
    TableView<Map.Entry<String,IValue>> symTableView;

    @FXML
    TableColumn<Map.Entry<String,IValue>,String> variableColumn;

    @FXML
    TableColumn<Map.Entry<String,IValue>,IValue> symValueColumn;

    @FXML
    ListView<String> executionStackListView;

    @FXML
    Button runOneStepButton;

    @FXML
    TableView<Map.Entry<Integer, Pair<Integer,List<Integer>>>> semaphoreTableView;

    @FXML
    TableColumn<Map.Entry<Integer, Pair<Integer,List<Integer>>>,Integer> indexSemaphore;

    @FXML
    TableColumn<Map.Entry<Integer, Pair<Integer,List<Integer>>>,Integer> valueSemaphore;

    @FXML
    TableColumn<Map.Entry<Integer, Pair<Integer,List<Integer>>>,List<Integer>> listSemaphore;

    public ProgramController(Controller cont)
    {
        controller = cont;
        System.out.println(cont.r.getCrtPrg());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

        variableColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        symValueColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

        indexSemaphore.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueSemaphore.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue().first));
        listSemaphore.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue().second));

        populateIdList();
        programStateIdList.getSelectionModel().select(0);
        PrgState currPrg = getPrgState();


        populateHeap(currPrg);
        populateOut(currPrg);
        populateFileTable(currPrg);
        populateSymTable(currPrg);
        populateExeStack(currPrg);
        populateSemaphore(currPrg);

        runOneStepButton.setOnAction(actionEvent -> {
            try {
                execute();
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("ERROR");
                a.setContentText(e.getMessage());
                a.show();
            }
        });

        programStateIdList.setOnMouseClicked(mouseEvent -> {changeProgramState(getPrgState());});

    }

    public void execute() throws Exception {
        controller.executeOneStep();
        PrgState currPrg = getPrgState();

        changeProgramState(currPrg);
        populateIdList();

        if(controller.r.getPrgList().size() == 1)
        {
            programStateIdList.getSelectionModel().select(0);
        }


        textField1.setText("Number of program states: " + controller.r.getPrgList().size());

        if(stopFlag == 1)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setContentText("Execution stack is empty");
            a.show();
        }

        if(programStateIdList.getItems().isEmpty()) {
            stopFlag = 1;
        }


    }

    private void changeProgramState(PrgState currPrg) {
        if(currPrg == null)
            return;
        populateExeStack(currPrg);
        populateSymTable(currPrg);
        populateOut(currPrg);
        populateHeap(currPrg);
        populateFileTable(currPrg);
        populateSemaphore(currPrg);
    }

    public void populateSemaphore(PrgState currPrg)
    {
        ISemaphoreTable<Integer,Pair<Integer,List<Integer>>> semaphoreTable = currPrg.getSemaphoreTable();
        List<Map.Entry<Integer, Pair<Integer, List<Integer>>>> semaphoreList = new ArrayList<>();

        for (Map.Entry<Integer, Pair<Integer, List<Integer>>> entry : semaphoreTable.getAll())
            semaphoreList.add(entry);
        semaphoreTableView.setItems(FXCollections.observableList(semaphoreList));
        semaphoreTableView.refresh();
    }

    public void populateHeap(PrgState currPrg)
    {
        IHeap<IValue> heap = new MyHeap<>();
        heap = currPrg.getHeap();

        List<Map.Entry<Integer,IValue>> heapTableList = new ArrayList<>();
        for(Map.Entry<Integer, IValue> entry: heap.getAll())
        {
            heapTableList.add(entry);
        }

        ObservableList<Map.Entry<Integer,IValue>> list = FXCollections.observableArrayList();
        list.addAll(heapTableList);
        heapTable.setItems(list);
        heapTable.refresh();
    }

    public void populateOut(PrgState currPrg)
    {
        IList<IValue> outputList = new MyList<>();
        outputList = currPrg.getOut();

        ObservableList<IValue> list = FXCollections.observableArrayList();
        for (Iterator<IValue> it = outputList.getAll(); it.hasNext(); ) {
            IValue val = it.next();
            list.add(val);
        }
        outListView.setItems(list);
        outListView.refresh();
    }

    public void populateIdList()
    {
        List<PrgState> prgStateList = controller.r.getPrgList();
        ObservableList<Integer> list = FXCollections.observableArrayList();
        for(PrgState prg : prgStateList)
        {
            list.add(prg.getStateID());
        }
        programStateIdList.setItems(list);
    }

    public void populateFileTable(PrgState currPrg)
    {
        IDict<StringValue, BufferedReader> fileTable = new MyDict<>();
        fileTable = currPrg.getFileTable();
        ObservableList<Map.Entry<StringValue,BufferedReader>> list = FXCollections.observableArrayList();
        for(Map.Entry<StringValue,BufferedReader> entry: fileTable.getAll())
        {
            list.add(entry);
        }
        fileTableView.setItems(list);
        fileTableView.refresh();
    }

    public void populateSymTable(PrgState currPrg)
    {
        IDict<String, IValue> symTable = new MyDict<>();
        symTable = currPrg.getSymTable();

        ObservableList<Map.Entry<String,IValue>> list = FXCollections.observableArrayList();
        for(Map.Entry<String, IValue> entry: symTable.getAll())
        {
            list.add(entry);
        }
        symTableView.setItems(list);
        symTableView.refresh();
    }

    public void populateExeStack(PrgState currPrg) {
        IStack<IStmt> executionStack = currPrg.getStk();

        List<String> executionStackList = new ArrayList<>();
        for (IStmt s : executionStack.getAll()) {
            executionStackList.add(s.toString());
        }

        executionStackListView.setItems(FXCollections.observableList(executionStackList));
        executionStackListView.refresh();
    }

    public PrgState getPrgState()
    {
        if(programStateIdList.getSelectionModel().getSelectedItem() == null)
            return null;
        int id = programStateIdList.getSelectionModel().getSelectedItem();

        return controller.r.getPrgById(id);
    }
}
