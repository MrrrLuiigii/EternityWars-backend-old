package com.eternitywars.Logic.WebsocketServer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceBean implements ApplicationContextAware {
    private static ApplicationContext appCxt;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCxt = applicationContext;
    }

    public static WebsocketService getRandomNameService() throws BeansException {
        return (WebsocketService) appCxt.getAutowireCapableBeanFactory().getBean("WebsocketService");
    }
}
