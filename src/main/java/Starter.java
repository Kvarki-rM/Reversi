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

    private Starter() {
        super("Starter");
        this.setBounds(500, 500, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        JLabel label = new JLabel("Press");
        container.add(label);
        container.add(input);

        ButtonGroup group = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("Black");
        group.add(radio1);
        group.add(radio2);
        container.add(radio1);
        radio1.setSelected(true);
        container.add(radio2);
        JButton button = new JButton("Press");
        button.addActionListener(new ButtonEvent());
        container.add(button);
    }

    class ButtonEvent implements ActionListener {
        public void actionPerformed(ActionEvent g) {
                Reversi.size = Integer.parseInt(input.getText());
            if (radio2.isSelected())
                Reversi.turn = Status.WHITE;
            new Reversi();
            dispose();
        }
    }
}
