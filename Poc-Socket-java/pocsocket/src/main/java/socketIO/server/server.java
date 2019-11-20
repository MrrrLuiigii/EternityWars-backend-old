package socketIO.server;

import models.Client;
import models.SocketServer;
import models.connection;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class server {

    public static void main(String[] args) throws IOException {


        SocketServer ss = new SocketServer();
        ss.setHostname("localhost");
        ss.setPort(8989);

        ServerSocket serverSocket = new ServerSocket(ss.getPort());
        Socket s;

        if(serverSocket != null){
            System.out.println("server running");
        }
        while(true){
            s = serverSocket.accept();
            if(s != null){
                Client client = new Client();
                client.setSocket(s);
                client.setId(UUID.randomUUID().toString());
                ss.connectedClients.add(client);
                System.out.println("connected");
                s = null;
            }

            for (Client c : ss.connectedClients){
                System.out.println(c.getId() + "  Status : " + c.getSocket().isClosed());
            };
        }

    }
}
