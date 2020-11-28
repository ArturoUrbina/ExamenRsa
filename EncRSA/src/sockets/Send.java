package sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;

public class Send {
    private Socket socket;
    
    public Send(Socket socket) {
        this.socket = socket;
    }

    public void SendMessage(BigInteger msg) {
        try {
            // Get output stream
            ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
            
            // Send message
            oos.writeObject(msg);
        } catch (IOException e) {}
    }
    
    public void Exit() throws IOException {
        this.socket.close();
    }
}
