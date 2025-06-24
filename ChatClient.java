import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            // Connect to server on localhost and port 6666
            Socket socket = new Socket("localhost", 6666);
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);

            String sendData = "";
            String receiveData = "";

            while (!sendData.equalsIgnoreCase("stop")) {
                System.out.print("TO SERVER: ");
                sendData = input.nextLine();

                dout.writeUTF(sendData);  // send message to server
                dout.flush();             // ensure it is sent

                receiveData = din.readUTF();  // receive reply from server
                System.out.println("SERVER SAYS: " + receiveData);
            }

            // Close resources
            input.close();
            din.close();
            dout.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
