package com.donren.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


//@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.donren.springboot"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
@PropertySource("classpath:application.yml")
public class SpringBootLibraryApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLibraryApp.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<SpringBootLibraryApp> applicationClass = SpringBootLibraryApp.class;
}
