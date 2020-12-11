package start_screen;

import game.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JPanel panel1;
    private JButton createButton;
    private JButton buttonBoardColorSelect;
    private JPanel colorPanel;
    private JSpinner spinnerBoardSizeY;
    private JSpinner spinnerBoardSizeX;
    private JSpinner spinnerCellSize;


    public MainWindow() {
        init();
    }

    private void init() {
        spinnerBoardSizeX.setValue(50);
        spinnerBoardSizeY.setValue(50);
        spinnerCellSize.setValue(10);


        colorPanel.setBackground(Color.BLACK);
        buttonBoardColorSelect.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
            if (color != null) {
                colorPanel.setBackground(color);
            }
        });

        createButton.addActionListener(e -> {
            showGameWindow();
        });

        setContentPane(panel1);
        setBounds(600, 200, 500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    private void showGameWindow() {
        int cellsX = (int) spinnerBoardSizeX.getValue();
        int cellsY = (int) spinnerBoardSizeY.getValue();
        int cellsSize = (int) spinnerCellSize.getValue();
        GameWindow gameWindow = new GameWindow(cellsY, cellsX, colorPanel.getBackground(), cellsSize);
        gameWindow.setVisible(true);
    }
}
