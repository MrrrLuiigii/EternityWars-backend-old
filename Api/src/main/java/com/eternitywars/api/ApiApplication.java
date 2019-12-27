package com.eternitywars.api;

import com.eternitywars.api.Database.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApiApplication
{
    @Bean
    public DatabaseConnection getDatabaseConnection()
    {
        return new DatabaseConnection();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(ApiApplication.class, args);
    }
}
