package Visual;

import Laba2.Leg;
import Laba2.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

/**
 * Created by SlyFox on 26.04.2017.
 */
public class Model {
    ObservableList<Person> VisualPersonData;

    ObservableList<Person> GetVisualPersonData(){return VisualPersonData;}

    public void Filter(FilterStruct filter)
    {
        VisualPersonData.clear();

        Updater.GetAll().forEach((str, pers)->{
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
                VisualPersonData.add(pers);
        });
    }

    boolean Add(Person temp)
    {
        Updater.Add(temp);

        if(VisualPersonData.size()!=Updater.GetAll().size()) {
            VisualPersonData.add(temp);
            return true;
        }
        return false;
    }

    boolean Add(ObservableList<Person>  temp)
    {
        if(!VisualPersonData.equals(temp))
        {
            VisualPersonData.clear();
            VisualPersonData.addAll(temp);
            return true;
        }
        return false;
    }

    boolean Update()
    {
        ObservableList<Person>  temp = FXCollections.observableList(new LinkedList<Person>(Updater.GetAll().values()));

        return Add(temp);
    }

    Model()
    {
        VisualPersonData = FXCollections.observableList(new LinkedList<Person>(Updater.GetAll().values()));
    }
}
