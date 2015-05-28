package com.insoul.copartner.validate;

import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import com.insoul.copartner.validate.constraint.StringPattern;

public final class StringPatternValidator implements ConstraintValidator<StringPattern, CharSequence> {

    private static Log log = LoggerFactory.make();

    private java.util.regex.Pattern pattern;

    @Override
    public void initialize(final StringPattern parameters) {
        final StringPattern.Flag[] flags = parameters.flags();
        int intFlag = 0;
        for (final StringPattern.Flag flag : flags) {
            intFlag = intFlag | flag.getValue();
        }

        try {
            pattern = java.util.regex.Pattern.compile(parameters.regexp(), intFlag);
        } catch (final PatternSyntaxException e) {
            throw log.getInvalidRegularExpressionException(e);
        }
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext constraintValidatorContext) {
        if ((value == null) || (value.length() == 0)) {
            return true;
        }
        final Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
