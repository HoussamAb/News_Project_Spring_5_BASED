package com.master4.annotaion;


import com.master4.services.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTagValidation implements ConstraintValidator <UniqueTag,String> {


    @Autowired
    private TagServiceImpl tagService;

    @Override
    public void initialize(UniqueTag uniqueTag) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try
        {
            return tagService.findByTitle(value)==null;
        } catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }
    }



}
