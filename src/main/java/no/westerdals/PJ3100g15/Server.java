package no.westerdals.PJ3100g15;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Eva Dahloe on 27.10.2016.
 * Henrik Bakkelund edited a little.
 */
public class Server {
    public static void main(String[] args) {
            new Server().runServer();
        }

        public void runServer(){try (
                ServerSocket server = new ServerSocket(8080, 50)){
            //ExecutorService executor;
            int threadNumber = 0;
            //boolean done = false;

            while (true) {
                threadNumber++;
                Socket clientConnection = server.accept();
                //executor = Executors.newCachedThreadPool();
                //executor.execute(new ServerThread(clientConnection, threadNumber));
                ServerThread serverThread = new ServerThread(clientConnection, threadNumber);
                //if (threadNumber == 10) {//TODO: lage en metode som gjør at man kan skru av serveren
                    //executor.shutdown(); //Denne tilnærmingen gjør at serveren bare skrur seg av etterhvert.
                    //done = true;
                //}

                stopServer();

            }
        }catch(IOException e){
            System.out.println("Error!");
        }

            System.out.println("Server closing .....");

        }

        public boolean stopServer(){
            return false;
        }
    }

