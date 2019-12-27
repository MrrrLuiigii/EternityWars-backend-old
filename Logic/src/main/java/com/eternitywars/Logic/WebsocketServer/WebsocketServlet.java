package com.eternitywars.Logic.WebsocketServer;


import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

    public class WebsocketServlet extends WebSocketServlet {
        @Override
        public void configure(WebSocketServletFactory webSocketServletFactory) {
            webSocketServletFactory.register(GameWebSocket.class);
        }
}
