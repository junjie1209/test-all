package com.test.annotation.validation;

import com.test.annotation.core.InetVersion;
import com.test.annotation.validator.InetPatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/11
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = InetPatternValidator.class)
public @interface InetPattern {

    InetVersion version() default InetVersion.V4;

    String pattern();

    String message() default "{com.test.annotation.validation.InetPattern.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
