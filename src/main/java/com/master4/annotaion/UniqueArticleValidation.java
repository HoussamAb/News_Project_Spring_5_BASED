package com.master4.annotaion;


import com.master4.services.ArticleServiceImpl;
import com.master4.services.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueArticleValidation implements ConstraintValidator <UniqueArticle,String> {


    @Autowired
    private ArticleServiceImpl articleService;

    @Override
    public void initialize(UniqueArticle uniqueArticle) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try
        {
            return articleService.findByTitle(value)==null;
        } catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }
    }



}
