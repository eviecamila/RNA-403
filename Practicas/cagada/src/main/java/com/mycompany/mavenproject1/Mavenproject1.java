package com.mycompany.mavenproject1;

import com.heatonresearch.book.introneuralnet.neural.hopfield.HopfieldNetwork;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.heatonresearch.book.introneuralnet.ch10.sp500.PredictSP500;
import com.heatonresearch.book.introneuralnet.ch10.sp500.FinancialSample;

public class Mavenproject1 {

    public static String formatBoolean(final boolean b[]) {
        final StringBuilder result = new StringBuilder();
        result.append('[');
        for (int i = 0; i < b.length; i++) {
            if (b[i]) {
                result.append("T");
            } else {
                result.append("F");
            }
            if (i != b.length - 1) {
                result.append(",");
            }
        }
        result.append(']');
        return (result.toString());
    }

    public static void main(final String args[]) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Hola, ¿qué desea hacer?");
            System.out.println("1- Hopfield");
            System.out.println("2- Applet");
            System.out.println("3- XOR");
            System.out.println("4- GeneticXOR");
            System.out.println("5- AnnealXOR");
            System.out.println("6- FinancialSample");
            System.out.println("7- PredictsSP500");

            int input = scanner.nextInt();
            switch (input) {
                case 1 ->
                    hopfield();
                case 2 ->
                    applet();
                case 3 ->
                    XOR.main(args, scanner);
                case 4 ->
                    GeneticXOR.main(args, scanner);
                case 5 ->
                    AnnealXOR.main(args, scanner);
                case 6 -> {
                    FinancialSample sample = new FinancialSample();
                    System.out.println(sample.toString());
                }
                case 7 -> {
                    PredictSP500.main(args);
                }
                default ->
                    System.out.println("Opción inválida.");
            }
        }
    }

    public static void applet() {
        JFrame frame = new JFrame("Hopfield Pattern Recognition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        PatternPanel patternPanel = new PatternPanel();
        frame.add(patternPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton trainButton = new JButton("Train");
        trainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patternPanel.train();
            }
        });
        JButton goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patternPanel.go();
            }
        });
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patternPanel.clear();
            }
        });
        JButton clearMatrixButton = new JButton("Clear Matrix");
        clearMatrixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patternPanel.clearMatrix();
            }
        });
        buttonPanel.add(trainButton);
        buttonPanel.add(goButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(clearMatrixButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void hopfield() {
        // Crear la red neuronal Hopfield
        final HopfieldNetwork network = new HopfieldNetwork(4);

        // Patrón de entrenamiento
        final boolean[] pattern1 = {true, true, false, false};

        // Entrenar la red con el patrón
        network.train(pattern1);

        // Probar la red con un patrón de entrada similar
        final boolean[] pattern2 = {true, false, false, false};
        final boolean[] result = network.present(pattern2);

        System.out.println("Entrada: " + formatBoolean(pattern2));
        System.out.println("Salida: " + formatBoolean(result));
    }
}

class PatternPanel extends JPanel implements MouseListener {

    private static final int GRID_X = 8;
    private static final int GRID_Y = 8;
    private static final int CELL_WIDTH = 20;
    private static final int CELL_HEIGHT = 20;

    private HopfieldNetwork hopfield;
    private boolean[] grid;

    public PatternPanel() {
        grid = new boolean[GRID_X * GRID_Y];
        hopfield = new HopfieldNetwork(GRID_X * GRID_Y);
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / CELL_WIDTH;
        int y = e.getY() / CELL_HEIGHT;
        if (x >= 0 && x < GRID_X && y >= 0 && y < GRID_Y) {
            int index = y * GRID_X + x;
            grid[index] = !grid[index];
            repaint();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void train() {
        hopfield.train(grid);
    }

    public void go() {
        grid = hopfield.present(grid);
        repaint();
    }

    public void clear() {
        grid = new boolean[GRID_X * GRID_Y];
        repaint();
    }

    public void clearMatrix() {
        hopfield.getMatrix().clear();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < GRID_Y; y++) {
            for (int x = 0; x < GRID_X; x++) {
                int index = y * GRID_X + x;
                if (grid[index]) {
                    g.fillRect(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
                } else {
                    g.drawRect(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
                }
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(GRID_X * CELL_WIDTH, GRID_Y * CELL_HEIGHT);
    }
}
