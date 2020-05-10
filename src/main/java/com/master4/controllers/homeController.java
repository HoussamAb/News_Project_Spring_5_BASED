package com.master4.controllers;

import com.master4.annotaion.Admin;
import com.master4.annotaion.Writer;
import com.master4.entities.Article;
import com.master4.entities.Tag;
import com.master4.exceptions.ResourceNotFoundException;
import com.master4.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class homeController {
    @Autowired
    private ArticleService articleService;
    @GetMapping({"/","home"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model)
    {
        List<Article> pages = articleService.getAllArticles();
        model.addAttribute("articles", pages);
       for(Article a : pages){
        System.out.println(a.getId());
           System.out.println(a.getTitle());
           System.out.println(a.getBody());}
        return "home";
    }

    @Admin
    @Writer
    @GetMapping("/delete1/{id}")
    public String delete1(@PathVariable("id") long id, ModelMap model,HttpServletRequest request) throws ResourceNotFoundException {
        articleService.deleteById(id);
        return "redirect:/home";
    }

}
