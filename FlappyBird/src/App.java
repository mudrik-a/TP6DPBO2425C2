import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    // Komponen yang dideklarasikan di GUI Designer
    private JPanel mainPanel;
    private JButton playButton;
    private JButton exitButton;

    // Konstruktor
    public App() {
        setTitle("Flappy Bird - Main Menu");
        setContentPane(mainPanel);
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Tombol Play
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                startGame();
                dispose();
            }
        });

        // Tombol Exit
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void startGame() {
        // Logika untuk memulai game Flappy Bird
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Logic logika = new Logic();
        View view = new View(logika);
        logika.setView(view);

        view.requestFocus();

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }

}