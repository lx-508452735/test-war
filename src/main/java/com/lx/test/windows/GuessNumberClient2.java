package com.lx.test.windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GuessNumberClient2 {
    private static final String SERVER_ADDRESS = "10.60.60.222";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
                if (message.startsWith("轮到你猜数字了")) {
                    int guess = scanner.nextInt();
                    out.println(guess);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}