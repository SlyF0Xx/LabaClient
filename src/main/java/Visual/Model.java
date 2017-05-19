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
    ObservableList<VisualPerson> VisualPersonData;

    ObservableList<VisualPerson> GetVisualPersonData(){return VisualPersonData;}

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
                VisualPersonData.add(new VisualPerson(pers,0));
        });
    }

    public boolean Add(VisualPerson temp)
    {
        Updater.AddPerson(temp.getPerson());

        /*       if(VisualPersonData.size()!=Updater.GetAll().size()) {
            VisualPersonData.add(temp);
            return true;
        }*/
        return false;
    }

    boolean Add(ObservableList<VisualPerson>  temp)
    {
        if(!VisualPersonData.equals(temp))
        {
            VisualPersonData.clear();
            VisualPersonData.addAll(temp);
            return true;
        }
        return false;
    }

    public boolean Update()
    {
        LinkedList<Person> temp = new LinkedList<Person>(Updater.GetAll().values());
        LinkedList<VisualPerson>  temp2 = new LinkedList<VisualPerson>();
        for (Person i: temp) {
            temp2.add(new VisualPerson(i, 0));
        }
        //ObservableList<Person>  temp = FXCollections.observableList(new LinkedList<VisualPerson>(Updater.GetAll().values()));

        return Add(FXCollections.observableList(temp2));
    }

    public void Delete(VisualPerson person)
    {
        Updater.DeletePerson(person.getPerson().GetName());
        VisualPersonData.removeAll(person);
    }

    public Model()
    {
        LinkedList<Person> temp = new LinkedList<Person>(Updater.GetAll().values());
        LinkedList<VisualPerson>  temp2 = new LinkedList<VisualPerson>();
        for (Person i: temp) {
            temp2.add(new VisualPerson(i, 0));
        }
        VisualPersonData = FXCollections.observableList(temp2);
    }
}
