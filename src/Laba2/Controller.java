package Laba2;

import Cmd.*;
import Exceptions.ExceptionWrongName;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    @FXML
    private TableView<Person> table;

    @FXML
    private TableColumn<Person, String> Name;

    @FXML
    private TextField FilterName;

    @FXML
    private TableColumn<Person, String> LegCount;

    @FXML
    private TextField FilterLegCount;

    @FXML
    private TableColumn<Person, String> LegIndex;

    @FXML
    private TextField FilterLegIndex;

    @FXML
    private TableColumn<Person, String> LegSize;

    @FXML
    private TextField FilterLegSize;

    @FXML
    private TableColumn<Person, String> LegWashed;

    @FXML
    private TextField FilterLegWashed;

    @FXML
    private TableColumn<Person, String> LegBarefoot;

    @FXML
    private TextField FilterLegBarefoot;

    @FXML
    private TableColumn<Person, String> LocationName;

    @FXML
    private TextField FilterLocationName;

    @FXML
    private TableColumn<Person, String> Came;

    @FXML
    private TextField FilterCame;

    @FXML
    private TableColumn<Person, String> Wait;

    @FXML
    private TextField FilterWait;

    @FXML
    private TableColumn<Person, String> Delete;

    @FXML
    private ComboBox<Command> BoxCommands;

    @FXML
    private TextArea Text;

    @FXML
    private Slider Slider;

    @FXML
    private MenuItem NewElement;

    @FXML
    private MenuItem Save;

    @FXML
    private MenuItem Load;

    @FXML
    private ColorPicker ColorChoose;

    private  Laba0 main;

    public StringProperty GetVisualParametr(Object param) {return new SimpleStringProperty(param.toString());}

    @FXML
    private void initialize()
    {
        Name.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        LegCount.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        LegIndex.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        LegSize.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        LegWashed.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        LegBarefoot.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        LocationName.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        Came.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        Wait.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        Delete.setCellFactory(TextFieldTableCell.<Person>forTableColumn());


        Name.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetName()));
        LegCount.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegCount()));

        LegIndex.setCellValueFactory(cellData -> new SimpleStringProperty(LegIndex.getUserData()==null? "0": LegIndex.getUserData().toString()));

        LegSize.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[((Integer) LegIndex.getUserData())==null? 0: (Integer) LegIndex.getUserData()].GetSize()));
        LegWashed.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[((Integer) LegIndex.getUserData())==null? 0: (Integer) LegIndex.getUserData()].IsWashed()));
        LegBarefoot.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[((Integer) LegIndex.getUserData())==null? 0: (Integer) LegIndex.getUserData()].IsBarefoot()));

        LocationName.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetPlace().GetPosition()));
        Came.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().IsCame()));
        Wait.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().IsWait()));


        LocationName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                data2.get(table.getSelectionModel().getSelectedIndex()).GetPlace().SetPosition(event.getNewValue());
            }
        });

        LegBarefoot.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                data2.get(table.getSelectionModel().getSelectedIndex()).GetLegs()[Integer.valueOf(LegIndex.getCellData(table.getSelectionModel().getSelectedItem()))].SetBarefoot(Boolean.parseBoolean(event.getNewValue()));
            }
        });

        LegWashed.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                //table.set = Boolean.parseBoolean(event.getNewValue());
                data2.get(table.getSelectionModel().getSelectedIndex()).GetLegs()[Integer.valueOf(LegIndex.getCellData(table.getSelectionModel().getSelectedItem()))].SetWashed(Boolean.parseBoolean(event.getNewValue()));
            }
        });

        LegSize.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                data2.get(table.getSelectionModel().getSelectedIndex()).GetLegs()[Integer.valueOf(LegIndex.getCellData(table.getSelectionModel().getSelectedItem()))].SetSize(Leg.Size.valueOf(event.getNewValue()));
            }
        });

        LegIndex.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                if(Integer.valueOf(event.getNewValue()) < table.getSelectionModel().getSelectedItem().GetLegCount() && Integer.valueOf(event.getNewValue()) >= 0)
                {
                    LegIndex.setUserData(Integer.valueOf(event.getNewValue()));
                    table.getItems().set(Integer.valueOf(table.getSelectionModel().getSelectedIndex()), table.getSelectionModel().getSelectedItem());
                }
                else
                {
                    InitAlert("Неверное значение индекса ноги!");
                }
            }
        });

        Came.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                if(Boolean.parseBoolean(event.getNewValue()))
                {
                    data2.get(table.getSelectionModel().getSelectedIndex()).Come(table.getSelectionModel().getSelectedItem().GetPlace());
                }
            }
        });

        Wait.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                data2.get(table.getSelectionModel().getSelectedIndex()).SetWait(Boolean.parseBoolean(event.getNewValue()));
            }
        });

        Save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Execute(new Save());
            }
        });

        Load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Execute(new Load());
            }
        });

        Delete.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                People.GetPersons().remove(table.getSelectionModel().getSelectedItem().GetName());
                table.getItems().remove(table.getSelectionModel().getSelectedIndex());
            }
        });

        Text.setStyle("-fx-text-fill: black;");

        ColorChoose.valueProperty().addListener((observable, oldColor, newColor) ->
                Text.setStyle(
                        "-fx-text-fill: " + toRgbString(newColor) + ";"
                ));


        Slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                table.scrollTo((int) ((double)new_val/ (Slider.getMax()/table.getItems().size())));
                //table.scrollTo((int) new_val);
            }
        });
    }

    public void Filter()
    {
        table.getItems().clear();

        People.GetPersons().forEach((str, pers)->{

            //FilterName.getUserData() != null ? ((!pers.GetName().equals(FilterName.getUserData()) ) ? return; : return;) : System.out.print("");

            if(!FilterName.getText().equals("")) if(!pers.GetName().equals(FilterName.getText())) return;

            if(!FilterLegCount.getText().equals("")) if (pers.GetLegCount() != (Integer.valueOf(FilterLegCount.getText()))) return;

            if(!FilterLegIndex.getText().equals("")) if(!LegIndex.getCellObservableValue(pers).getValue().equals(FilterLegIndex.getText())) return;

            if(!FilterLegBarefoot.getText().equals("")) if(pers.GetLegs()[Integer.valueOf(LegIndex.getCellObservableValue(pers).getValue())].IsBarefoot() != Boolean.valueOf(FilterLegBarefoot.getText())) return;

            if(!FilterLegSize.getText().equals("")) if(pers.GetLegs()[Integer.valueOf(LegIndex.getCellObservableValue(pers).getValue())].GetSize() != Leg.Size.valueOf(FilterLegSize.getText())) return;

            if(!FilterLegWashed.getText().equals("")) if(pers.GetLegs()[Integer.valueOf(LegIndex.getCellObservableValue(pers).getValue())].IsWashed() != Boolean.valueOf(FilterLegWashed.getText())) return;

            if(!FilterLocationName.getText().equals("")) if(!pers.GetPlace().GetPosition().equals(FilterLocationName.getText())) return;

            if(!FilterCame.getText().equals("")) if(pers.IsCame() != Boolean.valueOf(FilterCame.getText())) return;

            if(!FilterWait.getText().equals("")) if(pers.IsWait() != Boolean.valueOf(FilterWait.getText())) return;

            table.getItems().add(pers);
        });
    }

    private String toRgbString(Color c) {
        return "rgb("
                + to255Int(c.getRed())
                + "," + to255Int(c.getGreen())
                + "," + to255Int(c.getBlue())
                + ")";
    }

    private int to255Int(double d) {
        return (int) (d * 255);
    }

    public void Zoom()
    {
        table.setFixedCellSize(45.0);

        if(table.getHeight() - (table.getItems().size()+1)*table.getFixedCellSize() <0)
        {
            Slider.setVisible(true);
        }
        else
        {
            Slider.setVisible(false);
        }
    }

    public void ChangeLeg()
    {
        /*
        Person temp = table.getSelectionModel().getSelectedItem();

        String temp2 =  LegIndex.getCellData(table.getSelectionModel().getSelectedItem());



        if(LegIndex.getCellData(table.getSelectionModel().getSelectedItem()) == String.valueOf(table.getSelectionModel().getSelectedItem().GetLegCount()))
        {
            table.getItems().set(Integer.valueOf(table.getSelectionModel().getSelectedIndex()), table.getSelectionModel().getSelectedItem());
            /*
            LegSize.getCellData(table.getSelectionModel().getSelectedItem())

            Integer.valueOf(LegIndex.getCellData(cellData.getValue()))

            LegSize.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[0].GetSize()));
            LegWashed.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[0].IsWashed()));
            LegBarefoot.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[0].IsBarefoot()));
        }
        else
        {
        }*/
    }

    public void Execute()
    {
        Execute(BoxCommands.getValue());
    }

    private void InitAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предупреждение!");
        alert.setHeaderText(null);

        alert.setContentText(message);
        alert.showAndWait();
    }

    public void Execute(Command command)
    {
        Thread thread = new Thread(){
            public void run()
            {
                try
                {
                    sleep(1000);

                    if(command.execute(command.read(Text.getText())))
                    {
                        System.exit(0);
                    }

                    ObservableList<Person>  temp = FXCollections.observableList(new LinkedList<Person>(People.GetPersons().values()));
                    if(!table.getItems().equals(temp))
                    {
                        data2.clear();
                        data2.addAll(temp);
                        Zoom();
                        //.setItems(temp);
                    }

                    Platform.runLater(() -> InitAlert("Успешно!"));
                } catch (FileNotFoundException e) {
                    Platform.runLater(() -> InitAlert("Файл не найден"));
                } catch (IOException e) {
                    Platform.runLater(() -> InitAlert("Введите в поле ввода элеенты в json и убедитесь в наличии соотвествующего файла и установки переменной среды окружения ReadFileDir"));
                } catch (InstantiationException e) {
                    Platform.runLater(() -> InitAlert("Невозможно создать объект класса. Возмжно, класс не имеет конструктора без параметров. Убедитесь в корректности файла"));
                } catch (IllegalAccessException e) {
                    Platform.runLater(() -> InitAlert("Невозможно создать объект класса. Возмжно, класс не имеет конструктора без параметров. Убедитесь в корректности файла"));
                } catch (ClassNotFoundException e) {
                    Platform.runLater(() -> InitAlert("Класс не найден. Объекты данного класа не поддерживаются программой"));
                } catch (Exception e)
                {
                    Platform.runLater(() -> InitAlert("Неопознанная ошибка"));
                }
            }
        };

        thread.start();
    }

    ObservableList<Person> data2 = FXCollections.observableList(new LinkedList<Person>(People.GetPersons().values()));

    public void setMain(Laba0 main)
    {
        this.main = main;

        ObservableList<Person>  data =  FXCollections.observableArrayList();

        for (Person i : People.GetPersons().values()) {
            data.add(i);
        }

        table.setItems(data2);

        Commands commands = new Commands();

        commands.SetCommand("add_if_min", new AddIfMin());
        commands.SetCommand("remove_lower", new RemoveLower());
        commands.SetCommand("remove_all", new RemoveAll());
        commands.SetCommand("show_all", new ShowAll());
        commands.SetCommand("save", new Save());
        commands.SetCommand("load", new Load());
        commands.SetCommand("exit", new Exit());

        ObservableList<Command> info = FXCollections.observableArrayList();
        for (Command i : commands.GetCommands().values()) {
            info.add(i);
        }

        BoxCommands.setItems(info);
    }

    public void NewElement() throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        String[] strings = Text.getText().split(" ");
        Person temp;

        if(!strings[0].equals(""))
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            try
            {
                temp = (Person) mapper.readValue(Text.getText().split(strings[0],2)[1], Class.forName(strings[0]));
            }
            catch (Exception e)
            {
                temp = (Person) Class.forName(strings[0]).newInstance();
            }
            People.AddPerson(temp);

            if(data2.size()!=People.GetPersons().size())
            {
                data2.add(temp);
                Zoom();
            }
        }
        else
        {
            Platform.runLater(() -> InitAlert("Введите в поле ввода параметров название класса"));
        }

    }
}
