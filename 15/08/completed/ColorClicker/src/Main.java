import model.ColorClickerModel;
import view.ColorClickerGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
            ColorClickerModel model = new ColorClickerModel();
            model.initializeBoard(10);
            SwingUtilities.invokeLater(() -> new ColorClickerGUI(model));
    }
}