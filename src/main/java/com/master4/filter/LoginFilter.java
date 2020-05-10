package com.master4.filter;

import com.master4.entities.Role;
import com.master4.entities.User;
import com.master4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends HttpFilter {
    @Autowired
    private UserService u;

    private User user;
    String Role;
    String[] writerallowedLinks = {"/article/add","/article/","/article/view","/article/save","/tags/add", "/tags/page/*","/auth/index","/auth/login","/auth/logout"};
    String[] visiterallowedLinks = {"/article/","/auth/index","/auth/login","/auth/logout" ,"/article/view/*" , "/tags/"};

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("============== test filter ========");
        Map<String, Role> users = (HashMap<String, Role>) servletRequest.getSession().getAttribute("users");
        if(isLoguedIn(users)){
            if(users != null){
                getMapData(users);
                switch (Role)
                {
                    case "visiter":
                        if(isallowed(visiterallowedLinks,servletRequest.getServletPath()))
                        {
                            filterChain.doFilter(servletRequest, servletResponse);
                        }else{
                            System.out.println("i'm here !!");
                            try {
                                servletRequest.setAttribute("errorMessage","Alert ! You're not allowed to access this area.");
                                servletRequest.getRequestDispatcher("/WEB-INF/views/erreurAuth.jsp").forward(servletRequest,servletResponse);
                                // filterChain.doFilter(servletRequest, servletResponse);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                return;
                            }
                        }
                        break;
                    case "admin":
                        filterChain.doFilter(servletRequest, servletResponse);
                        break;
                    case "writer":
                        if(isallowed(writerallowedLinks,servletRequest.getServletPath()))
                        {
                            filterChain.doFilter(servletRequest, servletResponse);
                        }else{
                            System.out.println("i'm here !!");
                            try {
                                servletRequest.setAttribute("errorMessage","Alert ! You're not allowed to access this area.");
                                servletRequest.getRequestDispatcher("/WEB-INF/views/erreurAuth.jsp").forward(servletRequest,servletResponse);
                                // filterChain.doFilter(servletRequest, servletResponse);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                return;
                            }
                        }
                        break;
                }
            }

        }else{
            System.out.println("i'm going to login page ! :)");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
    }

    private boolean isLoguedIn(Map users) {
        try {
            if (users != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    boolean isallowed(String[] liste,String url) {

        for (String link:liste) {
            if(url.equals(link)==true)
            {
                return true;
            }
        }
        return false;
    }

    void getMapData(Map<String,Role> users){
        for (Map.Entry<String,Role> entry : users.entrySet()) {
            Role = entry.getValue().getName();
        }
    }
}
