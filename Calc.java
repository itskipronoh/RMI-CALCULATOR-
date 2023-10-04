import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Calculator {
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subtractButton, multiplyButton,
            divideButton;
    private JButton equalsButton, clearButton, decimalButton;
    private JPanel panel;
    private double firstNumber;
    private double secondNumber;
    private double result;
    private String operator;
    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(null);
        textField = new JTextField();
        textField.setBounds(30, 30, 240, 30);
        textField.setEditable(false);
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }
        functionButtons = new JButton[7];
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        decimalButton = new JButton(".");
        panel = new JPanel();
        panel.setBounds(30, 80, 240, 240);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);
        panel.add(decimalButton);
        panel.add(numberButtons[0]);
        panel.add(equalsButton);
        panel.add(divideButton);
        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = equalsButton;
        functionButtons[5] = clearButton;
        functionButtons[6] = decimalButton;
        for (int i = 0; i < 7; i++) {
            functionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonText = button.getText();
                    if (buttonText.equals("C")) {
                        textField.setText("");
                    } else if (buttonText.equals("=")) {
                        secondNumber =
                                Double.parseDouble(textField.getText());
                        calculate();
                        textField.setText(String.valueOf(result));
                    } else {
                        operator = buttonText;
                        firstNumber =
                                Double.parseDouble(textField.getText());
                        textField.setText("");
                    }
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonText = button.getText();
                    textField.setText(textField.getText() + buttonText);
                }
            });
        }
        frame.add(textField);
        frame.add(panel);
        frame.setVisible(true);
    }
    private void calculate() {
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break
                        ;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }
    }
    public static void main(String[] args) {
        new Calculator();
    }
}