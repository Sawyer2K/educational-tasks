package ru.cft.focusstart.task6.client.view;

import javax.swing.*;

class WelcomeMessagePane extends JOptionPane {

    protected String userNameAsking() {
        while (true) {
            JTextField responseTextField = new JTextField(10);
            showQuestionSamplePanel("Введите имя: ", responseTextField);

            return responseTextField.getText();
        }
    }

    protected String serverIpAsking() {
        while (true) {
            JTextField responseTextField = new JTextField(15);
            showQuestionSamplePanel("Введите IP сервера: ", responseTextField);

            if (responseTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Не введён IP сервера!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

            return responseTextField.getText();
        }
    }

    protected int serverPortAsking() {
        while (true) {
            JTextField responseTextField = new JTextField(10);
            showQuestionSamplePanel("Введите порт:", responseTextField);

            try {
                var port = Integer.parseInt(responseTextField.getText());

                if (port < 2000 || port >= Integer.MAX_VALUE) {
                    JOptionPane.showMessageDialog(this, "Введён неверный порт! Порт должен быть в диапазоне от 2000 до 65535!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    return port;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Порт должен быть в диапазоне от 2000 до 65535!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showQuestionSamplePanel(String question, JTextField responseTextField) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(question);
        panel.add(label);
        panel.add(responseTextField);

        String[] options = {"OK"};

        JOptionPane.showOptionDialog(null, panel, "Настройка клиента", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
}