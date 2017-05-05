package Visual;

import Laba2.Location;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * Created by SlyFox on 20.04.2017.
 */
public class MyMenu {
    TextField CameTextField;
    CustomMenuItem CameCustomMenuItem;
    Menu CameSideMenu;

    TextField WaitTextField;
    CustomMenuItem WaitCustomMenuItem;
    Menu WaitSideMenu;

    MenuButton menuButton;

    MyMenu(Controller owner, int current)
    {
        CameTextField = new TextField();
        CameCustomMenuItem = new CustomMenuItem(CameTextField, false);
        CameSideMenu = new Menu("came", null, CameCustomMenuItem);


        WaitTextField = new TextField();
        WaitCustomMenuItem = new CustomMenuItem(WaitTextField, false);
        WaitSideMenu = new Menu("wait", null, WaitCustomMenuItem);

        menuButton = new MenuButton("Actions", null, CameSideMenu, WaitSideMenu);

        CameTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                owner.model.GetVisualPersonData().get(current).getPerson().Come(new Location(CameTextField.getText()));
                owner.model.GetVisualPersonData().set(current, owner.model.GetVisualPersonData().get(current));
            }
        });

        WaitTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                owner.model.GetVisualPersonData().get(current).getPerson().Waiting(Updater.GetPerson(WaitTextField.getText()));
                owner.model.GetVisualPersonData().set(current, owner.model.GetVisualPersonData().get(current));
            }
        });

    }
}
