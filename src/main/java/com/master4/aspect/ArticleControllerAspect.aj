package com.master4.aspect;

import com.master4.annotaion.Admin;
import com.master4.annotaion.Vister;
import com.master4.annotaion.Writer;
import com.master4.controllers.ArticleController;
import com.master4.exception.NotAllowedMethodException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class ArticleControllerAspect {

    @Before("execution(* com.master4.controllers.ArticleController(..)) && args(request,..)")
    public void logBeforeV1(JoinPoint joinPoint, ProceedingJoinPoint jpj, HttpServletRequest request) throws NotAllowedMethodException {


        Class c = ArticleController.class;

            // get method name getCustomAnnotation as Method object
            Method[] methods = c.getMethods();
            Method method = null;
            for (Method m : methods) {
                if (m.getName().equals(joinPoint.getSignature().getName()))
                    method = m;
            }

            // get Annotation of Method object m by passing
            // Annotation class object as parameter
        boolean isAdim=false,isWriter=false,isVister=false;
            Annotation[] annos = method.getAnnotations();
        for (Object ano:annos) {
            if (ano instanceof Admin)
            {
                isAdim =true;
            }
            if (ano instanceof Writer)
            {
                isWriter =true;
            }
            if (ano instanceof Vister)
            {
                isVister=true;
            }


        }

        switch (request.getSession().getAttribute("Role").toString())
        {
            case "visiter":
                if(isVister==false)
                {
                    throw new NotAllowedMethodException();
                }
                break;
            case "admin":
                if(isAdim==false)
                {
                    throw new NotAllowedMethodException();
                }
                break;
            case "writer":
                if(isWriter==false)
                {
                    throw new NotAllowedMethodException();
                }
                break;
        }

  /*          // print Annotation Details
            System.out.println("Annotation for Method Object"
                               + " having name: " + method.getName());
            System.out.println("Key Attribute of Annotation: "
                               + anno.key());
            System.out.println("Value Attribute of Annotation: "
                               + anno.value()); */

    }

}
