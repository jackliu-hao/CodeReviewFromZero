package com.llu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jackliu  Email:
 * @description:
 * @Version
 * @create 2024-07-11 21:27
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.llu.*"})
@MapperScan(basePackages = {"com.llu.**.mapper"})
public class SqlInjectApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SqlInjectApplicationStarter.class, args);
    }

}
