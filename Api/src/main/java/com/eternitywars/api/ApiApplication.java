package com.eternitywars.api;

import com.eternitywars.api.DAL.Contexts.UserMySQLContext;
import com.eternitywars.api.Database.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(ApiApplication.class, args);


//        DatabaseConnection dbc = new DatabaseConnection();
//        UserMySQLContext yeet = new UserMySQLContext();
//
//        yeet.GetUserById(1);
//
//        System.out.println(yeet);
    }
}
