import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.function.IntBinaryOperator;

public class CalcGUI {

    private static final int TEXT_FIELD_WIDTH = 30;
    private JTextField operand1Field;
    private JTextField operand2Field;
    private JTextField resultField;

    public CalcGUI() {
        JFrame frame = new JFrame("Calc (short of calculator)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        JButton additionButton = new JButton("+");
        additionButton.addActionListener((event) -> {
            this.resultField.setText(String.valueOf(Integer.parseInt(operand1Field.getText()) + Integer.parseInt(operand2Field.getText())));
        });
        buttonPanel.add(additionButton);

        JButton subtractionButton = new JButton("-");
        subtractionButton.addActionListener(new CalcActionListener(Math::subtractExact));
        buttonPanel.add(subtractionButton);

        JButton multiplicationButton = new JButton("*");
        multiplicationButton.addActionListener(new CalcActionListener(Math::multiplyExact));
        buttonPanel.add(multiplicationButton);

        JButton divisionButton = new JButton("/");
        divisionButton.addActionListener(new CalcActionListener(Math::divideExact));
        buttonPanel.add(divisionButton);

        JButton powerButton = new JButton("^");
        powerButton.addActionListener(new CalcActionListener((op1, op2) -> Math.toIntExact(Math.round(Math.pow(op1, op2)))));
        buttonPanel.add(powerButton);

        JPanel textFieldPanel = new JPanel();
        this.operand1Field = new JTextField(TEXT_FIELD_WIDTH);
        textFieldPanel.add(operand1Field);
        this.operand2Field = new JTextField(TEXT_FIELD_WIDTH);
        textFieldPanel.add(operand2Field);
        this.resultField = new JTextField(TEXT_FIELD_WIDTH);
        resultField.setEditable(false);
        textFieldPanel.add(resultField);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(textFieldPanel, BorderLayout.NORTH);

        frame.setVisible(true);
        frame.pack();
    }

    class CalcActionListener implements ActionListener {
        private IntBinaryOperator op;

        public CalcActionListener(IntBinaryOperator op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer operand1 = Integer.parseInt(operand1Field.getText());
                Integer operand2 = Integer.parseInt(operand2Field.getText());
                BigInteger result = BigInteger.valueOf(op.applyAsInt(operand1, operand2));
                resultField.setText(String.valueOf(result));
            } catch (Exception ex) {
                resultField.setText("Unexpected operands.");
            }
        }
    }
}
