import model.FlashcardsModel;
import view.FlashCardsGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new FlashCardsGUI(
                new FlashcardsModel()
        ));
    }
}