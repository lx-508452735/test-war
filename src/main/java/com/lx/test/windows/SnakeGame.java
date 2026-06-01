package com.lx.test.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/***
 * 使用 Java Swing 实现的简单贪吃蛇游戏代码，具备图形
 *
 * 代码说明：
 * 游戏界面：使用JPanel和JFrame创建游戏窗口，大小为 300x300 像素。
 * 蛇的移动：蛇由ArrayList存储的一系列坐标表示，通过move方法实现移动。
 * 苹果生成：随机在游戏区域内生成苹果，蛇吃到苹果后会增长身体并得分。
 * 碰撞检测：检测蛇是否撞到自己或边界，若发生碰撞则游戏结束。
 * 用户交互：使用键盘的上下左右键控制蛇的移动方向。
 * 游戏循环：通过Timer实现游戏的定时更新，不断重绘界面。
 *
 * @param
 * @return
 * @date 2025/4/7 17:47
 **/
public class SnakeGame extends JPanel implements ActionListener {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int UNIT_SIZE = 10;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 75;
    private final ArrayList<Integer> x = new ArrayList<>();
    private final ArrayList<Integer> y = new ArrayList<>();
    private int applesEaten;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;

    public SnakeGame() {
        random = new Random();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        x.add(50);
        y.add(50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < x.size(); i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x.get(i), y.get(i), UNIT_SIZE, UNIT_SIZE);
            }
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = x.size() - 1; i > 0; i--) {
            x.set(i, x.get(i - 1));
            y.set(i, y.get(i - 1));
        }

        switch (direction) {
            case 'U':
                y.set(0, y.get(0) - UNIT_SIZE);
                break;
            case 'D':
                y.set(0, y.get(0) + UNIT_SIZE);
                break;
            case 'L':
                x.set(0, x.get(0) - UNIT_SIZE);
                break;
            case 'R':
                x.set(0, x.get(0) + UNIT_SIZE);
                break;
        }
    }

    public void checkApple() {
        if ((x.get(0) == appleX) && (y.get(0) == appleY)) {
            applesEaten++;
            newApple();
            x.add(0);
            y.add(0);
        }
    }

    public void checkCollisions() {
        // 检查是否撞到自己
        for (int i = x.size() - 1; i > 0; i--) {
            if ((x.get(0) == x.get(i)) && (y.get(0) == y.get(i))) {
                running = false;
            }
        }
        // 检查是否撞到边界
        if (x.get(0) < 0) {
            running = false;
        }
        if (x.get(0) >= WIDTH) {
            running = false;
        }
        if (y.get(0) < 0) {
            running = false;
        }
        if (y.get(0) >= HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - metrics2.stringWidth("Game Over")) / 2, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            SnakeGame gamePanel = new SnakeGame();
            frame.add(gamePanel);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}    