package com.lx.test.windows;

import java.io.*;
import java.net.*;
import java.util.Random;

/***
 * 当然可以使用 Java 编写联网游戏。Java 提供了丰富的网络编程 API，像 java.net 包中的类，借助它们就能方便地开发各种联网游戏。
 * 下面会以一个简单的基于 TCP 协议的双人猜数字联网游戏为例，为你展示 Java 是如何实现联网游戏的。
 * 这个游戏分为服务器端和客户端两部分，服务器端负责管理游戏逻辑并与两个客户端进行通信，客户端则是玩家进行游戏的界面。
 *
 * @param
 * @return
 * @date 2025/4/7 17:53
 **/
public class GuessNumberServer {
    private static final int PORT = 12345;
    private static final int MAX_NUMBER = 100;
    private static ServerSocket serverSocket;
    private static Socket player1;
    private static Socket player2;
    private static PrintWriter out1;
    private static PrintWriter out2;
    private static BufferedReader in1;
    private static BufferedReader in2;
    private static int numberToGuess;
    private static boolean player1Turn = true;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器已启动，等待玩家连接...");

            player1 = serverSocket.accept();
            System.out.println("玩家 1 已连接");
            out1 = new PrintWriter(player1.getOutputStream(), true);
            in1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            out1.println("你是玩家 1，等待玩家 2 连接...");

            player2 = serverSocket.accept();
            System.out.println("玩家 2 已连接");
            out2 = new PrintWriter(player2.getOutputStream(), true);
            in2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            out1.println("玩家 2 已连接，游戏开始！");
            out2.println("你是玩家 2，游戏开始！");

            Random random = new Random();
            numberToGuess = random.nextInt(MAX_NUMBER) + 1;
            System.out.println("要猜的数字是: " + numberToGuess);

            startGame();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) serverSocket.close();
                if (player1 != null) player1.close();
                if (player2 != null) player2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startGame() {
        try {
            while (true) {
                if (player1Turn) {
                    out1.println("轮到你猜数字了，请输入一个 1 到 " + MAX_NUMBER + " 之间的数字:");
                    String guess = in1.readLine();
                    int guessNumber = Integer.parseInt(guess);
                    checkGuess(guessNumber, out1, out2, 1);
                } else {
                    out2.println("轮到你猜数字了，请输入一个 1 到 " + MAX_NUMBER + " 之间的数字:");
                    String guess = in2.readLine();
                    int guessNumber = Integer.parseInt(guess);
                    checkGuess(guessNumber, out2, out1, 2);
                }
                player1Turn = !player1Turn;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkGuess(int guess, PrintWriter currentPlayerOut, PrintWriter otherPlayerOut, int playerNumber) {
        if (guess == numberToGuess) {
            currentPlayerOut.println("恭喜你，猜对了！你赢了！");
            otherPlayerOut.println("很遗憾，对方猜对了，你输了！");
            System.exit(0);
        } else if (guess < numberToGuess) {
            currentPlayerOut.println("猜的数字太小了，再试试。");
            otherPlayerOut.println("玩家 " + playerNumber + " 猜的数字太小了。");
        } else {
            currentPlayerOut.println("猜的数字太大了，再试试。");
            otherPlayerOut.println("玩家 " + playerNumber + " 猜的数字太大了。");
        }
    }
}