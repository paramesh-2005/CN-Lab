import java.net.*; 

class Server { 
    public static void main(String args[]) { 
        try { 
            DatagramSocket server = new DatagramSocket(1309); 
            System.out.println("Server is running...");

            while (true) { 
                byte[] receivebyte = new byte[1024]; 
                DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length); 
                server.receive(receiver);

                String receivedStr = new String(receiver.getData(), 0, receiver.getLength()).trim(); 
                InetAddress addr = receiver.getAddress(); 
                int port = receiver.getPort(); 

                String[] ip = { "165.165.80.80", "165.165.79.1" }; 
                String[] name = { "www.aptitudeguru.com", "www.downloadcyclone.blogspot.com" }; 

                String response = "Not found";
                for (int i = 0; i < ip.length; i++) { 
                    if (receivedStr.equals(ip[i])) { 
                        response = name[i]; 
                        break; 
                    } else if (receivedStr.equals(name[i])) { 
                        response = ip[i]; 
                        break; 
                    } 
                }

                byte[] sendbyte = response.getBytes(); 
                DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, port); 
                server.send(sender); 
            } 
        } catch (Exception e) { 
            System.out.println("Error: " + e.getMessage()); 
        } 
    } 
}
