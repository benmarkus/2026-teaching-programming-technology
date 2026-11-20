import model.ColorClickerModel;
import persistence.GameRunRepository;
import view.ColorClickerGUI;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    private static final String DATABASE_URL = "jdbc:sqlite:colorclicker.db";

    public static void main(String[] args) {
        ColorClickerModel model = new ColorClickerModel();
        model.initializeBoard(10);

        GameRunRepository repository;
        try {
            repository = new GameRunRepository(DATABASE_URL);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Could not open the database: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(() -> new ColorClickerGUI(model, repository));
    }
}
