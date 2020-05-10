package com.master4.interceptors;

import com.master4.exception.NotAllowedMethodException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleInterceptor extends HandlerInterceptorAdapter {


    String[] writerallowedLinks = {"","","",""};
    String[] viewerallowedLinks = {"","","",""};

    @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception {
        /*RequestMapping rm = ((HandlerMethod) handler).getMethodAnnotation(
                RequestMapping.class);
        boolean alreadyLoggedIn = request.getSession()
                .getAttribute("user") != null;
        boolean loginPageRequested = rm != null && rm.value().length > 0
                && "login".equals(rm.value()[0]);

        if (alreadyLoggedIn && loginPageRequested) {
            response.sendRedirect(request.getRequestURI());
            return false;
        } else if (!alreadyLoggedIn && !loginPageRequested) {

            response.sendRedirect(request.getContextPath() + "/auth/login");
            return false;
        }*/

        System.out.println("==================================="+request.getContextPath()+
                "=================================="+request.getServletPath()+"====="+request.getPathInfo());


        switch (request.getSession().getAttribute("Role").toString())
        {
            case "visiter":
                if(!isallowed(viewerallowedLinks,request.getServletPath()))
                {
                    //    response.sendRedirect(request.getContextPath() + "notallowedServlet");
                    return  false;
                }
                break;
            case "admin":
                return true;


            case "writer":


                if(!isallowed(writerallowedLinks,request.getServletPath()))
                {
                    //    response.sendRedirect(request.getContextPath() + "notallowedServlet");
                    return  false;
                }
                break;
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

}

