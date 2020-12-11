package game;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private int[][] cells;
    private int boardSizeX;
    private int boardSizeY;
    private Color cellColor;

    private int cellSize;

    private final static int CELL_DEAD = 0;
    private final static int CELL_ALIVE = 1;

    public GameBoard(int cellsNumY, int cellsNumX, Color boardColor, int cellSize) {
        cells = new int[cellsNumY][cellsNumX];
        this.cellSize = cellSize;
        boardSizeX = cellsNumX * cellSize;
        boardSizeY = cellsNumY * cellSize;
        cellColor = Color.WHITE;
        setBackground(boardColor);

        initCells();

        setMinimumSize(new Dimension(boardSizeX, boardSizeY));
        setMaximumSize(new Dimension(boardSizeX, boardSizeY));
    }

    public void makeStep() {
        nextStep();
        repaint();
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getBoardSizeX() {
        return boardSizeX;
    }

    public int getBoardSizeY() {
        return boardSizeY;
    }

    public int getCellState(int i, int j) {
        try {
            return cells[i][j];
        } catch (IndexOutOfBoundsException ex) {
            return -1;
        }
    }

    public void changeCellState(int i, int j) {
        try {
            cells[i][j] = cells[i][j] == CELL_ALIVE? CELL_DEAD: CELL_ALIVE;
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    private void initCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = CELL_DEAD;
            }
        }
    }

    private int getNeighboursNum(int i, int j) {
        int m = cells.length;
        int n = cells[0].length;
        int neighbours = 0;

        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                int newI = (i + k) % m;
                int newJ = (j + l) % n;
                if (cells[newI >= 0? newI: m - 1][newJ >= 0? newJ: n - 1] == CELL_ALIVE) {
                    neighbours++;
                }
            }
        }
        neighbours -= cells[i][j];
        return neighbours;
    }

    private void nextStep() {
        int[][] newCells = new int[cells.length][cells[0].length];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                int n = getNeighboursNum(i, j);
                int x = cells[i][j];

                if ((n == 2 || n == 3) && x == CELL_ALIVE) {
                    newCells[i][j] = CELL_ALIVE;
                } else if ((n < 1 || n > 3) && x == CELL_ALIVE) {
                    newCells[i][j] = CELL_DEAD;
                } else if (n == 3 && x == CELL_DEAD) {
                    newCells[i][j] = CELL_ALIVE;
                }
            }
        }
        cells = newCells;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor((getBackground()));
        g2d.fillRect(0, 0, boardSizeX, boardSizeY);
        g2d.setColor(cellColor);

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] == CELL_ALIVE) {
                    g2d.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}
