package com.kiselev.instagram.model.annotation;

import com.kiselev.instagram.model.annotation.type.BusinessType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessValue {

    BusinessType value() default BusinessType.SKIP;
}
