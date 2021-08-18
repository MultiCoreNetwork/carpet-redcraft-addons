package it.multicoredev.carpetredcraftaddons.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TagsRule {
    String name() default "";

    String[] tags() default "";

    String tagNamespace() default "carpet-redcraft-addons";

    String tagsFolder() default "";
}