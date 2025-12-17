package com.onofre.store.core.validations.ExistsInCollection;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ExistsInCollectionValidator implements ConstraintValidator<ExistsInCollection, String> {

    @Autowired
    private MongoTemplate template;
    private String collection;
    private String getBy;

    @Override
    public void initialize(ExistsInCollection annotation) {
        this.collection = annotation.collection();
        this.getBy = annotation.getBy();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true;
        return template.exists(Query.query(Criteria.where(getBy).is(value)), Object.class, collection);
    }
}
