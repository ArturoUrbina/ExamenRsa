package sockets;

import encrsa.RSA;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class ServerProcess extends Thread {
    private int port = 239;
    private JTextArea text;
    private RSA rsa;
    
    public ServerProcess(JTextArea text, RSA rsa) {
        this.text = text;
        this.rsa = rsa;
        start();
    }
    
    @Override
    public void run() {
        try {
            // Create server socket
            ServerSocket ss = new ServerSocket(this.port);
            System.out.println("Running server on port " + this.port);
            
            // Create client socket
            Socket socket;
            
            // Wait for connections
            System.out.println("Waiting for connections...");
            socket = ss.accept();
            System.out.println("User connected!");
            
            while (true) {
                // Get input stream
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                
                // Receive message
                BigInteger encryptedMessage = (BigInteger) ois.readObject();
                
                // Decrypt message
                BigInteger msg = rsa.decrypt(encryptedMessage);
                
                // Show message
                text.setText(msg + "");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while running server");
        }
    }
}
