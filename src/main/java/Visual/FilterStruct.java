package Visual;

import java.util.regex.*;

/**
 * Created by SlyFox on 18.04.2017.
 */
public class FilterStruct {
    private String Name;
    private String LocationName;

    private int MinCountLeg;
    private int MaxCountLeg;

    private Pattern LegSize;
    private Pattern LegWashed;
    private Pattern LegBarefoot;

    private boolean LegSizeAll;
    private boolean LegWashedAll;
    private boolean LegBarefootAll;

    private Pattern Came;
    private Pattern Wait;

    public String GetName()
    {
        return Name;
    }
    public void SetLocationName(String locationName) {
        LocationName = locationName;
    }
    public void SetName(String name) {
        Name = name;
    }
    public String GetLocationName() {
        return LocationName;
    }
    public int GetMinCountLeg() {
        return MinCountLeg;
    }
    public int GetMaxCountLeg() {
        return MaxCountLeg;
    }
    public void SetMinCountLeg(int minCountLeg) {
        MinCountLeg = minCountLeg;
    }
    public void SetMaxCountLeg(int maxCountLeg) {
        MaxCountLeg = maxCountLeg;
    }
    public Pattern GetLegSize() {
        return LegSize;
    }
    public void SetLegSize(Pattern legSize) {
        LegSize = legSize;
    }
    public Pattern GetLegWashed() {
        return LegWashed;
    }
    public Pattern GetLegBarefoot() {
        return LegBarefoot;
    }
    public void SetLegWashed(Pattern legWashed) {
        LegWashed = legWashed;
    }
    public void SetLegBarefoot(Pattern legBarefoot) {
        LegBarefoot = legBarefoot;
    }
    public boolean IsLegBarefootAll() {
        return LegBarefootAll;
    }
    public boolean IsLegSizeAll() {
        return LegSizeAll;
    }
    public boolean IsLegWashedAll() {
        return LegWashedAll;
    }
    public void SetLegSizeAll(boolean legSizeAll) {
        LegSizeAll = legSizeAll;
    }
    public void SetLegBarefootAll(boolean legBarefootAll) {
        LegBarefootAll = legBarefootAll;
    }
    public void SetLegWashedAll(boolean legWashedAll) {
        LegWashedAll = legWashedAll;
    }
    public Pattern GetCame() {
        return Came;
    }
    public Pattern GetWait() {
        return Wait;
    }
    public void SetCame(Pattern came) {
        Came = came;
    }
    public void SetWait(Pattern wait) {
        Wait = wait;
    }

    public FilterStruct(String Name, String LocationName, int MinCountLeg, int MaxCountLeg, Pattern LegSize,
                        Pattern LegWashed, Pattern LegBarefoot, boolean LegSizeAll, boolean LegWashedAll,
                        boolean LegBarefootAll, Pattern Came, Pattern Wait)
    {
        this.Name = Name;
        this.LocationName = LocationName;
        this.MinCountLeg = MinCountLeg;
        this.MaxCountLeg = MaxCountLeg;
        this.LegSize = LegSize;
        this.LegWashed = LegWashed;
        this.LegBarefoot = LegBarefoot;
        this.LegSizeAll = LegSizeAll;
        this.LegWashedAll = LegWashedAll;
        this.LegBarefootAll = LegBarefootAll;
        this.Came = Came;
        this.Wait = Wait;
    }
}
