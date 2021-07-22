package edu.tyut.assignsub.configuration;

import edu.tyut.assignsub.interceptor.TeacherInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TeacherWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TeacherInterceptor())
                .addPathPatterns("/teacher")
                .addPathPatterns("/teacher/**");
    }
}
