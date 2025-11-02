import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private Logic logic;
    private JLabel scoreLabel;

    public  View(Logic logic){
        this.logic = logic;
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.cyan);

        //Atur Layout ke BorderLayout untuk meletakkan JLabel
        setLayout(new BorderLayout());
        //Inisialisasi dan Konfigurasi scoreLabel
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER); // Teks di tengah
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        scoreLabel.setForeground(Color.white);
        // Tambahkan label diatas
        add(scoreLabel, BorderLayout.NORTH);

        setFocusable(true);
        addKeyListener(logic);
    }

    //Metode untuk memperbarui score
    public void updateScoreLabel() {
        // Hanya update jika game belum over, atau setelah game over untuk menampilkan skor akhir
        if (logic != null) {
            scoreLabel.setText("Score: " + logic.getScore());
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        Player player = logic.getPlayer();
        if(player != null){
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWitdth() , player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if(pipes != null){
            for(int i = 0;i < pipes.size();i++){
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }
        // Teks Game Over (Jika Anda mengimplementasikannya di langkah sebelumnya)
        if(logic.isGameOver()){
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            String gameOverText = "GAME OVER";
            String restartText = "Press R to Restart";
            FontMetrics fm = g.getFontMetrics();
            int xGameOver = (width - fm.stringWidth(gameOverText)) / 2;
            int yGameOver = height / 2;
            g.drawString(gameOverText, xGameOver, yGameOver);

            // Tambahkan instruksi restart
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics fm2 = g.getFontMetrics();
            int xRestart = (width - fm2.stringWidth(restartText)) / 2;
            int yRestart = yGameOver + 40;
            g.drawString(restartText, xRestart, yRestart);
        }
    }
}
