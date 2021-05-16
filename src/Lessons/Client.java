package Lessons;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class Client {

    public static void main(String[] args) {

        Socket socket = null;

        try {
            socket = new Socket("localhost", 8149);

            Scanner in =  new Scanner(socket.getInputStream());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            new Thread(() -> {

                while (true) {
                    String serverMessage = in.nextLine();
                    System.out.println("Сервер: " + serverMessage);
                }
            }).start();
            System.out.println("Введите сообщение...");

            while (true) {


                String message = sc.nextLine();
                out.println(message);
//                System.out.println("Клиент: " + message);
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {

            try {
                if (socket != null) {
                    socket.close();

                }

            } catch (IOException ex) {
                ex.printStackTrace();


            }
        }

    }
}


