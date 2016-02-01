package pl.edu.pw.eiti.isi.gui;

import pl.edu.pw.eiti.isi.gui.listener.DrawingPlaneMouseListener;
import pl.edu.pw.eiti.isi.model.Board;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainWindow extends JFrame {
    private static final Logger logger = Logger.getLogger(MainWindow.class.getName());
    private static MainWindow instance = new MainWindow();

    private final DrawingPlane drawingPlane = DrawingPlane.getInstance();
    private Board board;

    public static MainWindow getInstance() {
        return instance;
    }

    private MainWindow() {
        super("GameOfLife");
        setSize(800, 600);
        setLocationRelativeTo(null);

        addComponents();

        drawingPlane.addMouseListener(DrawingPlaneMouseListener.getInstance());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        newEmptyBoard(30, 15, 0);
    }

    private void addComponents() {
        setJMenuBar(MainMenuBar.getInstance());

        setLayout(new BorderLayout());
        add(MainToolBar.getInstance(), BorderLayout.PAGE_START);
        add(drawingPlane, BorderLayout.CENTER);
    }

    public void onMouseClick(Point position) {
        logger.log(Level.FINEST, "Clicked point: [{0},{1}]", new Object[]{position.x, position.y});
        int i = position.x / 20;
        int j = position.y / 20;
        if(i >= 0 && i < board.getWidth() && j >= 0 && j < board.getHeight()) {
            board.setCell(i, j, !board.getCell(i, j));
            board.recalculateNeighbours();
            MainToolBar.getInstance().setAliveCells(board.getAliveCells());
            repaint();
        }
    }

    public Board getBoard() {
        return board;
    }

    public void newEmptyBoard(int width, int height, int randomCells) {
        newBoard(new Board(width, height, randomCells));
    }

    public void newBoardFromFile(String path) {
        newBoard(new Board(path));
    }

    private void newBoard(Board board) {
        this.board = board;
        MainToolBar.getInstance().setFrameCounter(board.getFrame());
        MainToolBar.getInstance().setAliveCells(board.getAliveCells());
        // TODO adjust window size
        repaint();
    }

    public void saveBoardToFile(String filePath) {
        board.saveToFile(filePath);
    }

    public int nextFrame() {
        int frame = board.nextFrame();
        repaint();
        return frame;
    }
}
