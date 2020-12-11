package test;

import game.GameBoard;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class GameBoardTests {
    private static GameBoard gameBoard = new GameBoard(10, 10, Color.BLACK, 10);

    @Test
    public void testGameBoardInit() {
        GameBoard gm = new GameBoard(10, 10, Color.BLACK, 10);
        for (int i = 0; i < gm.getBoardSizeY()/gm.getCellSize(); i++) {
            for (int j = 0; j < gm.getBoardSizeX()/ gm.getCellSize(); j++) {
                if (gm.getCellState(i, j) == 1) {
                    Assert.fail();
                }
            }
        }
    }

    @Test
    public void testNeighboursNum() {
        gameBoard.changeCellState(0, 0);
        gameBoard.changeCellState(1, 1);
        Assert.assertEquals(2, gameBoard.getNeighboursNum(0, 1));
    }

    @Test
    public void testGameRules() {
        gameBoard.changeCellState(5, 5);
        gameBoard.nextStep();
        Assert.assertEquals(gameBoard.getCellState(5, 5), 0);
    }
}
