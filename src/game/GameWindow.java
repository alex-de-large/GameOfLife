package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends JFrame {

    private GameBoard gameBoard;
    private int currentMode;
    private Timer timer;
    private int cellsNumX;
    private int cellsNumY;

    private final static int EDIT_MODE = 0;
    private final static int GAME_MODE = 1;


    public GameWindow(int cellsNumY, int cellsNumX, Color boardColor, int cellsSize) {
        this.cellsNumX = cellsNumX;
        this.cellsNumY = cellsNumY;
        gameBoard = new GameBoard(cellsNumY, cellsNumX, boardColor, cellsSize);
        gameBoard.addMouseListener(gameBoardListener);
        gameBoard.addMouseMotionListener(mouseMotionListener);
        currentMode = EDIT_MODE;

        setContentPane(gameBoard);

        initMenu();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    timer.cancel();
                } catch (Exception ignored) {

                }
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(300, 300);
        Dimension dim = gameBoard.getMinimumSize();
        setSize(dim.width, dim.height + getJMenuBar().getHeight() + 45);
        setMaximumSize(gameBoard.getMinimumSize());;
    }

    private MouseListener gameBoardListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            onMouseAction(e, false);
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

    private MouseMotionListener mouseMotionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            onMouseAction(e, true);
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    };

    private void onMouseAction(MouseEvent e, boolean deadCellsOnly) {
        if (currentMode == EDIT_MODE) {
            int i = e.getY() / (gameBoard.getCellSize());
            int j = e.getX() / (gameBoard.getCellSize());
            if (deadCellsOnly) {
                int cellState = gameBoard.getCellState(i, j);
                if (cellState == 0) {
                    gameBoard.changeCellState(i, j);
                }
            } else {
                gameBoard.changeCellState(i, j);
            }
            gameBoard.repaint();
        }
    }

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
        timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, 800, 800);

    }

    private void stopGameMode() {
        timer.cancel();
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            gameBoard.makeStep();
            gameBoard.revalidate();
        }
    }
}
