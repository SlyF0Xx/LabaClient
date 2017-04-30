package Visual;

import Laba2.Person;

/**
 * Created by SlyFox on 30.04.2017.
 */
public class VisualPerson{
    private Person person;
    private Integer legIndex;
    VisualPerson(Person person)
    {
        this(person,0);
    }

    public Person getPerson(){return person;}
    public Integer getLegIndex(){return legIndex;}
    public void setLegIndex(Integer legIndex){this.legIndex = legIndex;}


    VisualPerson(Person person, Integer legIndex)
    {
        this.person = person;
        this.legIndex = legIndex;
    }
}
