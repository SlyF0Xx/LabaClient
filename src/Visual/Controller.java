package Visual;

import Cmd.*;
import Laba2.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Controller {
    @FXML
    private TableView<Person> table;

    //public TableView<Person> GetTable(){return table;}

    @FXML
    private TableColumn<Person, String> Name;

    @FXML
    private TableColumn<Person, String> LegCount;

    @FXML
    private TableColumn<Person, String> LegIndex;

    @FXML
    private TableColumn<Person, String> LegSize;

    @FXML
    private TableColumn<Person, String> LegWashed;

    @FXML
    private TableColumn<Person, String> LegBarefoot;

    @FXML
    private TableColumn<Person, String> LocationName;

    @FXML
    private TableColumn<Person, String> Came;

    @FXML
    private TableColumn<Person, String> Wait;

    @FXML
    private TableColumn<Person, Button> Delete;

    @FXML
    private TableColumn<Person, MenuButton> Actions;

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

    @FXML
    private Button FilterButton;

    private Laba0 main;
    private Stage mainStage;
    private FilterController controller;

    public StringProperty GetVisualParametr(Object param) {return new SimpleStringProperty(param.toString());}

    @FXML
    private void initialize()
    {
        table.setFixedCellSize(45.0);

        table.setItems(VisualPersonData);

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


        Name.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        LegCount.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        LegIndex.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        LegSize.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        LegWashed.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        LegBarefoot.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        LocationName.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        Came.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        Wait.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        Delete.setCellFactory(column->{return new TableCell<Person, Button>()
        {
            @Override
            protected void updateItem(Button item, boolean empty)
            {
                super.updateItem(item, empty);
                if(empty)
                {
                    setGraphic(null);
                }
                else
                {
                    item = new Button("Du! Du hast!");
                    item.setPrefHeight(table.getFixedCellSize());
                    //item.setPrefWidth(getWidth());
                    item.setOnAction(event -> {
                        People.GetPersons().remove(table.getItems().get(getIndex()).GetName());
                        table.getItems().remove(getIndex());
                    });
                    setGraphic(item);
                }
            }
        };
        });

        Actions.setCellFactory(column-> new TableCell<Person, MenuButton>()
        {
            @Override
            protected void updateItem(MenuButton item, boolean empty)
            {
                super.updateItem(item, empty);
                if(empty)
                {
                    setGraphic(null);
                }
                else
                {
                    item = new MyMenu(Controller.this, getIndex()).menuButton;
                    item.setPrefHeight(table.getFixedCellSize());
                    setGraphic(item);
                }
            }
        });
            Name.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetName()));
            LegCount.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegCount()));

        LegIndex.setCellValueFactory(cellData -> new SimpleStringProperty(LegIndex.getUserData()==null? "0": LegIndex.getUserData().toString()));

        LegSize.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[((Integer) LegIndex.getUserData())==null? 0: (Integer) LegIndex.getUserData()].GetSize()));
        LegWashed.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[((Integer) LegIndex.getUserData())==null? 0: (Integer) LegIndex.getUserData()].IsWashed()));
        LegBarefoot.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetLegs()[((Integer) LegIndex.getUserData())==null? 0: (Integer) LegIndex.getUserData()].IsBarefoot()));

        LocationName.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().GetPlace().GetPosition()));
        Came.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().IsCame()));
        Wait.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().IsWait()));


        LocationName.setOnEditCommit(event -> VisualPersonData.get(table.getSelectionModel().getSelectedIndex()).GetPlace().SetPosition(event.getNewValue()));

        LegBarefoot.setOnEditCommit(event ->
                VisualPersonData.get(table.getSelectionModel().getSelectedIndex()).GetLegs()[Integer.valueOf(LegIndex.getCellData(table.getSelectionModel().getSelectedItem()))].SetBarefoot(Boolean.parseBoolean(event.getNewValue())));

        LegWashed.setOnEditCommit(event ->
                VisualPersonData.get(table.getSelectionModel().getSelectedIndex()).GetLegs()[Integer.valueOf(LegIndex.getCellData(table.getSelectionModel().getSelectedItem()))].SetWashed(Boolean.parseBoolean(event.getNewValue())));

        LegSize.setOnEditCommit(event ->
                VisualPersonData.get(table.getSelectionModel().getSelectedIndex()).GetLegs()[Integer.valueOf(LegIndex.getCellData(table.getSelectionModel().getSelectedItem()))].SetSize(Leg.Size.valueOf(event.getNewValue())));

        LegIndex.setOnEditCommit(event -> {
            if(Integer.valueOf(event.getNewValue()) < table.getSelectionModel().getSelectedItem().GetLegCount() && Integer.valueOf(event.getNewValue()) >= 0)
            {
                LegIndex.setUserData(Integer.valueOf(event.getNewValue()));
                table.getItems().set(Integer.valueOf(table.getSelectionModel().getSelectedIndex()), table.getSelectionModel().getSelectedItem());
            }
            else
            {
                InitAlert("Неверное значение индекса ноги!");
            }
        });

        Came.setOnEditCommit(event -> {
            if(Boolean.parseBoolean(event.getNewValue()))
            {
                VisualPersonData.get(table.getSelectionModel().getSelectedIndex()).Come(table.getSelectionModel().getSelectedItem().GetPlace());
            }
        });

        Wait.setOnEditCommit(event -> VisualPersonData.get(table.getSelectionModel().getSelectedIndex()).SetWait(Boolean.parseBoolean(event.getNewValue())));

        Save.setOnAction(event -> Execute(new Save()));

        Load.setOnAction(event -> Execute(new Load()));

        Text.setStyle("-fx-text-fill: black;");

        ColorChoose.valueProperty().addListener((observable, oldColor, newColor) ->
                Text.setStyle(
                        "-fx-text-fill: " + toRgbString(newColor) + ";"
                ));


        Slider.valueProperty().addListener((ov, old_val, new_val) -> {
            table.scrollTo((int) ((double)new_val/ (Slider.getMax()/table.getItems().size())));
        });

        FilterButton.setOnAction(event -> {
            if(controller!=null)
            {
                Filter(controller.GetInfo());
            }
            else
            {
                Filter(null);
            }
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

    public void CreateFilter()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Filter.fxml"));

        Parent root = null;
        try {
            root = loader.load();
            Stage FilterStage = new Stage();
            FilterStage.setTitle("Filter");
            FilterStage.setScene(new Scene(root, 600, 422));
            FilterStage.initOwner(mainStage);
            FilterStage.setResizable(false);

            controller = loader.getController();
            controller.SetMain(this, FilterStage);

            FilterStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Filter(FilterStruct filter)
    {
        table.getItems().clear();

        People.GetPersons().forEach((str, pers)->{
            if(!filter.GetName().equals("") && filter.GetName()!=null && !pers.GetName().equals(filter.GetName())) return;

            if(!filter.GetLocationName().equals("") && filter.GetLocationName()!=null && !pers.GetPlace().GetPosition().equals(filter.GetLocationName())) return;

            if(pers.GetLegs().length<filter.GetMinCountLeg() || pers.GetLegs().length>filter.GetMaxCountLeg()) return;

            boolean LegSizeAllFlag = false;
            boolean LegWashedAllFlag = false;
            boolean LegBarefootAllFlag = false;

            for(Leg i: pers.GetLegs())
            {
                if (filter.IsLegSizeAll())
                {
                    if(!filter.GetLegSize().matcher(i.GetSize().toString()).matches()) return;
                }
                else
                {
                    if(filter.GetLegSize().matcher(i.GetSize().toString()).matches()) LegSizeAllFlag = true;
                }

                if(filter.IsLegBarefootAll())
                {
                    if(!filter.GetLegBarefoot().matcher(String.valueOf(i.IsBarefoot())).matches()) return;
                }
                else
                {
                    if(filter.GetLegBarefoot().matcher(String.valueOf(i.IsBarefoot())).matches()) LegBarefootAllFlag = true;
                }

                if(filter.IsLegWashedAll())
                {
                    if(!filter.GetLegWashed().matcher(String.valueOf(i.IsWashed())).matches()) return;
                }
                else
                {
                    if(filter.GetLegWashed().matcher(String.valueOf(i.IsWashed())).matches()) LegWashedAllFlag = true;
                }
            }

            if(!filter.GetCame().matcher(String.valueOf(pers.IsCame())).matches()) return;
            if(!filter.GetWait().matcher(String.valueOf(pers.IsWait())).matches()) return;


            if((filter.IsLegSizeAll() || LegSizeAllFlag) &&
                    (filter.IsLegWashedAll() || LegWashedAllFlag) &&
                    (filter.IsLegBarefootAll() || LegBarefootAllFlag))
            table.getItems().add(pers);
        });
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
                        VisualPersonData.clear();
                        VisualPersonData.addAll(temp);
                        Zoom();
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

    ObservableList<Person> VisualPersonData = FXCollections.observableList(new LinkedList<Person>(People.GetPersons().values()));

    ObservableList<Person> GetVisualPersonData(){return VisualPersonData;}

    public void setMain(Laba0 main, Stage mainStage)
    {
        this.mainStage = mainStage;
        this.main = main;
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

            if(VisualPersonData.size()!=People.GetPersons().size())
            {
                VisualPersonData.add(temp);
                Zoom();
            }
        }
        else
        {
            Platform.runLater(() -> InitAlert("Введите в поле ввода параметров название класса"));
        }

    }
}
