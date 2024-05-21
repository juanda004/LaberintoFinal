import javax.swing.*;
import java.awt.*;

public class run extends JFrame {
    private Laberinto2 laberinto;

    public run(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button11x11 = new JButton("11x11");
        JButton button8x8 = new JButton("8x8");
        JButton button10x10 = new JButton("10x10");

        button11x11.addActionListener(e -> iniciarLaberinto(11, 11));
        button8x8.addActionListener(e -> iniciarLaberinto(8, 8));
        button10x10.addActionListener(e -> iniciarLaberinto(10, 10));

        buttonPanel.add(button11x11);
        buttonPanel.add(button8x8);
        buttonPanel.add(button10x10);

        add(buttonPanel, BorderLayout.NORTH);

        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(salirButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void iniciarLaberinto(int rows, int cols) {
        if (laberinto != null) {
            remove(laberinto);
        }
        laberinto = new Laberinto2(rows, cols);
        add(laberinto, BorderLayout.CENTER);
        revalidate();
        laberinto.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            run menu = new run ("Laberinto Game");
            menu.setSize(600, 600);
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setVisible(true);
        });
    }
}
