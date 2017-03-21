package com.jee.tutorial.bookstore.jpa.validation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valid values are null and values from
 * <a href="http://www.isbn-international.org/en/manual.html">ISBN Specification</a>
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ISBNConstraintValidator.class)
@Documented
public @interface ISBNConstraint {
	String message() default "{com.jee.tutorial.bookstore.jpa.validation.ISBNConstraint.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};	
}
