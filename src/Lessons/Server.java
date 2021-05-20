package Lessons;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {


    public static void main(String[] args) {
        Server server = new Server();
    }

    public Server() {

//        ServerSocket server = null;
        Socket socket = null;

        try (ServerSocket server = new ServerSocket(8149)) {

//            server = new ServerSocket(3443);
            System.out.println("Сервер запущен, ожидание подключения...");

            socket = server.accept();
            System.out.println("Клиент подключился!");

            Scanner messageIn =  new Scanner(socket.getInputStream());

            PrintWriter messageOut = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);


            new Thread(() -> {

                System.out.println("Введите сообщение");
                while (true) {

                    String message = scanner.nextLine();
                    messageOut.println(message);
//                    System.out.println("Сервер: " + message);
                }
            }).start();

            while (true) {
                String clientMessage = messageIn.nextLine();
                if (clientMessage.equals("/end"))
                    break;
                    System.out.println("Клиент: " + clientMessage);
                }


        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("Соединение закрыто");


                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }



    }
}
