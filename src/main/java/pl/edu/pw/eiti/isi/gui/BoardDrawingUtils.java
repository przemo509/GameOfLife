package pl.edu.pw.eiti.isi.gui;

import pl.edu.pw.eiti.isi.model.Board;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardDrawingUtils {

    public static void drawBoard(Graphics2D g, Board board, int imageWidth, int imageHeight) {
        BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D bg = bi.createGraphics();
        drawBoardOnGraphics(bg, board, imageWidth, imageHeight);
        g.drawImage(bi, null, 0, 0);
        bg.dispose();
    }

    private static void drawBoardOnGraphics(Graphics2D g, Board board, int imageWidth, int imageHeight) {
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearPlane(g, imageWidth, imageHeight);

        g.setColor(Color.BLACK);
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                if (board.getCell(i, j)) {
                    g.fillRect(i * 20, j * 20, 20, 20);
                } else {
                    g.drawRect(i * 20, j * 20, 20, 20);
                }

            }
        }
    }

    private static void clearPlane(Graphics2D g, int imageWidth, int imageHeight) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imageWidth, imageHeight);
    }

}
