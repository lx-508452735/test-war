package com.lx.test.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/***
 * 下面为你生成一个使用 Java Swing 实现的简单的井字棋游戏代码，它具备图形用户界面。
 *
 * 这个程序的主要功能和逻辑如下：
 * 界面布局：借助JFrame和JPanel构建一个 3x3 的网格界面，每个网格由JButton组成。
 * 玩家交互：玩家轮流点击按钮，分别标记为X和O。
 * 胜负判断：每次点击后，程序会检查是否有玩家获胜（行、列、对角线相同）或者平局（棋盘已满）。
 * 状态显示：使用JLabel显示当前轮到哪个玩家以及游戏结果
 *
 * @param
 * @return
 * @date 2025/4/7 17:46
 **/
public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private boolean isPlayerX;
    private JLabel statusLabel;

    public TicTacToeGame() {
        setTitle("井字棋游戏");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buttons = new JButton[3][3];
        isPlayerX = true;

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[row][col].getText().isEmpty()) {
                            if (isPlayerX) {
                                buttons[row][col].setText("X");
                            } else {
                                buttons[row][col].setText("O");
                            }
                            if (checkWin()) {
                                statusLabel.setText((isPlayerX ? "玩家 X" : "玩家 O") + " 获胜！");
                                disableButtons();
                            } else if (isBoardFull()) {
                                statusLabel.setText("平局！");
                            } else {
                                isPlayerX = !isPlayerX;
                                statusLabel.setText((isPlayerX ? "玩家 X" : "玩家 O") + " 回合");
                            }
                        }
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("玩家 X 回合");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private boolean checkWin() {
        // 检查行
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty() &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return true;
            }
        }

        // 检查列
        for (int j = 0; j < 3; j++) {
            if (!buttons[0][j].getText().isEmpty() &&
                    buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[1][j].getText().equals(buttons[2][j].getText())) {
                return true;
            }
        }

        // 检查对角线
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeGame();
            }
        });
    }
}    