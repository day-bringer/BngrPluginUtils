package io.daybringer.annotations;

import java.lang.annotation.*;

/**
 * Indicates that overriding methods must call the superclass implementation.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface MustCallSuper
{
}
