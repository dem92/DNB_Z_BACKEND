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
        try (
            ServerSocket server = new ServerSocket(8000, 50)){
                ExecutorService executor;
                int threadNumber = 0;
                boolean done = false;

                while (!done) {
                    threadNumber++;
                    Socket clientConnection = server.accept();
                    executor = Executors.newCachedThreadPool();
                    executor.execute(new ServerThread(clientConnection, threadNumber));
                    if (threadNumber == 10) {
                        executor.shutdown();
                        done = true;
                    }
                }
            }catch(IOException e){
                System.out.println("Error!");
            }

            System.out.println("Server closing .....");
        }
    }

