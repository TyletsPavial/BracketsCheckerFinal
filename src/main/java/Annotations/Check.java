package Annotations;

import java.lang.annotation.*;
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Check {

}
