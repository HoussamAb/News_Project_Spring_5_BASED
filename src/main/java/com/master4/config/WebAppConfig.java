package com.master4.config;

import com.master4.converter.RoleFormater;
import com.master4.interceptors.RoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.master4" })
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolvers =new InternalResourceViewResolver();
        resolvers.setViewClass(JstlView.class);
        resolvers.setPrefix("/WEB-INF/views/");
        resolvers.setSuffix(".jsp");
        return resolvers;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RoleInterceptor())
                .addPathPatterns(new String[]{"/**","/article/*","/tag/*","/user/*"})
                .excludePathPatterns("/auth/**");
    }
*/
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new RoleFormater());
    }
}
