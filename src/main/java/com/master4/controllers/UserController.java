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
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/auth")
public class UserController {
    @Autowired
    private UserService userService;

     @Autowired
    private RoleRepository roleRepository;

    @InitBinder
    private void customizeBinding (WebDataBinder binder) {
        binder.registerCustomEditor(String.class,"roles", new RoleFormater());
    }

    @GetMapping("/register")
    public String registration(@ModelAttribute("userForm") User user,Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("userForm", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult) throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        userService.save(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String login( @ModelAttribute("userForm") User user ,Model model,HttpServletRequest request) {
        model.addAttribute("user", user);
        Map<String ,String> messages =(Map<String, String>)  request.getSession().getAttribute("users");
        if (messages != null) {
            return "redirect:/auth/index";
        }
        return "auth/login";
    }

    @PostMapping("/sendlogin")
    public String sendlogin( @ModelAttribute("userForm") User user, BindingResult result, ModelMap model, HttpServletRequest request) throws ResourceNotFoundException {
        model.addAttribute("user", user);
        System.out.println(user);
        if(result.hasErrors()){
            return "auth/login";
        }
        User u = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        Map<String, Role> sessionData = new HashMap<>();
        sessionData.put(u.getUsername(),u.getRoles());
        request.getSession().invalidate();
        request.getSession().setAttribute("users", sessionData);
        return "redirect:/auth/index";
    }

    @GetMapping({"/index"})
    public String welcome(Model model , HttpSession session) {
        Map<String,Role> messages = (HashMap<String, Role>) session.getAttribute("users");
        if (messages == null) {
            return "redirect:/auth/login";
        }
        Map<String,String> sessionData =  new HashMap<>();
        messages.forEach((key, value) -> sessionData.put(key,((Role)value).getName()));
        model.addAttribute("session" , sessionData);
        return "auth/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/auth/index";
    }

}