import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to server");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                // Client sends message
                System.out.print("Client: ");
                message = userInput.readLine();
                output.println(message);
                if (message.equalsIgnoreCase("quit")) {
                    break;
                }

                // Client receives message
                if ((message = input.readLine()) != null) {
                    System.out.println("Server: " + message);
                    if (message.equalsIgnoreCase("quit")) {
                        break;
                    }
                }
            }

            socket.close();
            System.out.println("Client closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}