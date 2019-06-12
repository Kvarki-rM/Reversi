import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Game extends JFrame {

    private static JPanel panel;
    private Board actualGame = new Board();

    public static void main(String[] args) {
        new Game();
    }

    private Game() {
        opener();
        window();
    }

    private void opener() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                scanner(actualGame.turn, actualGame.pastTurn);
                super.paintComponent(g);
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        Tile tile = getTile(x, y);
                        g.drawImage(getImage(tile), x * 64, y * 64, this);
                    }
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / 64;
                int y = e.getY() / 64;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    makeTurn(new Stats(x, y));
                }

            }
        });

        panel.setPreferredSize(new Dimension(512, 512));
        add(panel);
    }

    private void window() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("^-^");
        setVisible(true);
        setIconImage(getImage(Tile.ICON));
    }

    private Image getImage(Tile tile) {
        String filename = tile.getTitle() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private void makeTurn(Stats coord) {
        scanner(actualGame.turn, actualGame.pastTurn);
        if ((actualGame.board[coord.x][coord.y].getStatus() == Status.WHITE_L ||
                actualGame.board[coord.x][coord.y].getStatus() == Status.BLACK_L)) {
            add(coord.x, coord.y, actualGame.turn, actualGame.pastTurn);
            actualGame.switchTurn();
            panel.repaint();
        }
    }

    private Tile getTile(int x, int y) {
        if (actualGame.board[x][y].getStatus() == Status.EMPTY)
            return Tile.E;
        else return actualGame.board[x][y].getStatus().getTile();
    }

    private void add(int x, int y, Status value, Status another) {
        actualGame.board[x][y].setStatus(value);
        //вверх
        for (int i = 1; i < x; i++)
            if ( actualGame.board[x - i][y].getStatus().equals(value)) {
                for (int j = i; j > 0; j--)
                    if (Objects.equals(actualGame.board[x - j][y].getStatus(), another))
                        actualGame.board[x - j][y].setStatus(value);
            } else if (actualGame.board[x - i][y].getStatus().equals(Status.EMPTY)) {
                break;
            }
        //вправо
        for (int i = 1; i < 8 - y; i++) {
            if (actualGame.board[x][y + i].getStatus().equals(value)) {
                for (int j = i; j > 0; j--) {
                    if (Objects.equals(actualGame.board[x][y + i - j].getStatus(), another)) {
                        actualGame.board[x][y + i - j].setStatus(value);
                    }
                }
            } else if (actualGame.board[x][y + i].getStatus().equals(Status.EMPTY)) {
                break;
            }
        }
        //влево
        for (int i = 1; i < y; i++) {
            if (actualGame.board[x][y - i].getStatus().equals(value)) {
                for (int j = i; j > 0; j--) {
                    if (Objects.equals(actualGame.board[x][y - j].getStatus(), another)) {
                        actualGame.board[x][y - j].setStatus(value);

                    }
                }
            } else if (actualGame.board[x][y - i].getStatus().equals(Status.EMPTY)) {
                break;
            }
        }
        //вниз
        for (int i = 1; i < 7 - x; i++)
            if (actualGame.board[x + i][y].getStatus().equals(value)) {
                for (int j = i; j > 0; j--)
                    if (Objects.equals(actualGame.board[x + i - j][y].getStatus(), another))
                        actualGame.board[x + i - j][y].setStatus(value);
            } else if (actualGame.board[x + i][y].getStatus().equals(Status.EMPTY)) {
                break;
            }
        //вниз-вправо
        for (int i = 1; i < Math.min(7 - x, 7 - y); i++) {
            if (actualGame.board[x + i][y + i].getStatus().equals(value)) {
                for (int j = i; j > 0; j--) {
                    if (Objects.equals(actualGame.board[x + i - j][y + i - j].getStatus(), another)) {
                        actualGame.board[x + i - j][y + i - j].setStatus(value);
                    }
                }
            } else if (actualGame.board[x + i][y + i].getStatus().equals(Status.EMPTY)) {
                break;
            }
        }
        //вниз-влево
        for (int i = 1; i < Math.min(7 - x, y); i++) {
            if (actualGame.board[x + i][y - i].getStatus().equals(value)) {
                for (int j = i; j > 0; j--) {
                    if (Objects.equals(actualGame.board[x + j][y - j].getStatus(), another)) {
                        actualGame.board[x + j][y - j].setStatus(value);
                    }
                }
            } else if (actualGame.board[x + i][y - i].getStatus().equals(Status.EMPTY)) {
                break;
            }
        }
        //вверх-вправо
        for (int i = 1; i < Math.min(x, 7 - y); i++)
            if (actualGame.board[x - i][y + i].getStatus().equals(value)) {
                for (int j = i; j > 0; j--)
                    if (Objects.equals(actualGame.board[x - j][y + j].getStatus(), another))
                        actualGame.board[x - j][y + j].setStatus(value);
            } else if (actualGame.board[x - i][y + i].getStatus().equals(Status.EMPTY)) {
                break;
            }
        //вверх-влево
        for (int i = 1; i < Math.min(x, y); i++)
            if (actualGame.board[x - i][y - i].getStatus().equals(value)) {
                for (int j = i; j > 0; j--)
                    if (Objects.equals(actualGame.board[x - i + j][y - i + j].getStatus(), another))
                        actualGame.board[x - i + j][y - i + j].setStatus(value);

            } else if (actualGame.board[x - i][y - i].getStatus().equals(Status.EMPTY)) {
                break;
            }
    }

    private void scanner(Status count, Status another) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Objects.equals(actualGame.board[i][j].getStatus(), Status.WHITE_L) ||
                        Objects.equals(actualGame.board[i][j].getStatus(), Status.BLACK_L))
                    actualGame.board[i][j].setStatus(Status.EMPTY);

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Objects.equals(actualGame.board[i][j].getStatus(), count))
                    for (int z = 1; z < 7 - j; z++)
                        if (Objects.equals(actualGame.board[i][j + z].getStatus(), another)) {
                            if (Objects.equals(actualGame.board[i][j + z + 1].getStatus(), Status.EMPTY))
                                actualGame.board[i][j + z + 1].setStatus(actualGame.helper);
                        } else break;


        for (int i = 7; i >= 0; i--)
            for (int j = 7; j >= 0; j--)
                if (Objects.equals(actualGame.board[i][j].getStatus(), count))
                    for (int z = 1; z < j; z++)
                        if (Objects.equals(actualGame.board[i][j - z].getStatus(), another)) {
                            if (Objects.equals(actualGame.board[i][j - z - 1].getStatus(), Status.EMPTY))
                                actualGame.board[i][j - z - 1].setStatus(actualGame.helper);
                        } else break;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Objects.equals(actualGame.board[i][j].getStatus(), count))
                    for (int z = 1; z < 7 - i; z++)
                        if (Objects.equals(actualGame.board[i + z][j].getStatus(), another)) {
                            if (Objects.equals(actualGame.board[i + z + 1][j].getStatus(), Status.EMPTY))
                                actualGame.board[i + z + 1][j].setStatus(actualGame.helper);
                        } else break;


        for (int i = 7; i >= 0; i--)
            for (int j = 7; j >= 0; j--)
                if (Objects.equals(actualGame.board[i][j].getStatus(), count))
                    for (int z = 1; z < i; z++)
                        if (Objects.equals(actualGame.board[i - z][j].getStatus(), another)) {
                            if (Objects.equals(actualGame.board[i - z - 1][j].getStatus(), Status.EMPTY))
                                actualGame.board[i - z - 1][j].setStatus(actualGame.helper);
                        } else break;


        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Objects.equals(actualGame.board[i][j].getStatus(), count))
                    for (int z = 1; z < Math.min(7 - j, 7 - i); z++)
                        if (Objects.equals(actualGame.board[i + z][j + z].getStatus(), another)) {
                            if (Objects.equals(actualGame.board[i + z + 1][j + z + 1].getStatus(), Status.EMPTY))
                                actualGame.board[i + z + 1][j + z + 1].setStatus(actualGame.helper);
                        } else break;


        for (int i = 0; i < 8; i++)
            for (int j = 7; j >= 0; j--)
                if (Objects.equals(actualGame.board[i][j].getStatus(), count))
                    for (int z = 1; z < Math.min(j, 7 - i); z++)
                        if (Objects.equals(actualGame.board[i + z][j - z].getStatus(), another)) {
                            if (Objects.equals(actualGame.board[i + z + 1][j - z - 1].getStatus(), Status.EMPTY))
                                actualGame.board[i + z + 1][j - z - 1].setStatus(actualGame.helper);
                        } else break;


        for (int i = 7; i >= 0; i--)
            for (int j = 7; j >= 0; j--) {
                if (Objects.equals(actualGame.board[i][j].getStatus(), count))
                    for (int z = 1; z < Math.min(i, j); z++)
                        if (Objects.equals(actualGame.board[i - z][j - z].getStatus(), another)) {
                            if (Objects.equals(actualGame.board[i - z - 1][j - z - 1].getStatus(), Status.EMPTY))
                                actualGame.board[i - z - 1][j - z - 1].setStatus(actualGame.helper);
                        } else break;

            }
        for (int i = 7; i >= 0; i--)
            for (int j = 0; j < 8; j++)
                if (Objects.equals(actualGame.board[i][j].getStatus(), count))
                    for (int z = 1; z < Math.min(7 - j, i); z++)
                        if (Objects.equals(actualGame.board[i - z][j + z].getStatus(), another)) {
                            if (Objects.equals(actualGame.board[i - z - 1][j + z + 1].getStatus(), Status.EMPTY))
                                actualGame.board[i - z - 1][j + z + 1].setStatus(actualGame.helper);
                        } else break;

        ender();
    }

    private void ender() {
        int black = 0;
        int white = 0;
        int num = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Objects.equals(actualGame.board[i][j].getStatus(), Status.BLACK))
                    black++;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Objects.equals(actualGame.board[i][j].getStatus(), Status.WHITE))
                    white++;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (actualGame.board[i][j].getStatus() == actualGame.helper)
                    num++;

        if ((num == 0)) {
            JOptionPane.showMessageDialog(this, "Black " + black + "\n" + "White " + white,
                    "End", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }


}
