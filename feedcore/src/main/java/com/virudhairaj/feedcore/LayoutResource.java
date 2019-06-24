package com.virudhairaj.feedcore;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface LayoutResource {
    @LayoutRes
    int resourceId();

    boolean attachToRoot() default false;
}
