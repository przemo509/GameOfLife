package pl.edu.pw.eiti.isi.gui;

import pl.edu.pw.eiti.isi.properties.AppProperties;

import javax.swing.*;
import java.io.File;

public class MainMenuBar extends JMenuBar {
    private static MainMenuBar instance = new MainMenuBar();

    public static MainMenuBar getInstance() {
        return instance;
    }

    private MainMenuBar() {
        addFileMenu();
    }

    private void addFileMenu() {
        JMenu menu = new JMenu("Plik");

        addNewEmptyBoardMenuItem(menu);
        addBoardFromFileMenuItem(menu);
        addBoardToFileMenuItem(menu);
        menu.addSeparator();
        addExitMenuItem(menu);

        add(menu);
    }

    private void addNewEmptyBoardMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem("Nowa gra");
        menuItem.addActionListener(event -> {
            MainToolBar.getInstance().pause();
            new NewEmptyBoardDialog().setVisible(true);
        });
        menu.add(menuItem);
    }

    private void addBoardFromFileMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem("Wczytaj z pliku");
        menuItem.addActionListener(event -> {
            MainToolBar.getInstance().pause();

            JFileChooser fileChooser = new JFileChooser(AppProperties.getInstance().getLastOpenedPath());
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String openedPath = fileChooser.getSelectedFile().getAbsolutePath();
                AppProperties.getInstance().setLastOpenedPath(new File(openedPath).getParent());
                MainWindow.getInstance().newBoardFromFile(openedPath);
            }
        });
        menu.add(menuItem);
    }

    private void addBoardToFileMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem("Zapisz do pliku");
        menuItem.addActionListener(event -> {
            MainToolBar.getInstance().pause();

            JFileChooser fileChooser = new JFileChooser(AppProperties.getInstance().getLastOpenedPath());
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showSaveDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                File file = new File(filePath);
                if(file.exists()) {
                    returnValue = JOptionPane.showConfirmDialog(this, "Nadpisać wybrany plik?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
                    if(returnValue != JOptionPane.YES_OPTION) {
                        return;
                    }
                }
                AppProperties.getInstance().setLastOpenedPath(file.getParent());
                MainWindow.getInstance().saveBoardToFile(filePath);
            }
        });
        menu.add(menuItem);
    }

    private void addExitMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem("Zakończ");
        menuItem.addActionListener(event -> System.exit(0)); // TODO confirmation dialog + clean exit
        menu.add(menuItem);
    }

}
