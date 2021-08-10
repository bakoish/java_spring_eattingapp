package com.eattingapp.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/page").setViewName("page");
        registry.addViewController("/").setViewName("page");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/personaladressedit").setViewName("personaladressedit");
        registry.addViewController("/navbar").setViewName("navbar");
        // DOREJESTRACJI
        registry.addViewController("/registration").setViewName("registration");
        // *************
        registry.addViewController("/error404").setViewName("error404");
        //registry.addViewController("/error").setViewName("error");
    }

}