package com.eternitywars;

import com.eternitywars.Logic.WebsocketServer.Setup.ServiceBean;
import com.eternitywars.Logic.WebsocketServer.Setup.WebsocketServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ServletRegistrationBean socketServlet(){
        return new ServletRegistrationBean(new WebsocketServlet(), "/ws/");
    }

    @Bean
    public ServiceBean randomNameBeanUtil(){
        return new ServiceBean();
    }
}
