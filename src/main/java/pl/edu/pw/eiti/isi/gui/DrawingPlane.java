package pl.edu.pw.eiti.isi.gui;

import pl.edu.pw.eiti.isi.model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingPlane extends JPanel {
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Color LINES_COLOR = Color.LIGHT_GRAY;
    private static final Color LIFE_COLOR = new Color(53, 162, 255);
    private static final Color[] RAINBOW = {
            BACKGROUND_COLOR,
            new Color(255, 198, 174),
            new Color(255, 209, 130),
            new Color(251, 231, 68),
            new Color(193, 255, 33),
            new Color(75, 255, 0),
            new Color(0, 255, 0),
            new Color(0, 255, 255),
            new Color(0, 75, 255)
    };

    private static DrawingPlane instance = new DrawingPlane();
    private int cellSize;

    public static DrawingPlane getInstance() {
        return instance;
    }

    private DrawingPlane() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard((Graphics2D) g, MainWindow.getInstance().getBoard(), getWidth(), getHeight());
    }

    private void drawBoard(Graphics2D g, Board board, int imageWidth, int imageHeight) {
        BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D bg = bi.createGraphics();
        drawBoardOnGraphics(bg, board, imageWidth, imageHeight);
        g.drawImage(bi, null, 0, 0);
        bg.dispose();
    }

    private void drawBoardOnGraphics(Graphics2D g, Board board, int imageWidth, int imageHeight) {
        clearPlane(g, imageWidth, imageHeight);
        calculateCellSize(board.getWidth(), board.getHeight(), imageWidth, imageHeight);

        g.setColor(LINES_COLOR);
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
                if (MainToolBar.getInstance().showNeighbourColors()) {
                    g.setColor(RAINBOW[board.getNeighbours(i, j)]);
                    g.fillRect(i * cellSize + 1, j * cellSize + 1, cellSize - 1, cellSize - 1);
                    g.setColor(LINES_COLOR);
                } else {
                    if (board.getCell(i, j)) {
                        g.setColor(LIFE_COLOR);
                        g.fillRect(i * cellSize + 1, j * cellSize + 1, cellSize - 1, cellSize - 1);
                        g.setColor(LINES_COLOR);
                    }
                }
            }
        }
    }

    private static void clearPlane(Graphics2D g, int imageWidth, int imageHeight) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, imageWidth, imageHeight);
    }

    private void calculateCellSize(int boardWidth, int boardHeight, int imageWidth, int imageHeight) {
        cellSize = (int)Math.min((float)imageWidth / boardWidth, (float)imageHeight / boardHeight);
    }

    public int getCellSize() {
        return cellSize;
    }
}
