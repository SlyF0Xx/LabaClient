package ORM;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by SlyFox on 24.05.2017.
 */

@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Atribute {
    public String name();
    public String type();
    boolean isPrimaryKey() default false;
    String Reference() default "";
}
