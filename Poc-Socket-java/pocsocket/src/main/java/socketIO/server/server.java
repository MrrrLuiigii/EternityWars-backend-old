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

        //model for the server. holds all connecting clients
        SocketServer ss = new SocketServer();
        ss.setHostname("localhost");
        ss.setPort(8989);

        //creates server socket
        ServerSocket serverSocket = new ServerSocket(ss.getPort());

        Socket s;

        if(serverSocket != null){
            System.out.println("server running");
        }

        //accepts all in comming socket connections and add them to an client with an random generated id
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

            //checks of clients are still connected
            for (Client c : ss.connectedClients){
                System.out.println(c.getId() + "  Status : " + c.getSocket().isClosed());
            };
        }

    }
}
