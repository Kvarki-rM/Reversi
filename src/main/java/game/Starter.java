package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Starter extends JFrame {
    public static void main(String[] args) {
        Starter app = new Starter();
        app.setVisible(true);
    }

    private JTextField input = new JTextField("8", 5);
    private JRadioButton radio2 = new JRadioButton("White");
    private JRadioButton radio3 = new JRadioButton("P_vs_P");

    private Starter() {
        super("game.Starter");
        this.setBounds(500, 500, 350, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        JLabel label = new JLabel("Press");
        container.add(label);
        container.add(input);

        ButtonGroup group = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("Black");
        JRadioButton radio4 = new JRadioButton("P_vs_E");
        group.add(radio1);
        group.add(radio2);
        group2.add(radio3);
        group2.add(radio4);
        container.add(radio1);
        container.add(radio2);
        container.add(radio3);
        container.add(radio4);
        radio1.setSelected(true);
        radio4.setSelected(true);
        JButton button = new JButton("Press");
        button.addActionListener(new ButtonEvent());
        container.add(button);
    }

    class ButtonEvent implements ActionListener {
        public void actionPerformed(ActionEvent g) {
            Reversi.size = Integer.parseInt(input.getText());
            if (radio2.isSelected()) {
                Reversi.turn = Status.WHITE;
            Reversi.pColor = Status.WHITE; }
            if (radio3.isSelected()) Reversi.player = true;
            new Reversi();
            dispose();
        }
    }
}
