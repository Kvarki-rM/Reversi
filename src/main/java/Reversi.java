import javax.swing.*;
import java.awt.*;
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
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImage(Tile.DOP), imageSize * size, 0, this);
                g.drawString("" + actualGame.white, imageSize * size + 60, imageSize / 2);
                g.drawString("" + actualGame.black, imageSize * size + 190, imageSize / 2);
                g.drawString("Now move : " + actualGame.turn.toString().toLowerCase(), size * imageSize + 75, 90);
                g.drawString("Turn number : " + actualGame.numTurn, imageSize * size + 80, imageSize * 2 - imageSize / 3);
                double whi = actualGame.white;
                double bla = actualGame.black;
                g.drawString("" + Math.round(whi / (whi + bla) * 100), imageSize * size + 16, size * imageSize - 64);
                g.drawString("" + Math.round(bla / (whi + bla) * 100), size * imageSize + imageSize * 4 - 32, size * imageSize - 64);
                for (int i = 0; i < (whi / (whi + bla) * 10); i++)
                    g.drawImage(getImage(Tile.W_W), imageSize * size + 8 + (i * 24), size * imageSize - 48, this);
                for (int i = 0; i < (bla / (whi + bla) * 10); i++)
                    g.drawImage(getImage(Tile.B_B), size * imageSize + imageSize * 4 - (i * 24) - 32, size * imageSize - 48, this);
                if (temp > 1)
                    g.drawImage(getImage(Tile.BACK), imageSize * (size + 1) + 10, imageSize * 2 + 13, this);

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


