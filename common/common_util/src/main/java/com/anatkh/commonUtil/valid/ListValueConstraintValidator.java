package com.anatkh.commonUtil.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;

public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {

    HashSet<Integer> set = new HashSet<>();
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        Arrays.stream(vals).forEach(item->{
            set.add(item);
        });
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return set.contains(value);
    }
}
