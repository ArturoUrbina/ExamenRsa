package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize {
    private static FileOutputStream fo;
    private static ObjectOutputStream out;
    private static FileInputStream fi;
    private static ObjectInputStream in;

    private static void openStreams(String mode, String filename) {
        if ("out".equals(mode)) {
            try {
                fo = new FileOutputStream(filename);
                out = new ObjectOutputStream(fo);
            } catch (IOException e) {
            }
        } else if ("in".equals(mode)) {
            try {
                fi = new FileInputStream(filename);
                in = new ObjectInputStream(fi);
            } catch (IOException ex) {
            }
        }
    }

    private static void closeStreams(String mode) {
        if ("out".equals(mode)) {
            try {

                out.close();
                fo.close();
            } catch (IOException ex) {

            }
        } else if ("in".equals(mode)) {
            try {

                in.close();
                fi.close();
            } catch (IOException e) {
            }
        }
    }

    public static void saveConfig(String filename, PublicKey action) {
        try {
            openStreams("out", filename);
            out.writeObject(action);
            System.out.println("Saved: " + out.toString());
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            closeStreams("out");
        }
    }

    public static PublicKey getConfig(String filename) {
        PublicKey action = null;
        try {
            openStreams("in", filename);
            action = (PublicKey) in.readObject();
            System.out.println("Got: " + in.toString());
            
            return action;
        } catch (IOException | ClassNotFoundException e) {
            /*System.out.println("error");
            e.printStackTrace();*/
        } finally {
            closeStreams("in");
            return action;
        }
    }
}