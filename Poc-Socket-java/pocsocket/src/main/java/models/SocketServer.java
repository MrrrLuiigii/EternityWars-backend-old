package models;

import java.sql.Array;
import java.util.ArrayList;

public class SocketServer {


    String hostname;
    int port;
    public static ArrayList<Client> connectedClients;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ArrayList<Client> getConnectedClients() {
        return connectedClients;
    }

    public void setConnectedClients(ArrayList<Client> connectedClients) {
        this.connectedClients = connectedClients;
    }

    public SocketServer() {
        this.connectedClients = new ArrayList<Client>();
    }
}
