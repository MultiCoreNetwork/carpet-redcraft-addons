package it.multicoredev.carpetredcraftaddons.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TagRule {
    String[] overrideTags() default "";

    String[] tags() default "";
}