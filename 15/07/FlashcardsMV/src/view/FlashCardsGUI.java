package view;

import model.FlashcardsModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class FlashCardsGUI {

    private final FlashcardsModel model;
    private final JTextArea displayArea;
    private final JLabel scoreLabel;
    private final JButton correctButton;
    private final JButton incorrectButton;
    private final JButton toggleButton;

    public FlashCardsGUI(FlashcardsModel model) {
        this.model = model;
        JFrame frame = new JFrame("Flashcards");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel northPanel = new JPanel();
        frame.getContentPane().add(northPanel, BorderLayout.NORTH);
        JPanel southPanel = new JPanel();
        frame.getContentPane().add(southPanel, BorderLayout.SOUTH);

        this.displayArea = new JTextArea("Please open a cards file.", 10, 40);
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        northPanel.add(displayArea);

        this.scoreLabel = new JLabel("0/0");
        northPanel.add(scoreLabel);

        this.toggleButton = new JButton("TOGGLE");
        toggleButton.addActionListener(event -> {
            this.model.flipCard();
            refreshView();
        });
        southPanel.add(toggleButton);

        this.correctButton = new JButton("✓");
        correctButton.addActionListener(event -> {
            if (this.model.hasCurrentCard()) {
                this.model.increaseScore();
                this.model.selectNext();
                refreshView();
            }
        });
        southPanel.add(correctButton);

        this.incorrectButton = new JButton("×");
        incorrectButton.addActionListener(event -> {
            if (this.model.hasCurrentCard()) {
                this.model.selectNext();
                refreshView();
            }
        });
        southPanel.add(incorrectButton);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openFileMenuitem = createOpenFileMenuitem(model, frame);
        fileMenu.add(openFileMenuitem);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        refreshView();
    }

    private JMenuItem createOpenFileMenuitem(FlashcardsModel model, JFrame frame) {
        JMenuItem openFileMenuitem = new JMenuItem("Open file...");
        openFileMenuitem.addActionListener((event) -> {
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
            int returnVal = fileChooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    model.readCardsFromFile(file);
                    refreshView();
                } catch (NoSuchFileException ex) {
                    JOptionPane.showMessageDialog(frame, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "IO error!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid flashcard data file!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Unexpected error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return openFileMenuitem;
    }

    private void refreshView() {
        updateScore();
        updateDisplayArea();
        boolean buttonsShouldBeEnabled = this.model.hasCurrentCard();
        if (buttonsShouldBeEnabled) {
            this.toggleButton.setEnabled(true);
            this.correctButton.setEnabled(true);
            this.incorrectButton.setEnabled(true);
        } else {
            this.toggleButton.setEnabled(false);
            this.correctButton.setEnabled(false);
            this.incorrectButton.setEnabled(false);
        }
    }

    private void updateDisplayArea() {
        if (model.hasNoCards()) {
            this.displayArea.setText("Please open a cards file.");
        } else if (!model.hasCurrentCard()) {
            this.displayArea.setText("Finished! Score: " + model.getScore() + '/' + model.getCardCount());
        } else {
            this.displayArea.setText(this.model.getCurrentCardsUpsideText());
        }
    }

    private void updateScore() {
        this.scoreLabel.setText(String.valueOf(model.getScore()) + '/' + model.getCurrentlyShownCardIndex());
    }
}
