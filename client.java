import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

 class TimeClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Server IP and port number
            InputStream inputStream = socket.getInputStream();

            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            if (bytesRead > 0) {
                String dateTime = new String(buffer, 0, bytesRead);
                System.out.println("Server date and time: " + dateTime);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
