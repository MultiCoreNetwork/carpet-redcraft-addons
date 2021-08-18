package it.multicoredev.carpetredcraftaddons.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LootTableRule {
    String name() default "";

    String[] loottables() default "";

    String loottableNamespace() default "carpet-redcraft-addons";

    String loottablesFolder() default "";
}