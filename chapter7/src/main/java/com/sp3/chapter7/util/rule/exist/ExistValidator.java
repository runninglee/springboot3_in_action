package com.sp3.chapter7.util.rule.exist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ExistValidator implements ConstraintValidator<Exist, String> {

    private String table;
    private String field;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(Exist constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return (Long) entityManager.createNativeQuery("SELECT COUNT(*) FROM " + this.table + " WHERE " + this.field + "='" + value + "'").getSingleResult() > 0;
    }
}
