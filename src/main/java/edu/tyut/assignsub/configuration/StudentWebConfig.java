package edu.tyut.assignsub.configuration;

import edu.tyut.assignsub.interceptor.StudentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StudentWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StudentInterceptor())
                .addPathPatterns("/student")
                .addPathPatterns("/student/**");
    }
}
