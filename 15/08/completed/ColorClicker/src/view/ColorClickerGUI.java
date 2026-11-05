package view;

import model.ColorClickerModel;

import javax.swing.*;
import java.awt.*;

public class ColorClickerGUI {
    private JFrame frame;
    private ColorClickerModel model;
    private BoardPanel boardPanel;
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;

    public ColorClickerGUI(ColorClickerModel model) {
        this.model = model;

        this.frame = new JFrame("ColorClicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu newGameMenu = new JMenu("New Game");
        int[] boardSizes = new int[]{5, 10, 15, 20};
        for (int boardSize : boardSizes) {
            JMenuItem menuItem = new JMenuItem(boardSize + " x " + boardSize);
            menuItem.addActionListener((e) -> newGame(boardSize));
            newGameMenu.add(menuItem);
        }
        menuBar.add(newGameMenu);
        frame.setJMenuBar(menuBar);

        this.boardPanel = new BoardPanel(this.model, this::checkGameOver);
        frame.add(boardPanel, BorderLayout.CENTER);

        this.timerLabel = new JLabel();
        frame.add(timerLabel, BorderLayout.SOUTH);
        this.timer = new Timer(100, (e) -> updateTimerLabel());
        restartTimer();

        frame.pack();
        frame.setVisible(true);
    }

    private void newGame(int boardSize) {
        model.initializeBoard(boardSize);
        refreshView();
        restartTimer();
    }

    private void checkGameOver() {
        if (!model.isGameOver()) {
            return;
        }
        timer.stop();
        JOptionPane.showMessageDialog(frame,
                "Game over! Time: " + formatElapsed(),
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        newGame(model.getBoardSize());
    }

    private void restartTimer() {
        startTime = System.currentTimeMillis();
        updateTimerLabel();
        timer.restart();
    }

    private void updateTimerLabel() {
        timerLabel.setText("Time: " + formatElapsed());
    }

    private String formatElapsed() {
        long elapsed = System.currentTimeMillis() - startTime;
        return String.format("%d.%d s", elapsed / 1000, (elapsed % 1000) / 100);
    }

    private void refreshView() {
        boardPanel.rebuild();
        frame.pack();
    }
}
