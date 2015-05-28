package com.insoul.copartner.validate.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.insoul.copartner.validate.StringPatternValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = StringPatternValidator.class)
public @interface StringPattern {

    public static enum Flag {

        UNIX_LINES(java.util.regex.Pattern.UNIX_LINES),

        CASE_INSENSITIVE(java.util.regex.Pattern.CASE_INSENSITIVE),

        COMMENTS(java.util.regex.Pattern.COMMENTS),

        MULTILINE(java.util.regex.Pattern.MULTILINE),

        DOTALL(java.util.regex.Pattern.DOTALL),

        UNICODE_CASE(java.util.regex.Pattern.UNICODE_CASE),

        CANON_EQ(java.util.regex.Pattern.CANON_EQ);

        private final int value;

        private Flag(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface SList {
        StringPattern[] value();
    }

    Flag[] flags() default {};

    Class<?>[] groups() default {};

    String message() default "{javax.validation.constraints.Pattern.message}";

    Class<? extends Payload>[] payload() default {};

    String regexp();
}
