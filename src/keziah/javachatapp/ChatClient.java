
package keziah.javachatapp;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class ChatClient {
    private Socket socket = null;
    private BufferedReader inputConsole = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    
    public ChatClient(String address, int port){
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to the chat server");
            inputConsole = new BufferedReader(new InputStreamReader(System.in)); // reads out the characters input into byte
            out = new PrintWriter(socket.getOutputStream(), true); // autoflush
            
            String line = "";
            while(!line.equals("exit")){
                line = inputConsole.readLine();
                out.print(line);
                System.out.println(in.readLine());
            }
            
            socket.close();
            inputConsole.close();
            out.close();
        } catch (UnknownHostException u) {
            System.out.println("Host unknown: " + u.getMessage());
        } catch(IOException e){
            System.out.println("Unexpected exception: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        ChatClient client = new ChatClient("127.0.0.1", 2000);
    }

    void sendMessage(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
