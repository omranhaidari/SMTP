package core;

import database.Users;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

    private final int port;
    private final ServerSocket serverSocket;
    private final String address;
    private final Users users;

    public Server(int port, String address) throws IOException {
        this.port = port;
        this.address = address;
        this.serverSocket = new ServerSocket(port);

        this.users = Users.loadUsers(Utils.SERVERS_ROOT_PATH + address, address);
    }

    private String[] getAnonOnly(String[] suites) {
        List<String> resultList = new ArrayList<>();
        for (String s : suites) {
            if (s.contains("anon")) {
                resultList.add(s);
            }
        }
        return resultList.toArray(new String[resultList.size()]);
    }

    public Users getUsers() {
        return users;
    }

    @Override
    public void run() {
        try {
            System.out.println(String.format("Server \"%s\" started.\nAddress: %s.\nListening on port %d.",
                    address, InetAddress.getLocalHost(), port));
        } catch (UnknownHostException ex) {
            //ex.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Initialization for a connection.");
                Socket socket = serverSocket.accept();
                System.out.println("Connection accepted.");
                new Thread(new Connection(this, socket, address)).start();
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        }
    }
}
