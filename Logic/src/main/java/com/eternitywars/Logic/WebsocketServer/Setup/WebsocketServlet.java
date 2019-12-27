package com.eternitywars.Logic.WebsocketServer.Setup;


import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

    public class WebsocketServlet extends WebSocketServlet {
        @Override
        public void configure(WebSocketServletFactory webSocketServletFactory) {
            webSocketServletFactory.register(WebSocketJetty.class);
        }
}
