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

    {
        $$$setupUI$$$();
    }

    /**
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
        panel1.add(panel2, BorderLayout.SOUTH);
        createButton = new JButton();
        createButton.setText("Create");
        panel2.add(createButton);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel1.add(panel3, BorderLayout.CENTER);
        final JLabel label1 = new JLabel();
        label1.setText("Cells num:");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel3.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("M:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel3.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("N:");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel3.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Board color:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel3.add(label4, gbc);
        buttonBoardColorSelect = new JButton();
        buttonBoardColorSelect.setText("...");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(buttonBoardColorSelect, gbc);
        colorPanel = new JPanel();
        colorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(colorPanel, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Cell size:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel3.add(label5, gbc);
        spinnerBoardSizeY = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spinnerBoardSizeY, gbc);
        spinnerBoardSizeX = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spinnerBoardSizeX, gbc);
        spinnerCellSize = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spinnerCellSize, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
