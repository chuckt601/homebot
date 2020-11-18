import java.io.*;
import java.net.*;
 
/**
 * This program demonstrates a simple TCP/IP socket server that echoes every
 * message from the client in reversed form.
 * This server is single-threaded.
 *
 * @author www.codejava.net
 */
public class ReverseServer {
 
    public static void main(String[] args) {
        if (args.length < 1) return;
 
        int port = Integer.parseInt(args[0]);
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {     //initiate socket
     
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();             //block for socket connection
                System.out.println("New client connected");
 
                InputStream input = socket.getInputStream();        //create object to read from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(input)); //read object
 
                OutputStream output = socket.getOutputStream();    //create socket writing stream
                PrintWriter writer = new PrintWriter(output, true); //create writing object
 
 
                String text;
                int counterint=0;   
                do {
                    text = reader.readLine();
                    String reverseText = new StringBuilder(text).reverse().toString();
                    writer.print(counterint);
                    writer.println("Server: " + text); //reverseText);
                    counterint++;
                } while (!text.equals("bye"));
 
                socket.close();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
