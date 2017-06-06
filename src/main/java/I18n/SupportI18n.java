package I18n;

import Laba2.Laba0;
import Visual.Controller;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleObjectProperty;

import java.text.Collator;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by SlyFox on 20.05.2017.
 */
public class SupportI18n {

    public static String get(final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle("I18n.StatsBundle", Laba0.getCurrentLocale());
        if (key.equals("Boolean") || key.equals("LegSizeEnum"))
        {
            return MessageFormat.format(bundle.getString(key), new Integer(args[0].toString()));
        }
        return MessageFormat.format(bundle.getString(key), args);
    }

    public static Boolean parseBoolean(final Object... args)
    {
        ResourceBundle bundle = ResourceBundle.getBundle("I18n.StatsBundle", Laba0.getCurrentLocale());
        Collator collator = Collator.getInstance(Laba0.getCurrentLocale());
        return collator.compare(args[0], bundle.getString("true")) == 0? true: false;
    }

    public static Enum parseEnum(Class<? extends Enum>enumClass, final Object... args)
    {
        ResourceBundle bundle = ResourceBundle.getBundle("I18n.StatsBundle", Laba0.getCurrentLocale());
        Collator collator = Collator.getInstance(Laba0.getCurrentLocale());

        Enum[] enums = enumClass.getEnumConstants();
        for(int i = 0; i<enums.length;i++)
        {
            if(MessageFormat.format(bundle.getString(enums[i].name()), null).equals(args[0]))
            {
                return enums[i];
            }
        }
        Controller.InitAlert("CannotCreateObject");
        return enums[0];
    }

    public static StringBinding createStringBinding(final String key, final Object... args)
    {
        return javafx.beans.binding.Bindings.createStringBinding(() -> get(key, args), new SimpleObjectProperty<Locale>(Laba0.getCurrentLocale()));
    }
}
