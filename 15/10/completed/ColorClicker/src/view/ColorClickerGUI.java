package view;

import model.ColorClickerModel;
import persistence.GameRun;
import persistence.GameRunRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ColorClickerGUI {
    private static final int LEADERBOARD_SIZE = 10;
    private JFrame frame;
    private ColorClickerModel model;
    private GameRunRepository repository;
    private BoardPanel boardPanel;
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;

    public ColorClickerGUI(ColorClickerModel model, GameRunRepository repository) {
        this.model = model;
        this.repository = repository;

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
        JMenu leaderboardMenu = new JMenu("Leaderboard");
        for (int boardSize : boardSizes) {
            JMenuItem menuItem = new JMenuItem(boardSize + " x " + boardSize);
            menuItem.addActionListener((e) -> showLeaderboard(boardSize));
            leaderboardMenu.add(menuItem);
        }
        menuBar.add(leaderboardMenu);
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
        long elapsedMillis = System.currentTimeMillis() - startTime;
        int choice = JOptionPane.showConfirmDialog(frame,
                "Game over! Time: " + formatElapsed() + "\nDo you want to save this run?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            promptPlayerName().ifPresent(name ->
                    saveRun(name, model.getBoardSize(), model.getClickNumber(), elapsedMillis));
        }
        newGame(model.getBoardSize());
    }

    private Optional<String> promptPlayerName() {
        while (true) {
            String input = JOptionPane.showInputDialog(frame,
                    "Enter your name:",
                    "Save Run",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return Optional.empty();
            }
            String name = input.trim();
            if (!name.isEmpty()) {
                return Optional.of(name);
            }
            JOptionPane.showMessageDialog(frame,
                    "The name cannot be empty.",
                    "Invalid Name",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveRun(String playerName, int boardSize, int clicks, long elapsedMillis) {
        try {
            repository.save(new GameRun(playerName, boardSize, clicks, elapsedMillis));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,
                    "Could not save the run: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        showLeaderboard(boardSize);
    }

    private void showLeaderboard(int boardSize) {
        List<GameRun> topRuns;
        try {
            topRuns = repository.findTopRuns(boardSize, LEADERBOARD_SIZE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,
                    "Could not load the leaderboard: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] columns = {"#", "Name", "Time", "Clicks"};
        Object[][] rows = new Object[topRuns.size()][];
        for (int i = 0; i < topRuns.size(); i++) {
            GameRun run = topRuns.get(i);
            rows[i] = new Object[]{i + 1, run.playerName(), formatMillis(run.elapsedMillis()), run.clicks()};
        }
        JTable table = new JTable(rows, columns);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(
                new Dimension(350, table.getRowHeight() * LEADERBOARD_SIZE));

        JOptionPane.showMessageDialog(frame,
                new JScrollPane(table),
                "Leaderboard - " + boardSize + " x " + boardSize,
                JOptionPane.PLAIN_MESSAGE);
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
        return formatMillis(System.currentTimeMillis() - startTime);
    }

    private static String formatMillis(long millis) {
        return String.format("%d.%d s", millis / 1000, (millis % 1000) / 100);
    }

    private void refreshView() {
        boardPanel.rebuild();
        frame.pack();
    }
}
