import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                // Server receives message
                if ((message = input.readLine()) != null) {
                    System.out.println("Client: " + message);
                    if (message.equalsIgnoreCase("quit")) {
                        output.println("Server: quit");
                        break;
                    }
                }

                // Server sends message
                System.out.print("Server: ");
                String response = userInput.readLine();
                output.println(response);
                if (response.equalsIgnoreCase("quit")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Server closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}