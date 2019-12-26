package game;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import Bot.Bot;
import java.awt.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reversi extends JFrame {
    static Status turn;
    static int size = 8;
    private final static int imageSize = 64;
    private static JPanel panel;
    private int temp = 1;
    static boolean player = false;
    static Status pColor = Status.BLACK;

    public static void main(String[] args) {
        new Reversi();
    }

    Reversi() {
        new Board();
        if (turn == Status.WHITE) {
            Board.switchTurn();
            Board.numTurn -= 1;
        }
        opener();
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(Board.turn + " Turn           " + "White : " + Board.white + " Black : " + Board.black);
        setIconImage(getImage(Tile.B_ICON));
        setLocationRelativeTo(null);
        setVisible(true);
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
                g.setFont(new Font("Arial", Font.ITALIC, 18));
                g.drawImage(getImage(Tile.DOP), imageSize * size, 0, this);

                double whi = Board.white;
                double bl = Board.black;
                int i1 = (int) (Math.round(whi / (whi + bl) * 240));

                wh.setPreferredSize(new Dimension(i1, 32));
                wh.setLocation(imageSize * size + 8, size * imageSize - 48);
                g.drawImage(getImage(Tile.BLACK), imageSize * size + 8, size * imageSize - 48, this);
                g.drawString("" + Math.round(whi / (whi + bl) * 100), imageSize * size + 10, size * imageSize - 55);
                g.drawString("" + Math.round(bl / (whi + bl) * 100), size * imageSize + imageSize * 4 - 38, size * imageSize - 55);

                label.setLocation(imageSize * size, 0);
                label2.setLocation(imageSize * size + 128, 0);
                back.setLocation(imageSize * size, 64);
                label.setText("" + Board.black);
                label2.setText("" + Board.white);
                back.setText("Now move : " + Board.turn.toString().toLowerCase() + " / Turn № : " + Board.numTurn);

                if (Board.turn == Status.WHITE) back.setBackground(Color.WHITE);
                else back.setBackground(Color.BLACK);

                if (temp > 1) g.drawImage(getImage(Tile.BACK), imageSize * (size + 1) + 10, imageSize * 2 + 13, this);

                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++)
                        g.drawImage(getImage(Board.board[x][y].getStatus().getTile()), x * imageSize, y * imageSize, this);
            }
        };
        panel.setPreferredSize(new Dimension(size * imageSize + imageSize * 4, size * 64));
        panel.add(back);
        panel.add(label);
        panel.add(label2);
        panel.add(wh);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / imageSize;
                int y = e.getY() / imageSize;
                if (e.getButton() == MouseEvent.BUTTON1)
                    if (x >= size && temp >= 2) {
                        temp = 0;
                        Board.numTurn = Board.numTurn - 2;
                        Board.backTurn();
                        Board.scanner();
                        Board.switchTurn();
                        panel.repaint();
                    } else if (x < size && y < size) {
                        makeTurn(new Coordinate(x, y));
                        temp++;
                    }
            }
        });
        add(panel);
    }

    private void botMakeTurn() {
        int x = Bot.coordinate().x;
        int y = Bot.coordinate().y;
        System.out.println(x);
        System.out.println(y);
        Board.add(x, y);
        turner();
    }


    private void makeTurn(Coordinate coord) {
        if (Board.manyTurns == 0) {
            JOptionPane.showMessageDialog(this, "No way",
                    "Ret", JOptionPane.INFORMATION_MESSAGE);
            Board.switchTurn();
            if (Board.manyTurns == 0)
                end();
        }
        if (Board.board[coord.x][coord.y].getStatus() == Board.helper) {
            Board.add(coord.x, coord.y);
            turner();
            if (!player && (pColor != Board.turn)) {
                temp++;
                botMakeTurn();
            }
            panel.repaint();
        }

    }

    private void turner() {
        Board.switchTurn();
        setTitle(Board.turn + " Turn           " + "White : " + Board.white + " Black : " + Board.black);
        if (Board.turn == Status.WHITE)
            setIconImage(getImage(Tile.W_ICON));
        else setIconImage(getImage(Tile.B_ICON));
        if (Board.manyTurns == 0) end();
        panel.repaint();
    }

    private Image getImage(@NotNull Tile tile) {
        return new ImageIcon(tile.getTitle()).getImage();
    }

    private void end() {
        JOptionPane.showMessageDialog(this, "Black " + Board.black + "\n" + "White " + Board.white,
                "End", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
}


