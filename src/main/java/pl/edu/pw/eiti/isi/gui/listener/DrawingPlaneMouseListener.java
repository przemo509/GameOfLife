package pl.edu.pw.eiti.isi.gui.listener;

import pl.edu.pw.eiti.isi.gui.MainWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DrawingPlaneMouseListener implements MouseListener {
    private static final Logger logger = Logger.getLogger(DrawingPlaneMouseListener.class.getName());
    private static final DrawingPlaneMouseListener instance = new DrawingPlaneMouseListener();

    public static DrawingPlaneMouseListener getInstance() {
        return instance;
    }

    private DrawingPlaneMouseListener() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        logger.log(Level.FINEST, "click x={0}, y={1}", new Object[]{e.getX(), e.getY()});
    }

    @Override
    public void mousePressed(MouseEvent e) {
        logger.log(Level.FINEST, "press x={0}, y={1}", new Object[]{e.getX(), e.getY()});
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        logger.log(Level.FINEST, "release x={0}, y={1}", new Object[]{e.getX(), e.getY()});
        MainWindow.getInstance().onMouseClick(e.getPoint());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        logger.log(Level.FINEST, "enter x={0}, y={1}", new Object[]{e.getX(), e.getY()});
    }

    @Override
    public void mouseExited(MouseEvent e) {
        logger.log(Level.FINEST, "exit x={0}, y={1}", new Object[]{e.getX(), e.getY()});
    }
}
