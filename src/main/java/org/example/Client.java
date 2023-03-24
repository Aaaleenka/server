package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static org.example.ServerConfig.HOST;
import static org.example.ServerConfig.PORT;

public class Client {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            String input = reader.readLine();
            System.out.println("Сообщение от сервера: " + input);
            if (input.equals("???")) {
                //игрок первый
                //может назвать любой город. Назвать = отправить город в виде одной строки на сервер,
                System.out.println("Назовите любой город");
                String cityName = scanner.nextLine();
                writer.println(cityName); // передаем серверу
            } else {
                System.out.println("Введите город на последюю букву предыдущего: " + input);
                String cityName = scanner.nextLine();
                writer.println(cityName);
                System.out.println("Ответ: " + reader.readLine());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
