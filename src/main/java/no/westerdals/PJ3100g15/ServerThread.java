package no.westerdals.PJ3100g15;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Eva Dahloe on 27.10.2016.
 * Edited a little by Henrik Bakkelund
 */

public class ServerThread implements Runnable {
    private Socket clientConnection;
    private int number;

    public ServerThread(Socket clientConnection, int number){
        this.clientConnection = clientConnection;
        this.number = number;
    }

    public void run(){
        try(DataOutputStream output = new DataOutputStream(clientConnection.getOutputStream());
            DataInputStream input = new DataInputStream(clientConnection.getInputStream())){
            output.flush();

            String message = "Thread number " + number + ", welcome to server!";
            System.out.println("Server: " + message);
            output.writeUTF(message);
            output.flush();
            String data;

            do {
                data = input.readUTF();
                System.out.println("Thread number " + number + ": " + data);
            } while (!data.equals("done"));
        } catch (Exception e){
            System.out.println("An error occurred in ServerThread!");
        }
    }
}
