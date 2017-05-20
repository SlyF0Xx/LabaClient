package Visual;

import java.util.regex.Pattern;

import Laba2.Leg;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * Created by SlyFox on 18.04.2017.
 */
public class FilterController {
    @FXML
    private TextField FilterName;

    @FXML
    private TextField FilterLocationName;

    @FXML
    private TextField FilterMinCountLeg;

    @FXML
    private TextField FilterMaxCountLeg;

    @FXML
    private CheckBox FilterLegSizeBig;

    @FXML
    private CheckBox FilterLegSizeMedium;

    @FXML
    private CheckBox FilterLegSizeSmall;

    @FXML
    private CheckBox FilterLegWashedTrue;

    @FXML
    private CheckBox FilterLegWashedFalse;

    @FXML
    private CheckBox FilterLegBarefootTrue;

    @FXML
    private CheckBox FilterLegBarefootFalse;

    @FXML
    private ToggleButton FilterLegSizeAll;

    @FXML
    private ToggleButton FilterLegWashedAll;

    @FXML
    private ToggleButton FilterLegBarefootAll;

    @FXML
    private CheckBox FilterCameTrue;

    @FXML
    private CheckBox FilterCameFalse;

    @FXML
    private CheckBox FilterWaitTrue;

    @FXML
    private CheckBox FilterWaitFalse;

    Stage FilterStage;
    Controller controller;

    public void FilterAndClose()
    {
        controller.model.Filter(this.GetInfo());
        FilterStage.close();
    }

    public FilterStruct GetInfo()
    {
        return new FilterStruct(
                FilterName.getText(),
                FilterLocationName.getText(),
                FilterMinCountLeg.getText().equals("")? Integer.MIN_VALUE: Integer.valueOf(FilterMinCountLeg.getText()),
                FilterMaxCountLeg.getText().equals("")? Integer.MAX_VALUE: Integer.valueOf(FilterMaxCountLeg.getText()),
                Pattern.compile((!FilterLegSizeBig.isSelected()&&!FilterLegSizeMedium.isSelected()&&!FilterLegSizeSmall.isSelected())? ".*":
                        ((FilterLegSizeBig.isSelected()? Leg.Size.Big.toString() + "|" : "") +
                        (FilterLegSizeMedium.isSelected()? Leg.Size.Medium.toString() + "|" : "") +
                        (FilterLegSizeSmall.isSelected()? Leg.Size.Small.toString() : ""))),
                Pattern.compile((!FilterLegWashedTrue.isSelected()&&!FilterLegWashedFalse.isSelected())? ".*":
                        ((FilterLegWashedTrue.isSelected()? "true|" : "") +
                        (FilterLegWashedFalse.isSelected()? "false" : ""))),
                Pattern.compile((!FilterLegBarefootTrue.isSelected()&&!FilterLegBarefootFalse.isSelected())? ".*":
                        ((FilterLegBarefootTrue.isSelected()? "true|" : "") +
                        (FilterLegBarefootFalse.isSelected()? "false" : ""))),
                FilterLegSizeAll.isSelected(),
                FilterLegWashedAll.isSelected(),
                FilterLegBarefootAll.isSelected(),
                Pattern.compile((!FilterCameTrue.isSelected()&&!FilterCameFalse.isSelected())? ".*":
                        ((FilterCameTrue.isSelected()? "true|" : "") +
                                (FilterCameFalse.isSelected()? "false" : ""))),
                Pattern.compile((!FilterWaitTrue.isSelected()&&!FilterWaitFalse.isSelected())? ".*":
                        ((FilterWaitTrue.isSelected()? "true|" : "") +
                                (FilterWaitFalse.isSelected()? "false" : "")))
        );
    }

    public void SetMain(Controller controller, Stage FilterStage)
    {
        this.FilterStage = FilterStage;
        this.controller = controller;

    }
}
