import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.IntBinaryOperator;

public class App
{
    private final static String WINDOW_TITLE = "CALC (short for calculator)";
    private final static int TEXT_FIELD_WIDTH = 30;

    private JFrame mainFrame;

    private JPanel buttonPanel;
    private JPanel literalPanel;

    private JTextField operand1TextField;
    private JTextField operand2TextField;
    private JTextField resultTextField;

    public App() {
        this.mainFrame = new JFrame(WINDOW_TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.literalPanel = new JPanel();
        literalPanel.setLayout(new GridLayout(3,1,5,5));
        this.operand1TextField = new JTextField(TEXT_FIELD_WIDTH);
        literalPanel.add(operand1TextField);
        this.operand2TextField = new JTextField(TEXT_FIELD_WIDTH);
        literalPanel.add(operand2TextField);
        this.resultTextField = new JTextField(TEXT_FIELD_WIDTH);
        resultTextField.setEditable(false);
        literalPanel.add(resultTextField);

        createButtonPanel();
        buttonPanel.setLayout(new GridLayout(1,5,5,5));

        literalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        mainFrame.getContentPane().add(BorderLayout.CENTER, literalPanel);
        mainFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        createMenuBar();
    }

    public void run() {
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);
        JMenu calcMenu = new JMenu("Calc");
        calcMenu.setMnemonic('C');
        menuBar.add(calcMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        calcMenu.add(exitMenuItem);
        exitMenuItem.addActionListener((event) -> System.exit(0));
    }

    private void createButtonPanel() {
        this.buttonPanel = new JPanel();
        addOperationButton("+", Math::addExact);
        addOperationButton("-", Math::subtractExact);
        addOperationButton("*", Math::multiplyExact);
        addOperationButton("/", Math::floorDiv);
        addOperationButton("^", (a, b) -> Math.toIntExact(Math.round(Math.pow(a, b))));
    }

    private void addOperationButton(String label, IntBinaryOperator operation) {
        JButton button = new JButton(label);
        //button.addActionListener(new CalcActionListener(operation, operand1TextField, operand2TextField, resultTextField));
        button.addActionListener((event) -> calculateAndDisplay(operation));

        buttonPanel.add(button);
    }

    private void calculateAndDisplay(IntBinaryOperator operator) {
        try {
            int a = Integer.parseInt(operand1TextField.getText().trim());
            int b = Integer.parseInt(operand2TextField.getText().trim());
            resultTextField.setText(String.valueOf(operator.applyAsInt(a, b)));
        } catch (NumberFormatException ex) {
            resultTextField.setText("Invalid number");
        } catch (ArithmeticException ex) {
            resultTextField.setText("Error: " + ex.getMessage());
        }
    }

    static class CalcActionListener implements ActionListener {
        private final IntBinaryOperator operation;
        private final JTextField operand1TextField;
        private final JTextField operand2TextField;
        private final JTextField resultTextField;

        public CalcActionListener(IntBinaryOperator operation, JTextField operand1TextField,
                                  JTextField operand2TextField, JTextField resultTextField) {
            this.operation = operation;
            this.operand1TextField = operand1TextField;
            this.operand2TextField = operand2TextField;
            this.resultTextField = resultTextField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int a = Integer.parseInt(operand1TextField.getText().trim());
                int b = Integer.parseInt(operand2TextField.getText().trim());
                resultTextField.setText(String.valueOf(operation.applyAsInt(a, b)));
            } catch (NumberFormatException ex) {
                resultTextField.setText("Invalid number");
            } catch (ArithmeticException ex) {
                resultTextField.setText("Error: " + ex.getMessage());
            }
        }
    }
}
