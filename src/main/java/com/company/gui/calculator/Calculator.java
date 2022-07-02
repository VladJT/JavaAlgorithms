package com.company.gui.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame {

    public static void main(String[] args) {
        new Calculator();
    }

    JPanel panel;
    JButton buttonPlus;
    JButton buttonMinus;
    JButton buttonMultiply;
    JButton buttonDivide;
    JButton buttonExponent;
    JLabel labelResult;
    JTextField editTextNum1;
    JTextField editTextNum2;

    public Calculator() {
        super("Калькулятор");
        setSize(800, 600);
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Times New Roman", Font.BOLD, 18);

        //  JPanel panel = new JPanel(new VerticalLayout());
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setFont(font);

        /**
         *  ГРИД для ввода данных и вывода результата
         */
        JLabel labelNumber1 = new JLabel("Введите первое число:");
        JLabel labelNumber2 = new JLabel("Введите второе число:");
        JLabel labelNumber3 = new JLabel("Результат:");
        labelNumber3.setIcon(UIManager.getIcon("OptionPane.questionIcon"));

        // поле ввода 1го числа
        editTextNum1 = new JTextField(10);
        editTextNum1.setText("0");
        editTextNum1.setHorizontalAlignment(JTextField.RIGHT);
        // поле ввода 2го числа
        editTextNum2 = new JTextField(10);
        editTextNum2.setText("0");
        editTextNum2.setHorizontalAlignment(JTextField.RIGHT);
        // поле вывода результата
        labelResult = new JLabel(" = ?");
        labelResult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel gridNumbers = new JPanel();
        gridNumbers.setLayout(new GridLayout(3, 2, 4, 5));
        gridNumbers.add(labelNumber1);
        gridNumbers.add(editTextNum1);
        gridNumbers.add(labelNumber2);
        gridNumbers.add(editTextNum2);
        gridNumbers.add(labelNumber3);
        gridNumbers.add(labelResult);


        /**
         *  ГРИД для математических операций
         */
        //кнопки
        buttonPlus = new JButton("+");
        buttonMinus = new JButton("-");
        buttonMultiply = new JButton("*");
        buttonDivide = new JButton("/");
        buttonExponent = new JButton("Степень");

        JPanel gridButtons = new JPanel();
        gridButtons.setLayout(new GridLayout(3, 0, 4, 5));
        gridButtons.add(buttonPlus);
        gridButtons.add(buttonMinus);
        gridButtons.add(buttonMultiply);
        gridButtons.add(buttonDivide);
        gridButtons.add(buttonExponent);


        // размещаем на форме
        panel.add(gridNumbers);
        panel.add(gridButtons);


        // установка обработчиков событий
        setListeners();

        setContentPane(panel);
        pack();
        setVisible(true);
    }


    private void setListeners() {
        ActionListener buttonPlusListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(editTextNum1.getText());
                    double y = Double.parseDouble(editTextNum2.getText());
                    labelResult.setText(" = " + String.valueOf(x + y));
                    JButton j = (JButton) e.getSource();
                    String command = j.getText();
                    switch (command) {
                        case "+":
                            labelResult.setText(" = " + String.valueOf(x + y));
                            break;
                        case "-":
                            labelResult.setText(" = " + String.valueOf(x - y));
                            break;
                        case "/":
                            labelResult.setText(" = " + String.format("%.4f", x / y));
                            break;
                        case "*":
                            labelResult.setText(" = " + String.format("%.4f", x * y));
                            break;
                        case "Степень":
                            // степень должна быть целым числом
                            int s = Integer.parseInt(editTextNum2.getText());
                            if (s >= 0) labelResult.setText(" = " + String.valueOf(getExponent(x, s)));
                            else JOptionPane.showMessageDialog(panel, "Степень числа должна быть больше или равна 0");
                            break;
                    }

                } catch (NumberFormatException e1) {
                    showErrorDlg();
                }

            }
        };
        buttonPlus.addActionListener(buttonPlusListener);
        buttonMinus.addActionListener(buttonPlusListener);
        buttonDivide.addActionListener(buttonPlusListener);
        buttonMultiply.addActionListener(buttonPlusListener);
        buttonExponent.addActionListener(buttonPlusListener);
    }

    private double getExponent(double x, int y) {
        if (y == 0) return 1;
        else if (y == 1) return x;
        else return x * getExponent(x, y - 1);
    }

    private void showErrorDlg() {
        JOptionPane.showMessageDialog(panel, "<html><h4>Некорректный ввод</h4>", "Ошибка", JOptionPane.ERROR_MESSAGE,
                UIManager.getIcon("OptionPane.warningIcon"));
    }
}