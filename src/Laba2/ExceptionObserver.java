package Laba2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by SlyFox on 12.04.2017.
 */
public class ExceptionObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Да ладно?");
    }
}
