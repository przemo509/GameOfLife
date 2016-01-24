package pl.edu.pw.eiti.isi.gui.listener;

import pl.edu.pw.eiti.isi.gui.MainWindow;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuCloseListener implements PopupMenuListener {
    private static final Logger logger = Logger.getLogger(MenuCloseListener.class.getName());

    public MenuCloseListener() {
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        logger.log(Level.FINEST, "1. popupMenuWillBecomeVisible");
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        logger.log(Level.FINEST, "2. popupMenuWillBecomeInvisible");
        MainWindow.getInstance().repaint();
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
        logger.log(Level.FINEST, "3. popupMenuCanceled");
    }
}
