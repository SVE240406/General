import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * Java Socket Programming - Multiple Clients Chat
 */
public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("New connection");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                closeServerSocket();
            }
        }
    }

    public void closeServerSocket(){
        try {
            if (serverSocket != null)
                serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
