package com.insoul.copartner.validate;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.insoul.copartner.validate.constraint.NumberMin;

public final class NumberMinValidator implements ConstraintValidator<NumberMin, CharSequence> {

    private BigDecimal minValue;

    @Override
    public void initialize(final NumberMin minValue) {
        this.minValue = BigDecimal.valueOf(minValue.value());
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        try {
            return new BigDecimal(value.toString()).compareTo(minValue) != -1;
        } catch (final NumberFormatException nfe) {
            return false;
        }
    }
}
