package chat.client.task;

import java.io.*;
import java.net.Socket;

public class MsgWriter implements Runnable {
    private Socket socket;
    private String userName;
    public MsgWriter(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try (Socket socket = this.socket) {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter your name, please: ");
            userName = br.readLine();
            if (userName == null || userName.equals("")) {
                userName = "YOU";
//                return;
            }
            System.out.println("userName: " + userName);

//            System.out.println("\nEnter your message or type 'e' for exit");
            System.out.println(userName + ", please, enter your message or type 'e' for exit");
            String message = br.readLine();

            while (!"e".equalsIgnoreCase(message)) {
//                socketWriter.println(message);
                socketWriter.println(userName + " wrote: " + message);  // ? not sure
                socketWriter.flush();

//                System.out.println("Enter your message or type 'e' for exit");
                System.out.println(userName + ", please, enter your message or type 'e' for exit");

                message = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}