import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Laberinto2 extends JPanel implements KeyListener {
    private int[][] tablero;
    private int playerRow = 1;
    private int playerCol = 1;
    private final Stack<Integer> stack = new Stack<>();
    private final ArrayList<Integer> arrayList = new ArrayList<>();
    private final LinkedList<Integer> linkedList = new LinkedList<>();
    private final Queue<Integer> queue = new LinkedList<>();

    public Laberinto2(int rows, int cols) {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);
        addKeyListener(this);
        setFocusable(true);
        generarMaze(rows, cols);
    }

    private void generarMaze(int rows, int cols) {
        tablero = new int[rows][cols];
        Random rand = new Random();
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || col == 0 || row == rows - 1 || col == cols - 1) {
                    tablero[row][col] = 1;
                } else {
                    tablero[row][col] = rand.nextInt(2);
                }
            }
        }

        tablero[1][1] = 0;
        tablero[rows - 2][cols - 2] = 2;
        playerRow = 1;
        playerCol = 1;


        stack.clear();
        arrayList.clear();
        linkedList.clear();
        queue.clear();

        for (int row = 0; row < tablero.length; row++) {
            for (int col = 0; col < tablero[row].length; col++) {
                if (tablero[row][col] == 1 || tablero[row][col] == 2) {
                    stack.push(row * tablero[0].length + col);
                    arrayList.add(row * tablero[0].length + col);
                    linkedList.add(row * tablero[0].length + col);
                    queue.offer(row * tablero[0].length + col);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = getWidth() / tablero[0].length;
        int cellHeight = getHeight() / tablero.length;

        for (int row = 0; row < tablero.length; row++) {
            for (int col = 0; col < tablero[row].length; col++) {
                if (tablero[row][col] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                } else if (tablero[row][col] == 2) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                }
            }
        }

        g.setColor(Color.GREEN);
        g.fillRect(playerCol * cellWidth, playerRow * cellHeight, cellWidth, cellHeight);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            if (tablero[playerRow - 1][playerCol] == 0 || tablero[playerRow - 1][playerCol] == 2) {
                playerRow--;
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (tablero[playerRow + 1][playerCol] == 0 || tablero[playerRow + 1][playerCol] == 2) {
                playerRow++;
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (tablero[playerRow][playerCol - 1] == 0 || tablero[playerRow][playerCol - 1] == 2) {
                playerCol--;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (tablero[playerRow][playerCol + 1] == 0 || tablero[playerRow][playerCol + 1] == 2) {
                playerCol++;
            }
        }
        if (tablero[playerRow][playerCol] == 2) {
            JOptionPane.showMessageDialog(null, "¡Has ganado!");
            System.exit(0);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
