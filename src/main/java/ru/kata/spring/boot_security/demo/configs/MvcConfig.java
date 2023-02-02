package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kata.spring.boot_security.demo.service.UserService;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
/*

       String userUrl ="/user/";// + userService.findIdByUsername(aut.getName());
        registry.addViewController(userUrl).setViewName("user");
        registry.addViewController("/admin").setViewName("admin");

    }*/
}
