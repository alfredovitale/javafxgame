package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import server.controller.Client;
import server.controller.ClientManager;

public class StartServer {
    public static ServerSocket serverSocket;
    public static boolean isServerRunning = true;

    public static void main(String[] args) {
        new StartServer();
    }

    public StartServer () {
        try {
            int PORT = 8888;

            // create server
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is listening on port: " + PORT);

            // init manager
            ClientManager clientManager = new ClientManager();

            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    1,
                    1,
                    1,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(1)
            );

            // main loop
            while (isServerRunning) {
                try {
                    // blocking accept connection listener
                    Socket s = serverSocket.accept();
                    System.out.println("got new connection: " + s);

                    // create new runnable object
                    Client client = new Client(s);
                    // add client to the client manager
                    clientManager.addClient(client);

                    // execute runnable object
                    executor.execute(client);
                } catch (IOException e) {
                    System.out.println("ERROR: StartServer#mainLoop#" + e.getMessage());
                }
            }


        } catch (IOException e) {
            System.out.println("ERROR: StartServer#" + e.getMessage());
        }
    }
}
