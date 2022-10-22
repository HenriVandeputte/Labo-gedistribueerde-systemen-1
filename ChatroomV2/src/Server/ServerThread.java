package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread extends  Thread{
    private Socket socket = null;
    private String username = null;
    private List<PrintWriter> PWList = null;

    public ServerThread(Socket socket, List<PrintWriter> printWriters) {
        super("ServerThread");
        this.socket = socket;
        this.username = null;
        this.PWList = printWriters;
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            username = in.readLine();
            out.println("Dag " + username + " welkom in deze chatruimte.\nAlles wat jij hier typt zal iedereen in deze chatruimte kunnen lezen. \nVeel plezier! \n");

            while ((inputLine = in.readLine()) != null) {
                for (PrintWriter pw : PWList) {
                    outputLine = inputLine;
                    pw.println(username + ": " + outputLine);
                    if (outputLine.equals("Bye"))
                        break;
                }

            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
