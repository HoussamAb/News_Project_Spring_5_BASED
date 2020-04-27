package com.master4.controllers;


import com.master4.converter.RoleFormater;

import com.master4.entities.Role;
import com.master4.entities.User;
import com.master4.exceptions.ResourceNotFoundException;
import com.master4.repositories.RoleRepository;
import com.master4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/auth")
public class UserController {
    @Autowired
    private UserService userService;

     @Autowired
    private RoleRepository roleRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "roles",
                new RoleFormater(List.class));
    }

    @GetMapping("/register")
    public String registration(@ModelAttribute("userForm") User user,Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("userForm", new User());

        return "auth/register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult) throws ResourceNotFoundException {
       // userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        userService.save(user);

        // securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login( @ModelAttribute("userForm") User user ,Model model,HttpServletRequest request) {
        model.addAttribute("user", user);
        List<String> messages = (List<String>) request.getSession().getAttribute("users");
        if (messages != null) {
            return "redirect:/index";
        }
        return "auth/login";
    }
    @PostMapping("/sendlogin")
    public String sendlogin( @ModelAttribute("userForm") User user, BindingResult result, ModelMap model, HttpServletRequest request) throws ResourceNotFoundException {
        model.addAttribute("user", user);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());



        System.out.println(user);
        if(result.hasErrors()){
            return "auth/login";
        }

        User u = userService.findByUserUsername((String) model.getAttribute("username"));
        if(u != null){
            Map<String, Object> sessionData = new HashMap<>();
            sessionData.put(user.getUsername(),user.getRole());
            request.getSession().invalidate();
            request.getSession().setAttribute("users", sessionData);
            request.getSession().setAttribute("role", ((Role)u.getRole()).getName());
            return "auth/index";
        }
        return "redirect:/login";

    }

    @GetMapping({"/index"})
    public String welcome(Model model , HttpSession session) {
        Map<String,List> messages = (HashMap<String, List>) session.getAttribute("users");
        Map<String,String> sessionData =  new HashMap<>();
        messages.forEach((key, value) -> sessionData.put(key,transfer(value).getName()));
        model.addAttribute("session" , sessionData);
        model.addAttribute("role" , roleRepository.findAll());
        return "auth/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }

    public Role transfer(List<Role> roles){
        for (Role value : roles)
        {
         return value;
        }
        return null;
    }
}