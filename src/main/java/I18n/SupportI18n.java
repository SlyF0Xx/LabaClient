package I18n;

import Laba2.Laba0;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleObjectProperty;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by SlyFox on 20.05.2017.
 */
public class SupportI18n {

    public static String get(final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle("I18n.StatsBundle", Laba0.getCurrentLocale());
        if (key == "Boolean") {
            return MessageFormat.format(bundle.getString(key), new Integer(args[0].toString()));
        }
        return MessageFormat.format(bundle.getString(key), args);
    }

    public static StringBinding createStringBinding(final String key, final Object... args)
    {
        return javafx.beans.binding.Bindings.createStringBinding(() -> get(key, args), new SimpleObjectProperty<Locale>(Laba0.getCurrentLocale()));
    }
}
