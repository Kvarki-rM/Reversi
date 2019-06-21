import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reversi extends JFrame {
    final static int size = 8;
    private static JPanel panel;
    private Board actualGame = new Board();
    private boolean temp = false;

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
                g.drawImage(getImage(Tile.DOP), 512, 0, this);
                g.drawString("Now move : " + actualGame.turn.toString().toLowerCase(), 590, 90);
                g.drawString("" + actualGame.white, 575, 35);
                g.drawString("" + actualGame.black, 700, 35);
                g.drawString("" + actualGame.numTurn, 638, 105);
                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++) {
                        Tile tile = getTile(x, y);
                        g.drawImage(getImage(tile), x * 64, y * 64, this);
                    }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / 64;
                int y = e.getY() / 64;
                if (e.getButton() == MouseEvent.BUTTON1)
                    if (x >= 8 && temp) {
                        temp = false;
                        actualGame.backTurn();
                        panel.repaint();
                    } else if (x < 8 && y < 8) {
                        makeTurn(new Coordinate(x, y));
                        temp = true;
                    }
            }
        });
        panel.setPreferredSize(new Dimension(size * 64 + 256, size * 64));
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
        for (int i = 0; i < actualGame.board.length; i++)
            for (int j = 0; j < actualGame.board[0].length; j++)
                actualGame.lastboard[i][j].setStatus(actualGame.board[i][j].getStatus());
        if (actualGame.manyTurns == 0 && actualGame.black + actualGame.white != 64) {
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


