package com.insoul.copartner.validate;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.insoul.copartner.validate.constraint.NumberMax;

public final class NumberMaxValidator implements ConstraintValidator<NumberMax, CharSequence> {

    private BigDecimal maxValue;

    @Override
    public void initialize(final NumberMax maxValue) {
        this.maxValue = BigDecimal.valueOf(maxValue.value());
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        try {
            return new BigDecimal(value.toString()).compareTo(maxValue) != 1;
        } catch (final NumberFormatException nfe) {
            return false;
        }
    }
}
