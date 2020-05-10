package com.master4.annotaion;


import com.master4.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserValidation implements ConstraintValidator <UniqueUser,String> {


    @Autowired
    private UserServiceImpl userService;

    @Override
    public void initialize(UniqueUser uniqueUser) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try
        {
            return userService.findByEmail(value)==null;
        } catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }
    }



}
