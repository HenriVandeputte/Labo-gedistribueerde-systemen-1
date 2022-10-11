package Opdracht;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class InputThread extends Thread{
        private Socket socket = null;
        private JTextArea chatbox = null;

        public InputThread(Socket socket, JTextArea chatbox) {
            this.socket = socket;
            this.chatbox = chatbox;
        }

        public void run() {

            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
                String message;
                int i = 1;
                while (i<1000){

                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                        chatbox.append(message + "\n");

                    }
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


