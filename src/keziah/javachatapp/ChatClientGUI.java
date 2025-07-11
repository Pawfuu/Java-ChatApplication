
package keziah.javachatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatClientGUI extends JFrame{
    private JTextArea messageArea;
    private JTextField txtField;
    private ChatClient client;
    
    public ChatClientGUI(){
        super("Chat Application");
        setSize(400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Center the frame on the screen
        setLocationRelativeTo(null);
        
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);
        
        txtField = new JTextField();
        
        txtField.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               client.sendMessage(txtField.getText());
               txtField.setText("");
           }
         });
        add(txtField, BorderLayout.SOUTH);
        
        
        // Initialize and start the ChatClient 
       try {
          this.client = new ChatClient("127.0.0.1", 2000, this::onMessageReceived);
          client.startClient();
      } catch (IOException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(this, "Error connecting to the server", "Connection error",
                  JOptionPane.ERROR_MESSAGE);
          System.exit(1);
      }
        
    }
    
    private void onMessageReceived(String message){
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new ChatClientGUI().setVisible(true);
        });
    }
}
