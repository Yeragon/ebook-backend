package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration.class
})
@MapperScan(basePackages = "org.example")
public class EbookBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbookBackendApplication.class, args);
    }
}