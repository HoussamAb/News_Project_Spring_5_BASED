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

    String[] writerallowedLinks = {"/article/add","/article/","/article/view","/article/save","/tags/add", "/tags/page/*","/auth/index","/auth/login","/auth/logout"};
    String[] visiterallowedLinks = {"/article/","/auth/index","/auth/login","/auth/logout" ,"/article/view/*" , "/tags/"};

    String Role;
    @Override
    public boolean preHandle (HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        Map<String, Role> users = (HashMap<String, Role>) request.getSession().getAttribute("users");
        if(users != null){
            getMapData(users);
            switch (Role)
            {   case "visiter":
                    if(!isallowed(visiterallowedLinks,request.getServletPath()))
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
                    if(!isallowed(writerallowedLinks,request.getServletPath())){
                        request.setAttribute("errorMessage","Alert ! You're not allowed to access this area.");
                        request.getRequestDispatcher("/WEB-INF/views/erreurAuth.jsp").forward(request,response);
                        return  false;                    }
                    break;
            }
        }        return true;
    }

    boolean isallowed(String[] liste,String url)
    {

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

