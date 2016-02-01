package pl.edu.pw.eiti.isi.model;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {
    private static final String DEFAULT_ALIVE_CHAR = "#";
    private static final String DEFAULT_DEAD_CHAR = "_";

    private static final int MIN_NEIGHBOURS_DEAD = 3;
    private static final int MAX_NEIGHBOURS_DEAD = 3;
    private static final int MIN_NEIGHBOURS_ALIVE = 2;
    private static final int MAX_NEIGHBOURS_ALIVE = 3;

    private static final Logger logger = Logger.getLogger(Board.class.getName());

    private int width;
    private int height;
    private boolean[][] board;
    private int frame = 0;
    private int aliveCells = 0;

    public Board(int width, int height, int randomCells) {
        this.width = width;
        this.height = height;
        board = createEmptyBoard(width, height);
        addRandomCells(randomCells);
    }

    public Board(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            width = scanner.nextInt();
            height = scanner.nextInt();
            String lifeChar = scanner.next();
            board = createEmptyBoard(width, height);
            logger.log(Level.FINEST, "Board read from file:");
            for (int j = 0; j < height; j++) {
                String line = "";
                for (int i = 0; i < width; i++) {
                    String cell = scanner.next();
                    line += cell + " ";
                    boolean isCellAlive = lifeChar.equals(cell);
                    board[i][j] = isCellAlive;
                    aliveCells += isCellAlive ? 1 : 0;
                }
                logger.log(Level.FINEST, line);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private boolean[][] createEmptyBoard(int width, int height) {
        boolean[][] board = new boolean[width][];
        for (int j = 0; j < width; j++) {
            board[j] = new boolean[height];
        }
        return board;
    }

    private void addRandomCells(int randomCells) {
        for (int i = 0; i < randomCells; i++) {
            int randX = (int) Math.floor(Math.random() * width);
            int randY = (int) Math.floor(Math.random() * height);
            setCell(randX, randY, true);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getCell(int i, int j) {
        return board[i][j];
    }

    public void setCell(int i, int j, boolean value) {
        if (!board[i][j] && value) {
            aliveCells++;
        } else if (board[i][j] && !value) {
            aliveCells--;
        }
        board[i][j] = value;
    }

    public int getFrame() {
        return frame;
    }

    public int getAliveCells() {
        return aliveCells;
    }

    public int nextFrame() {
        aliveCells = 0;
        boolean[][] newBoard = createEmptyBoard(width, height);
        logger.log(Level.FINEST, "Neighbours count at frame {0}", frame);
        logger.log(Level.FINEST, "# # # # # # # # # # # # # # # # # # # # # # # # # #");
        for (int j = 1; j < height - 1; j++) {
            String line = "#";
            for (int i = 1; i < width - 1; i++) {
                line += " " + refreshCell(i, j, newBoard);
            }
            logger.log(Level.FINEST, line + " #");
        }
        logger.log(Level.FINEST, "# # # # # # # # # # # # # # # # # # # # # # # # # #");
        frame++;
        board = newBoard;
        return frame;
    }

    private int refreshCell(int x, int y, boolean[][] newBoard) {
        int neighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // don't count cell itself, only 8 neighbours
                }
                neighbours += board[x + i][y + j] ? 1 : 0;
                // TODO cut off (optimization)
            }
        }
        boolean isAlive = board[x][y]
                ? (neighbours >= MIN_NEIGHBOURS_ALIVE && neighbours <= MAX_NEIGHBOURS_ALIVE)
                : (neighbours >= MIN_NEIGHBOURS_DEAD && neighbours <= MAX_NEIGHBOURS_DEAD);
        newBoard[x][y] = isAlive;
        aliveCells += isAlive ? 1 : 0;
        return neighbours;
    }

    public void saveToFile(String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            writer.print(width);
            writer.print(" ");
            writer.print(height);
            writer.print(" ");
            writer.println(DEFAULT_ALIVE_CHAR);

            for (int j = 0; j < height; j++) {
                String separator = "";
                for (int i = 0; i < width; i++) {
                    writer.print(separator);
                    writer.print(board[i][j] ? DEFAULT_ALIVE_CHAR : DEFAULT_DEAD_CHAR);
                    separator = " ";
                }
                writer.println();
            }
            writer.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
