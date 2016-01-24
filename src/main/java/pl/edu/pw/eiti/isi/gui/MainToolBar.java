package pl.edu.pw.eiti.isi.gui;

import javax.swing.*;

public class MainToolBar extends JToolBar {
    private static MainToolBar instance = new MainToolBar();

    private JButton toolNextFrame;
    private JButton toolPause;
    private JButton toolAuto;
    private JSpinner toolInterval;
    private final Timer timer;

    public static MainToolBar getInstance() {
        return instance;
    }

    private MainToolBar() {
        addToolNextFrame();
        addToolPause();
        addToolAuto();
        addToolInterval();
        timer = new Timer(Integer.MAX_VALUE, e -> MainWindow.getInstance().nextFrame());
    }

    private void addToolNextFrame() {
        toolNextFrame = new JButton("Jedna klatka");
        toolNextFrame.addActionListener(e -> MainWindow.getInstance().nextFrame());
        add(toolNextFrame);
    }

    private void addToolPause() {
        toolPause = new JButton("Pauza");
        toolPause.addActionListener(e -> {
            timer.stop();
            setAutoPlay(false);
        });
        toolPause.setVisible(false);
        add(toolPause);
    }

    private void addToolAuto() {
        toolAuto = new JButton("Auto");
        toolAuto.addActionListener(e -> {
            setAutoPlay(true);
            Integer delay = (Integer) (toolInterval.getValue());
            timer.setDelay(delay);
            timer.setInitialDelay(delay);
            timer.start();
        });
        add(toolAuto);
    }

    private void setAutoPlay(boolean autoPlay) {
        toolNextFrame.setVisible(!autoPlay);
        toolPause.setVisible(autoPlay);
        toolAuto.setVisible(!autoPlay);
    }

    private void addToolInterval() {
        toolInterval = new JSpinner(new SpinnerNumberModel(300, 10, Integer.MAX_VALUE, 10));
        toolInterval.addChangeListener(e -> timer.setDelay((Integer) (toolInterval.getValue())));

        add(new JLabel("Czas: "));
        add(toolInterval);
        add(new JLabel(" ms"));
    }

}
