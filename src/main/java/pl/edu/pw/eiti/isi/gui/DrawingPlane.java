package pl.edu.pw.eiti.isi.gui;

import pl.edu.pw.eiti.isi.model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingPlane extends JPanel {
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Color LINES_COLOR = Color.DARK_GRAY;
    private static final Color LIFE_COLOR = Color.GREEN;

    private static DrawingPlane instance = new DrawingPlane();

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

        g.setColor(LINES_COLOR);
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                g.drawRect(i * 20, j * 20, 20, 20);
                if (board.getCell(i, j)) {
                    g.setColor(LIFE_COLOR);
                    g.fillRect(i * 20 + 1, j * 20 + 1, 20 - 1, 20 - 1);
                    g.setColor(LINES_COLOR);
                }

            }
        }
    }

    private static void clearPlane(Graphics2D g, int imageWidth, int imageHeight) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, imageWidth, imageHeight);
    }
}
