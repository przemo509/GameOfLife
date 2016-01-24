package pl.edu.pw.eiti.isi;

import pl.edu.pw.eiti.isi.gui.MainWindow;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.LogManager;

public class GameOfLife {
    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration(GameOfLife.class.getResourceAsStream("/logging.properties"));

        SwingUtilities.invokeLater(() -> MainWindow.getInstance().setVisible(true));
    }
}
