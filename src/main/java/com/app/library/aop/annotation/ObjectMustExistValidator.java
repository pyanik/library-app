package com.app.library.aop.annotation;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface ObjectMustExistValidator {

    Class<?> serviceClass();

    String methodName();


    /**
     * Requires to take first (by default). In the other hand will take the argument based on index
     * @return declared UUID as one of method argument where annotation is applied
     */
    String idPropertyName() default StringUtils.EMPTY;
}
