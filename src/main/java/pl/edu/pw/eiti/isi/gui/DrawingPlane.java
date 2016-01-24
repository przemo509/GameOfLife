package pl.edu.pw.eiti.isi.gui;

import javax.swing.*;
import java.awt.*;

public class DrawingPlane extends JPanel {
    private static DrawingPlane instance = new DrawingPlane();

    public static DrawingPlane getInstance() {
        return instance;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BoardDrawingUtils.drawBoard((Graphics2D) g, MainWindow.getInstance().getBoard(), getWidth(), getHeight());
    }
}
