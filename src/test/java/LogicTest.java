import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class LogicTest {
    @Test
    void boardTest() {
        Board firstTestBoard = new Board();

        firstTestBoard.add(4, 5);
        firstTestBoard.switchTurn();
        firstTestBoard.add(3, 5);
        firstTestBoard.switchTurn();
        firstTestBoard.add(2, 5);
        firstTestBoard.switchTurn();
        firstTestBoard.add(3, 6);
        firstTestBoard.switchTurn();
        firstTestBoard.add(2, 2);
        firstTestBoard.switchTurn();
        firstTestBoard.add(3, 2);
        firstTestBoard.switchTurn();
        firstTestBoard.add(2, 1);
        firstTestBoard.switchTurn();
        firstTestBoard.add(1, 1);
        firstTestBoard.switchTurn();
        firstTestBoard.add(2, 4);
        firstTestBoard.switchTurn();
        firstTestBoard.add(1, 5);
        firstTestBoard.switchTurn();

        firstTestBoard.add(3, 7);
        firstTestBoard.switchTurn();
        firstTestBoard.add(4, 7);
        firstTestBoard.switchTurn();
        firstTestBoard.add(5, 7);
        firstTestBoard.switchTurn();
        firstTestBoard.add(5, 2);
        firstTestBoard.switchTurn();
        firstTestBoard.add(4, 2);
        firstTestBoard.switchTurn();
        firstTestBoard.add(5, 3);
        firstTestBoard.switchTurn();
        firstTestBoard.add(2, 3);
        firstTestBoard.switchTurn();
        firstTestBoard.add(1, 0);
        firstTestBoard.switchTurn();
        firstTestBoard.add(0, 0);
        firstTestBoard.switchTurn();
        firstTestBoard.add(0, 1);
        firstTestBoard.switchTurn();

        firstTestBoard.add(0, 5);
        firstTestBoard.switchTurn();
        firstTestBoard.add(0, 6);
        firstTestBoard.switchTurn();
        firstTestBoard.add(0, 7);
        firstTestBoard.switchTurn();
        firstTestBoard.add(2, 6);
        firstTestBoard.switchTurn();
        firstTestBoard.add(1, 4);
        firstTestBoard.switchTurn();
        firstTestBoard.add(3, 1);
        firstTestBoard.switchTurn();
        firstTestBoard.add(4, 1);
        firstTestBoard.switchTurn();
        firstTestBoard.add(3, 0);
        firstTestBoard.switchTurn();
        firstTestBoard.add(6, 3);
        firstTestBoard.switchTurn();
        firstTestBoard.add(5, 4);
        firstTestBoard.switchTurn();

        firstTestBoard.add(0, 2);
        firstTestBoard.switchTurn();
        firstTestBoard.add(7, 2);
        firstTestBoard.switchTurn();
        firstTestBoard.add(2, 0);
        firstTestBoard.switchTurn();
        firstTestBoard.add(1, 6);
        firstTestBoard.switchTurn();
        firstTestBoard.add(1, 7);
        firstTestBoard.switchTurn();
        firstTestBoard.add(0, 4);
        firstTestBoard.switchTurn();
        firstTestBoard.add(0, 3);
        firstTestBoard.switchTurn();
        firstTestBoard.add(1, 2);
        firstTestBoard.switchTurn();
        firstTestBoard.add(1, 3);
        firstTestBoard.switchTurn();
        firstTestBoard.add(2, 7);
        firstTestBoard.switchTurn();

        firstTestBoard.add(6, 4);
        firstTestBoard.switchTurn();
        firstTestBoard.add(6, 5);
        firstTestBoard.switchTurn();
        firstTestBoard.add(5, 5);
        firstTestBoard.switchTurn();
        firstTestBoard.add(5, 6);
        firstTestBoard.switchTurn();
        firstTestBoard.add(6, 7);
        firstTestBoard.switchTurn();
        firstTestBoard.add(7, 7);
        firstTestBoard.switchTurn();
        firstTestBoard.add(7, 3);
        firstTestBoard.switchTurn();
        firstTestBoard.add(6, 2);
        firstTestBoard.switchTurn();
        firstTestBoard.add(5, 1);
        firstTestBoard.switchTurn();
        firstTestBoard.add(4, 6);
        firstTestBoard.switchTurn();

        firstTestBoard.add(7, 5);
        firstTestBoard.switchTurn();
        firstTestBoard.add(7, 4);
        firstTestBoard.switchTurn();
        firstTestBoard.add(7, 6);
        firstTestBoard.switchTurn();
        firstTestBoard.add(6, 6);
        firstTestBoard.switchTurn();
        firstTestBoard.add(4, 0);
        firstTestBoard.switchTurn();
        firstTestBoard.add(6, 0);
        firstTestBoard.switchTurn();
        firstTestBoard.add(6, 1);
        firstTestBoard.switchTurn();
        firstTestBoard.add(7, 1);
        firstTestBoard.switchTurn();
        firstTestBoard.add(7, 0);
        firstTestBoard.switchTurn();
       //firstTestBoard.add(5, 0);
       //firstTestBoard.switchTurn();

        // for (int i = 0; i < firstTestBoard.board.length; i++) {
        //     for (int j = 0; j < firstTestBoard.board[0].length; j++)
        //         System.out.print(firstTestBoard.board[i][j].getStatus() + "=="+ testBoard[i][j]+" ");
        //     System.out.println();
        // }

        assertEquals(41, firstTestBoard.black);

        assertEquals(23, firstTestBoard.white);
    }
}
