import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            // Create server socket on port 6666
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Server is waiting for client connection...");

            Socket socket = serverSocket.accept(); // Accept client connection
            System.out.println("Client connected.");

            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);

            String sendData = "";
            String receiveData = "";

            while (!receiveData.equalsIgnoreCase("stop")) {
                receiveData = din.readUTF();  // Read from client
                System.out.println("CLIENT SAYS: " + receiveData);

                System.out.print("TO CLIENT: ");
                sendData = input.nextLine();  // Input from server user
                dout.writeUTF(sendData);      // Send to client
                dout.flush();
            }

            // Close resources
            input.close();
            din.close();
            dout.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
