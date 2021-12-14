package ru.cft.focusstart.task3.view;

import javax.swing.*;
import java.awt.*;

public class AboutWindow extends JDialog {

    public AboutWindow(JFrame frame) {
        super(frame, "About", true);

        GridBagLayout layout = new GridBagLayout();
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        JTextArea textField = new JTextArea();
        textField.setText(getTextInfo());
        contentPane.add(textField);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(350, 300));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

     private String getTextInfo() {
        return """
                   Цель – открыть все ячейки на поле так,
                чтобы не “взорвать” ни одну мину.
                   Для поиска взрывоопасных квадратиков
                можно использовать подсказки:
                                
                   Числа в ячейках показывают количество мин,
                скрытых в восьми находящихся рядом ячейках.
                   Если возле открытой ячейки есть пустые клеточки,
                они откроются автоматически.
                   Если вы понимаете, что в какой-то клеточке
                скрывается бомба, пометьте ее флажком.
                   Когда вы откроете ячейку с миной, игра заканчивается
                проигрышем.
                   Для победы в игре нужно открыть все ячейки на поле,
                пометив все клеточки с бомбой флажком.
                """;
     }
}
