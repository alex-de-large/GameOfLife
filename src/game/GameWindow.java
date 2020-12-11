package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.http.WebSocket;

public class GameWindow extends JFrame {

    private GameBoard gameBoard;
    private int currentMode;
    private GameThread gameThread;
    private int cellsNumX;
    private int cellsNumY;

    private final static int EDIT_MODE = 0;
    private final static int GAME_MODE = 1;


    public GameWindow(int cellsNumY, int cellsNumX, Color boardColor, int cellsSize) {
        this.cellsNumX = cellsNumX;
        this.cellsNumY = cellsNumY;
        gameBoard = new GameBoard(cellsNumY, cellsNumX, boardColor, cellsSize);
        gameBoard.addMouseListener(gameBoardListener);
        gameThread = new GameThread();
        currentMode = EDIT_MODE;

        setContentPane(gameBoard);

        initMenu();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameThread.interrupt();
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(300, 300);
        Dimension dim = gameBoard.getMinimumSize();
        setSize(dim.width + 16, dim.height + getJMenuBar().getHeight() + 62);
        setMaximumSize(gameBoard.getMinimumSize());
        setResizable(false);
    }

    private MouseListener gameBoardListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (currentMode == EDIT_MODE) {
                int i = e.getY() / (gameBoard.getCellSize());
                int j = e.getX() / (gameBoard.getCellSize());
                gameBoard.changeCellState(i, j);
                gameBoard.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    private void initMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem itemEditMode = new JMenuItem("Edit Mode");
        itemEditMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMode == GAME_MODE) {
                    stopGameMode();
                    currentMode = EDIT_MODE;
                }
            }
        });
        JMenuItem itemGameMode = new JMenuItem("Game Mode");
        itemGameMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMode == EDIT_MODE) {
                    startGameMode();
                    currentMode = GAME_MODE;
                }
            }
        });
        menu.add(itemEditMode);
        menu.add(itemGameMode);
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
    }

    private void startGameMode() {
        gameThread = new GameThread();
        gameThread.start();
    }

    private void stopGameMode() {
        gameThread.interrupt();
    }


    private class GameThread extends Thread {
        @Override
        public void run() {
            while (!interrupted()) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    interrupt();
                }
                gameBoard.makeStep();
            }
        }
    }
}
