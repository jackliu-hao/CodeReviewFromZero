package com.llu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpELApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpELApplication.class, args);
    }
}