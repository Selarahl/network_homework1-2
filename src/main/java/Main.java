import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8089;
        String welcomeMessage;

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

            out.println("Write your name");
            String name = in.readLine();

            out.println("Are you child? (yes/no)");
            String answer = in.readLine();
            if (answer.equals("yes")) {
                welcomeMessage = String.format("Welcome to the kids area, %s! Let's play!", name);
            } else {
                welcomeMessage = String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name);
            }
            out.println(welcomeMessage);
            String nameAndPortAnswer = String.format("%s, your port is %d", name, clientSocket.getPort());
            out.println(nameAndPortAnswer);
        }
    }
}
