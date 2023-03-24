package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        String city = "null";
        String message = "???";

        try (ServerSocket serverSocket = new ServerSocket(ServerConfig.PORT)) {
            System.out.println("Server start");

            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {

                    System.out.println("Подключен клиент " + clientSocket.getPort());
                    if (city.equals("null")) {
                        writer.println(message); //сообщение первому игроку
                        String newCity = input.readLine(); //ждем ход первого игрока
                        System.out.println("Введен город " + newCity);
                        city = newCity;
                        // break; //первый ход завершился
                    } else {
                        writer.println(city); //сообщение не первому
                        String newCity = input.readLine(); //ждем ход игрока
                        // если условия игры верчны
                        char firstLetter = newCity.charAt(0);
                        char lastLetter = city.charAt(city.length()-1);
                        if (firstLetter == lastLetter) {
                            writer.println("OK");
                            city = newCity;
                        } else {
                            writer.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

