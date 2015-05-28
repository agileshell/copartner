package com.insoul.copartner.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import com.insoul.copartner.validate.constraint.StringLength;

public final class StringLengthValidator implements ConstraintValidator<StringLength, CharSequence> {

    private static Log log = LoggerFactory.make();

    private int max;

    private int min;

    @Override
    public void initialize(final StringLength slength) {
        max = slength.max();
        min = slength.min();
        validateParameters();
    }

    @Override
    public boolean isValid(final CharSequence validStr, final ConstraintValidatorContext constraintContext) {
        if ((validStr == null) || (validStr.length() == 0)) {
            return true;
        }
        final int length = validStr.length();
        return (length >= min) && (length <= max);
    }

    private void validateParameters() {
        if (min < 0) {
            throw log.getMinCannotBeNegativeException();
        }
        if (max < 0) {
            throw log.getMaxCannotBeNegativeException();
        }
        if (max < min) {
            throw log.getLengthCannotBeNegativeException();
        }
    }

}
