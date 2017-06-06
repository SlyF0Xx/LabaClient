package Visual;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import Cmd.Command;
import Cmd.Commands;
import Cmd.Exit;
import I18n.SupportI18n;
import Laba2.Laba0;
import Laba2.Leg;
import Laba2.Person;
import Laba2.Reciver;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private TableView<VisualPerson> table;

    @FXML
    private TableColumn<VisualPerson, String> Name;

    @FXML
    private TableColumn<VisualPerson, String> LegCount;

    @FXML
    private TableColumn<VisualPerson, Spinner<Integer>> LegIndex;

    @FXML
    private TableColumn<VisualPerson, String> LegSize;

    @FXML
    private TableColumn<VisualPerson, String> LegWashed;

    @FXML
    private TableColumn<VisualPerson, String> LegBarefoot;

    @FXML
    private TableColumn<VisualPerson, String> LocationName;

    @FXML
    private TableColumn<VisualPerson, String> Came;

    @FXML
    private TableColumn<VisualPerson, String> Wait;

    @FXML
    private TableColumn<VisualPerson, Button> Delete;

    @FXML
    private TableColumn<VisualPerson, MenuButton> Actions;

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

    @FXML
    private Button ChangeLoc;

    private Laba0 main;
    private Stage mainStage;
    private FilterController controller;

    Model model;

    private static StringProperty GetVisualParametr(Object param, String key) {
        StringProperty temp = new SimpleStringProperty(param.toString());
        temp.bind(SupportI18n.createStringBinding(key, temp.get()));
        return temp;
    }
    
    private void CreateRowButtonDelete()
    {
        Delete.setCellFactory(column->{return new TableCell<VisualPerson, Button>()
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
                    item.textProperty().bind(SupportI18n.createStringBinding("ButtonDelete", ""));
                    item.setPrefHeight(table.getFixedCellSize());
                    //item.setPrefWidth(getWidth());
                    item.setOnAction(event -> {
                        model.Delete(model.GetVisualPersonData().get(getIndex()));
                    });
                    setGraphic(item);
                }
            }
        };
        });
    }

    private void CreateRowButtonActions()
    {
        Actions.setCellFactory(column-> new TableCell<VisualPerson, MenuButton>()
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
    }


    Commands commands;
    private void InitialCommand()
    {
        commands = new Commands();

        //commands.SetCommand("add_if_min", new AddIfMin());
        //commands.SetCommand("remove_lower", new RemoveLower());
        //commands.SetCommand("remove_all", new RemoveAll());
        //commands.SetCommand("show_all", new ShowAll());
        //commands.SetCommand("save", new Save());
        //commands.SetCommand("load", new Load());
        commands.SetCommand("exit", new Exit());

        System.out.println("Готов к отправке команд");

        Set<String> coms = Updater.GetCommandNames();

        System.out.println("Команды получены");

        for (String i: coms)
        {
            commands.SetCommand(i, new Command() {
                @Override
                public String toString() {
                    return i;
                }

                @Override
                public Object[] read(String string) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException
                {
                    return new Object[] {string};
                }

                @Override
                public boolean execute(Object... objects) throws Exception {
                    synchronized (Command.class)
                    {
                        return Updater.ExecuteCommand(i, (String) objects[0]);
                    }
                }
            });
        }


        ObservableList<Command> info = FXCollections.observableArrayList();
        for (Command i : commands.GetCommands().values()) {
            info.add(i);
        }

        BoxCommands.setItems(info);
    }

    private void SetTableFactory()
    {
        Name.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());
        LegCount.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());

        //LegIndex.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());
        LegSize.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());
        LegWashed.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());
        LegBarefoot.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());

        LocationName.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());
        Came.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());
        Wait.setCellFactory(TextFieldTableCell.<VisualPerson>forTableColumn());

        LegIndex.setCellFactory(column-> { return new TableCell<VisualPerson, Spinner<Integer>>() {
            @Override
            protected void updateItem(Spinner<Integer> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    item = new Spinner<Integer>(0, Integer.valueOf(LegCount.getCellData(getIndex()))-1, model.GetVisualPersonData().get(getIndex()).getLegIndex());
                    item.setPrefHeight(table.getFixedCellSize());
                    item.setEditable(true);

                    item.valueProperty().addListener(
                            ((observable, oldValue, newValue) ->
                            {
                                try {
                                    model.GetVisualPersonData().get(getIndex()).setLegIndex(newValue);
                                }
                                catch (IndexOutOfBoundsException e)
                                {
                                    System.out.println("Забавная ситуация. По какой-то причине программа не видит индекса, при этом успешно изменяет данные по нему же");
                                }
                                table.refresh();
                            })
                    );

                    setGraphic(item);
                }
            }
        };
        });


        CreateRowButtonDelete();

        CreateRowButtonActions();
    }

    private void SetTableValueFactory()
    {
        Name.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().getPerson().GetName(), "String"));
        LegCount.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().getPerson().GetLegCount(), "Integer"));

        LegIndex.setCellValueFactory(cellData-> new SimpleObjectProperty<Spinner<Integer>>(new Spinner<Integer>(0, Integer.valueOf(LegCount.getCellData(cellData.getValue()))-1, cellData.getValue().getLegIndex())));

        LegSize.setCellValueFactory(cellData->
                GetVisualParametr(cellData.getValue().getPerson().GetLegs()[LegIndex.getCellData(cellData.getValue()).getValue()].GetSize().ordinal(),"LegSizeEnum")
        );

        LegWashed.setCellValueFactory(cellData->
                GetVisualParametr(cellData.getValue().getPerson().GetLegs()[LegIndex.getCellData(cellData.getValue()).getValue()].IsWashed()? 1:0, "Boolean"));

        LegBarefoot.setCellValueFactory(cellData->
                GetVisualParametr(cellData.getValue().getPerson().GetLegs()[LegIndex.getCellData(cellData.getValue()).getValue()].IsBarefoot()? 1:0, "Boolean"));

        LocationName.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().getPerson().GetPlace().GetPosition(), "String"));
        Came.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().getPerson().IsCame()? 1:0 , "Boolean"));
        Wait.setCellValueFactory(cellData -> GetVisualParametr(cellData.getValue().getPerson().IsWait()? 1:0, "Boolean"));
    }

    private void SetHadlersAndListeners()
    {
        LocationName.setOnEditCommit(event ->
        {
            event.getRowValue().getPerson().GetPlace().SetPosition(event.getNewValue());
            Updater.EditPerson(event.getRowValue().getPerson().GetName(), event.getRowValue().getPerson());
        });

        LegBarefoot.setOnEditCommit(event ->
        {
            event.getRowValue().getPerson().GetLegs()[LegIndex.getCellData(table.getSelectionModel().getSelectedItem()).getValue()]
                    .SetBarefoot(SupportI18n.parseBoolean(event.getNewValue()));
            Updater.EditPerson(event.getRowValue().getPerson().GetName(), event.getRowValue().getPerson());
        });


        LegWashed.setOnEditCommit(event ->
         {
             event.getRowValue().getPerson().GetLegs()[LegIndex.getCellData(table.getSelectionModel().getSelectedItem()).getValue()]
                            .SetWashed(SupportI18n.parseBoolean(event.getNewValue()));
             Updater.EditPerson(event.getRowValue().getPerson().GetName(), event.getRowValue().getPerson());
         });

        LegSize.setOnEditCommit(event ->
        {
            event.getRowValue().getPerson().GetLegs()[LegIndex.getCellData(table.getSelectionModel().getSelectedItem()).getValue()]
                        .SetSize((Leg.Size)SupportI18n.parseEnum(Leg.Size.class, event.getNewValue()));
            Updater.EditPerson(event.getRowValue().getPerson().GetName(), event.getRowValue().getPerson());
        });


        Came.setOnEditCommit(event -> {
            if(SupportI18n.parseBoolean(event.getNewValue()))
            {
                model.GetVisualPersonData().get(table.getSelectionModel().getSelectedIndex()).getPerson().Come(table.getSelectionModel().getSelectedItem().getPerson().GetPlace());
                Updater.EditPerson(event.getRowValue().getPerson().GetName(), event.getRowValue().getPerson());
            }
        });

        Wait.setOnEditCommit(event ->
        {
                event.getRowValue().getPerson().SetWait(SupportI18n.parseBoolean(event.getNewValue()));
                Updater.EditPerson(event.getRowValue().getPerson().GetName(), event.getRowValue().getPerson());
        });

        Save.setOnAction(event -> Execute(
                commands.GetCommands().get("save")
                , null));

        Load.setOnAction(event -> Execute(
                commands.GetCommands().get("load")
                , null));

        Text.setStyle("-fx-text-fill: black;");

        ColorChoose.valueProperty().addListener((observable, oldColor, newColor) ->
                Text.setStyle("-fx-text-fill: " + toRgbString(newColor) + ";"));

        Slider.valueProperty().addListener((ov, old_val, new_val) ->
                table.scrollTo((int) ((double)new_val/ (Slider.getMax()/table.getItems().size()))));

        FilterButton.setOnAction(event -> {
            if(controller!=null) model.Filter(controller.GetInfo());
            else model.Filter(null);
        });

        ChangeLoc.setOnAction(event -> main.CreateWindow( new Locale(Text.getText().split(" ")[0], Text.getText().split(" ")[1]),
                mainStage.getScene().getWidth(),
                mainStage.getScene().getHeight()
        ));
    }

    @FXML
    private void initialize()
    {
        Updater.SetInetAddress();
        /*
        Updater.SendObject(1);
        Updater.SendObject(15);
        Updater.SendObject(1155255442);
        Updater.SendObject(957);
        */

        //Person pers =  Updater.GetPerson("Mal");

        model = new Model();

        System.out.println("Получены люди");

        Reciver.setModel(model);

        table.setFixedCellSize(45.0);

        table.setItems(model.GetVisualPersonData());

        InitialCommand();

        SetTableFactory();

        SetTableValueFactory();

        SetHadlersAndListeners();
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
        ObservableList list = table.getItems();
        if(table.getHeight() - (table.getItems().size()+1)*table.getFixedCellSize() <0)
        {
            Slider.setVisible(true);
        }
        else
        {
            Slider.setVisible(false);
        }
    }

    public void CreateFilter()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../Filter.fxml"));

        Parent root = null;
        try {
            loader.setResources(ResourceBundle.getBundle("I18n.StatsBundle", main.getCurrentLocale()));
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

    public void Execute()
    {
        Execute(BoxCommands.getValue(),Text.getText());
    }

    public static void InitAlert(String key)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предупреждение!");
        alert.setHeaderText(null);
        int val = (int)(Math.random()*10);
        Sound s;
        switch(val){
        case 0:{
        	s = new Sound(Laba0.class.getResourceAsStream("/attention.wav"));
        	break;
        }
        default:{
        	s = new Sound(Laba0.class.getResourceAsStream("/Delete.wav"));
        }
        }
        s.play();
        alert.contentTextProperty().bind(SupportI18n.createStringBinding(key));
        alert.showAndWait();
    }

    public void Execute(Command command, String parametrs)
    {
        Thread thread = new Thread(){
            public void run()
            {
                try
                {
                    sleep(1000);

                    if(command.execute(command.read(parametrs)))
                    {
                        System.exit(0);
                    }

                    if(model.Update())
                    {
                        Zoom();
                    }

                    Platform.runLater(() -> InitAlert("Success"));
                } catch (FileNotFoundException e) {
                    Platform.runLater(() -> InitAlert("FileNotFound"));
                } catch (IOException e) {
                    Platform.runLater(() -> InitAlert("InputJSON"));
                } catch (InstantiationException e) {
                    Platform.runLater(() -> InitAlert("CannotCreateObject"));
                } catch (IllegalAccessException e) {
                    Platform.runLater(() -> InitAlert("AccessObjectDeny"));
                } catch (ClassNotFoundException e) {
                    Platform.runLater(() -> InitAlert("ClassNotFound"));
                } catch (Exception e)
                {
                    Platform.runLater(() -> InitAlert("UnknownException"));
                }
            }
        };

        thread.start();
    }

    public void setMain(Laba0 main, Stage mainStage)
    {
        this.mainStage = mainStage;
        this.main = main;
    }

    public void GenerateRandom()
    {
        String Legs = new String();

        String RandomLegSiz = Leg.Size.class.getEnumConstants()[(int)(Math.random()*2)].toString();
        Legs += "{\"Washed\":" + String.valueOf(Math.random() >0.5? true : false) +
                ",\"Barefoot\":"+ String.valueOf(Math.random() >0.5? true : false) +
                ",\"LegSize\":\"" + RandomLegSiz+ "\"}";

        for(int i = 0; i<Math.random()*10;i++)
        {
            String RandomSize = Leg.Size.class.getEnumConstants()[(int)(Math.random()*2)].toString();
            Legs += ",{\"Washed\":" + String.valueOf(Math.random() >0.5? true : false) +
                    ",\"Barefoot\":"+ String.valueOf(Math.random() >0.5? true : false) +
                    ",\"LegSize\":\"" + RandomSize+ "\"}";
        }

        try {
            NewElement("Laba2.FrekenBok "+"{\"Legs\":[" +
                Legs +
                "],\"Place\":{\"Position\":\"" + String.valueOf(Math.random()) +
                    "\"},\"Name\":\"" + (char)(Math.random()*256) +
                    "\",\"Came\":" + String.valueOf(Math.random() >0.5? true : false) +
                    ",\"wait\":" + String.valueOf(Math.random() >0.5? true : false) +
                    "}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void NewElement()
    {
        try {
            NewElement(Text.getText());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void NewElement(String message) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        String[] strings = message.split(" ");
        Person temp;

        if(!strings[0].equals(""))
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            try
            {
                temp = (Person) mapper.readValue(message.split(strings[0],2)[1], Class.forName(strings[0]));
            }
            catch (Exception e)
            {
                temp = (Person) Class.forName(strings[0]).newInstance();
            }

            if(model.Add(new VisualPerson(temp)))
            {
                Zoom();
            }
        }
        else
        {
            Platform.runLater(() -> InitAlert("InputParams"));
        }

    }
}
