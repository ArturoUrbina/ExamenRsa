package sockets;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

public class UserProcess {
    private String ipAddress = "127.0.0.1";
    private int port = 239;
    private Socket s;
    private Send send;
    private boolean connected = false;
    
    public UserProcess() {
        while (!this.connected) {
            try {
                // Connect to server
                this.s = new Socket(ipAddress, port);
                
                // Create send object
                send = new Send(this.s);
                
                // Set connected flag on true
                this.connected = true;
            } catch (IOException e) {
                // Can't connect to server
                System.out.println("Can't connect to " + ipAddress + ":" + port);
                // Exit
                System.exit(1);
            }
        }
    }
    
    public void SendMessage(BigInteger msg) {
        this.send.SendMessage(msg);
    }
    
    public void Exit() {
        try {
            this.send.Exit();
        } catch (IOException e) {
            System.out.println("Unnable to exit properly");
        }
    }
}
