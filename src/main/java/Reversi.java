import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reversi extends JFrame {
    final static int size = 8;
    private final static int imageSize = 64;
    private static JPanel panel;
    private Board actualGame = new Board();
    private int temp = 1;

    public static void main(String[] args) {
        new Reversi();
    }

    private Reversi() {
        opener();
        window();
    }

    private void opener() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImage(Tile.DOP), imageSize * size, 0, this);
                g.drawString("" + actualGame.white, imageSize * size + 66, imageSize / 2);
                g.drawString("" + actualGame.black, imageSize * size + 190, imageSize / 2);
                g.drawString("Now move : " + actualGame.turn.toString().toLowerCase(), size * imageSize + 75, 90);
                g.drawString("Turn number : " + actualGame.numTurn, imageSize * size + 80, imageSize * 2 - imageSize / 3);
                if (temp < 2)
                    g.drawImage(getImage(Tile.BACK), imageSize * size+5, imageSize * 3, this);

                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++) {
                        Tile tile = getTile(x, y);
                        g.drawImage(getImage(tile), x * imageSize, y * imageSize, this);
                    }
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
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Image getImage(Tile tile) {
        ImageIcon icon = new ImageIcon(getClass().getResource(tile.getTitle()));
        return icon.getImage();
    }

    private void makeTurn(Coordinate coord) {
        if (actualGame.manyTurns == 0) {
            actualGame.switchTurn();
            actualGame.temp++;
            if (actualGame.temp >= 2)
                end();
        }
        if (actualGame.board[coord.x][coord.y].getStatus() == actualGame.helper) {
            actualGame.add(coord.x, coord.y);
            actualGame.switchTurn();
            setTitle(actualGame.turn + " Turn           " + "White : " + actualGame.white + " Black : " + actualGame.black);
            if (actualGame.manyTurns == 0) end();
            panel.repaint();
        }
    }

    private Tile getTile(int x, int y) {
        if (actualGame.board[x][y].getStatus() == Status.EMPTY)
            return Tile.E;
        else return actualGame.board[x][y].getStatus().getTile();
    }

    private void end() {
        JOptionPane.showMessageDialog(this, "Black " + actualGame.black + "\n" + "White " + actualGame.white,
                "End", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
}


