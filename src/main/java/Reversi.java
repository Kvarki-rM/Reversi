import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reversi extends JFrame {
    static Status turn;
    static int size = 8;
    private final static int imageSize = 64;
    private static JPanel panel;
    private Board actualGame = new Board();
    private int temp = 1;

    public static void main(String[] args) {
        new Reversi();
    }

    Reversi() {
        if (turn == Status.WHITE) {
            actualGame.switchTurn();
            actualGame.numTurn -= 1;
        }
        opener();
        window();
    }

    private void opener() {
        Dimension windowSize = new Dimension(128, 64);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Font font = new Font("Verdana", Font.PLAIN, 16);

        final JLabel label = new JLabel();
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(windowSize);
        label.setFont(font);
        label.setBorder(border);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);

        final JLabel label2 = new JLabel();
        label2.setVerticalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setPreferredSize(windowSize);
        label2.setFont(font);
        label2.setBorder(border);
        label2.setOpaque(true);
        label2.setBackground(Color.WHITE);
        label2.setForeground(Color.BLACK);

        final JLabel back = new JLabel();
        back.setVerticalAlignment(JLabel.CENTER);
        back.setHorizontalAlignment(JLabel.CENTER);
        back.setPreferredSize(new Dimension(256, 64));
        back.setFont(new Font("Verdana", Font.PLAIN, 12));
        back.setOpaque(true);
        back.setForeground(Color.GRAY);

        final JLabel wh = new JLabel();
        wh.setOpaque(true);
        wh.setForeground(Color.WHITE);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font(null, Font.BOLD, 22));

                label.setLocation(imageSize * size, 0);
                label2.setLocation(imageSize * size + 128, 0);
                back.setLocation(imageSize * size, 64);
                label.setText("" + actualGame.white);
                label2.setText("" + actualGame.black);
                back.setText("Now move : " + actualGame.turn.toString().toLowerCase() + " / Turn № : " + actualGame.numTurn);

                if (actualGame.turn == Status.WHITE) back.setBackground(Color.WHITE);
                else back.setBackground(Color.BLACK);

                g.setFont(new Font("Arial", Font.ITALIC, 18));
                g.drawImage(getImage(Tile.DOP), imageSize * size, 0, this);

                double whi = actualGame.white;
                double bl = actualGame.black;
                int i1 = (int) (Math.round(whi / (whi + bl) * 240));
                wh.setPreferredSize(new Dimension(i1, 32));
                wh.setLocation(imageSize * size + 8, size * imageSize - 48);
                g.drawImage(getImage(Tile.BLACK), imageSize * size + 8, size * imageSize - 48, this);
                g.drawString("" + Math.round(whi / (whi + bl) * 100), imageSize * size + 10, size * imageSize - 55);
                g.drawString("" + Math.round(bl / (whi + bl) * 100), size * imageSize + imageSize * 4 - 38, size * imageSize - 55);

                if (temp > 1) g.drawImage(getImage(Tile.BACK), imageSize * (size + 1) + 10, imageSize * 2 + 13, this);

                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++)
                        g.drawImage(getImage(actualGame.board[x][y].getStatus().getTile()), x * imageSize, y * imageSize, this);

            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / imageSize;
                int y = e.getY() / imageSize;
                if (e.getButton() == MouseEvent.BUTTON1)
                    if (x >= size && temp >= 2) {
                        temp = 0;
                        actualGame.backTurn();
                        panel.repaint();
                    } else if (x < size && y < size) {
                        makeTurn(new Coordinate(x, y));
                        temp++;
                    }
            }
        });
        panel.setPreferredSize(new Dimension(size * imageSize + imageSize * 4, size * 64));
        panel.add(back);
        panel.add(label);
        panel.add(label2);
        panel.add(wh);
        add(panel);
    }

    private void window() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(actualGame.turn + " Turn           " + "White : " + actualGame.white + " Black : " + actualGame.black);
        setIconImage(getImage(Tile.B_ICON));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void makeTurn(Coordinate coord) {
        if (actualGame.manyTurns == 0) {
            JOptionPane.showMessageDialog(this, "No way",
                    "Ret", JOptionPane.INFORMATION_MESSAGE);
            actualGame.switchTurn();
            if (actualGame.manyTurns == 0)
                end();
        }
        if (actualGame.board[coord.x][coord.y].getStatus() == actualGame.helper) {
            actualGame.add(coord.x, coord.y);
            actualGame.switchTurn();
            setTitle(actualGame.turn + " Turn           " + "White : " + actualGame.white + " Black : " + actualGame.black);
            if (actualGame.turn == Status.WHITE)
                setIconImage(getImage(Tile.W_ICON));
            else setIconImage(getImage(Tile.B_ICON));
            if (actualGame.manyTurns == 0) end();
            panel.repaint();
        }
    }

    private Image getImage(Tile tile) {
        return new ImageIcon(getClass().getResource(tile.getTitle())).getImage();
    }

    private void end() {
        JOptionPane.showMessageDialog(this, "Black " + actualGame.black + "\n" + "White " + actualGame.white,
                "End", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
}


