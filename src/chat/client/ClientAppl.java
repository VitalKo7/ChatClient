package chat.client;

import chat.client.task.MsgWriter;
import chat.client.task.ServerHandler;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientAppl {
    public static void main(String[] args) {
        // print &
        // send msg 2 server

        // & waits for the reply from server all the time
        // get all msgs from server - all the time

        String serverHost = "127.0.0.1";
        int port = 9000;

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try (Socket socket = new Socket(serverHost, port)) {
/*            ServerHandler serverHandler = new ServerHandler(socket);
            MsgWriter msgWriter = new MsgWriter(socket);
            
            Thread[] threads = new Thread[2];
//            threads[0] = serverHandler;
//            threads[1] = msgWriter;

            for (int i = 0; i < threads.length; i++) {
                threads[i].setDaemon(true);
                threads[i].start();
                threads[i].join();
            }*/

            try {
                ServerHandler serverHandler = new ServerHandler(socket);
                executorService.execute(serverHandler);  // serverHandler.setDaemon(true);

                MsgWriter msgWriter = new MsgWriter(socket);
                executorService.execute(msgWriter);
            } finally {
                executorService.shutdown();
                executorService.awaitTermination(1, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}