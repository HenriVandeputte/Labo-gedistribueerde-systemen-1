package Opdracht;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {

        int portNumber = 4444;
        boolean listening = true;
        List<PrintWriter> PWList = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                Socket newSocket = serverSocket.accept();
                PrintWriter new_out = new PrintWriter(newSocket.getOutputStream(), true);
                PWList.add(new_out);
                System.out.println("A new client has been connected!");
                System.out.println(newSocket);
                new ServerThread(newSocket,PWList).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
