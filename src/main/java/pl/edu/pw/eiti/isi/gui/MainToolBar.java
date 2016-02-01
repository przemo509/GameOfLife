package pl.edu.pw.eiti.isi.gui;

import javax.swing.*;

public class MainToolBar extends JToolBar {
    private static MainToolBar instance = new MainToolBar();

    private JLabel framesCounter;
    private JButton toolNextFrame;
    private JButton toolPause;
    private JButton toolAuto;
    private JSpinner toolInterval;
    private JLabel cellsCounter;
    private final Timer timer;

    public static MainToolBar getInstance() {
        return instance;
    }

    private MainToolBar() {
        addFramesCounter();
        addToolNextFrame();
        addToolPause();
        addToolAuto();
        addToolInterval();
        addCellsCounter();
        timer = new Timer(Integer.MAX_VALUE, e -> nextFrame());
    }

    private void addFramesCounter() {
        add(new JLabel("Klatka: "));
        framesCounter = new JLabel("-");
        add(framesCounter);
        addSeparator();
    }

    private void addToolNextFrame() {
        toolNextFrame = new JButton("Następna");
        toolNextFrame.addActionListener(e -> nextFrame());
        add(toolNextFrame);
    }

    private void nextFrame() {
        int frame = MainWindow.getInstance().nextFrame();
        int aliveCells = MainWindow.getInstance().getBoard().getAliveCells();
        setFrameCounter(frame);
        setAliveCells(aliveCells);
    }

    public void setFrameCounter(int frame) {
        framesCounter.setText(String.format("%03d", frame));
    }

    public void setAliveCells(int cells) {
        cellsCounter.setText(String.format("%06d", cells));
    }

    private void addToolPause() {
        toolPause = new JButton("Pauza");
        toolPause.addActionListener(e -> pause());
        toolPause.setVisible(false);
        add(toolPause);
    }

    public void pause() {
        timer.stop();
        setAutoPlay(false);
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

        addSeparator();
        add(new JLabel("Czas: "));
        add(toolInterval);
        add(new JLabel(" ms"));
        addSeparator();
    }

    private void addCellsCounter() {
        add(new JLabel("Liczba żywych komórek: "));
        cellsCounter = new JLabel("-");
        add(cellsCounter);
    }
}
