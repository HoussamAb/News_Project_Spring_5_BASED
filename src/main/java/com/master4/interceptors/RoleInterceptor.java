package com.master4.interceptors;

import com.master4.entities.Role;
import com.master4.exception.NotAllowedMethodException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RoleInterceptor extends HandlerInterceptorAdapter {


    String[] writerallowedLinks = {"","","",""};
    String[] viewerallowedLinks = {"/article/","","",""};
    String Role;
    @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception {
        /*RequestMapping rm = ((HandlerMethod) handler).getMethodAnnotation(
                RequestMapping.class);
        boolean alreadyLoggedIn = request.getSession()
                .getAttribute("users") != null;
        boolean loginPageRequested = rm != null && rm.value().length > 0
                && "login".equals(rm.value()[0]);

        if (alreadyLoggedIn && loginPageRequested) {
            response.sendRedirect(request.getRequestURI());
            return false;
        } else if (!alreadyLoggedIn && !loginPageRequested) {

            response.sendRedirect(request.getContextPath() + "/auth/login");
            return false;
        }*/
        Map<String, Role> users = (HashMap<String, Role>) request.getSession().getAttribute("users");
        if(users != null){
            getMapData(users);


            System.out.println("Interceptor ==================================="+request.getContextPath()+
                    "=================================="+request.getServletPath()+"====="+request.getPathInfo());



            switch (Role)
            {
                case "visiter":
                    if(!isallowed(viewerallowedLinks,request.getServletPath()))
                    {
                        request.setAttribute("errorMessage","Alert ! You're not allowed to access this area.");
                        request.getRequestDispatcher("/WEB-INF/views/erreurAuth.jsp").forward(request,response);
                        //request.getRequestDispatcher("/WEB-INF/views/erreurAuth.jsp").forward(request,response);
                        return  false;
                    }
                    break;
                case "admin":
                    return true;


                case "writer":


                    if(!isallowed(writerallowedLinks,request.getServletPath()))
                    {
                        request.setAttribute("errorMessage","Alert ! You're not allowed to access this area.");
                        request.getRequestDispatcher("/WEB-INF/views/erreurAuth.jsp").forward(request,response);
                        return  false;
                    }
                    break;
            }
        }


        return true;
    }

    boolean isallowed(String[] liste,String url)
    {

        for (String link:liste) {
            if(url.compareTo(link)!=0)
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

