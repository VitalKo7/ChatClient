package chat.client.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Socket socket = this.socket) {
            InputStream inputStream = socket.getInputStream();
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));

            while (true) {
                String response = socketReader.readLine();

                if (response != null) {
                    System.out.println(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}