import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth/2;
    int playerStartPosY = frameHeight/2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    int pipeVelocityX = -2;

    //Variabel status game
    boolean gameOver = false;
    int score = 0; // Tambahkan untuk implementasi scoring (opsional, tapi sering berjalan beriringan dengan collision)

    public Logic(){
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!gameOver) { // Hanya buat pipa jika game belum over
                    System.out.println("Pipa");
                    placePipes();
                }
            }
        });
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
        pipesCooldown.start();;
    }

    public void setView(View view){
        this.view = view;
    }

    public Player getPlayer(){
        return player;
    }

    public ArrayList<Pipe> getPipes(){return pipes;}

    //Getter untuk status game dan skor
    public boolean isGameOver(){
        return gameOver;
    }

    public int getScore(){
        return score;
    }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth,pipeHeight,upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth,pipeHeight,lowerPipeImage);
        pipes.add(lowerPipe);
    }

    //Metode untuk deteksi tabrakan
    public boolean collision(Pipe pipe){
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWitdth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

        return playerRect.intersects(pipeRect);
    }

    public void move(){

        if(gameOver){ // Hentikan semua pergerakan jika game over
            return;
        }

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY((player.getPosY() + player.getVelocityY()));
        player.setPosY(Math.max(player.getPosY(), 0));

        int groundLevel = frameHeight - player.getHeight(); // Hitung batas bawah
        // 1. Deteksi Jatuh ke Batas Bawah (Game Over)
        if (player.getPosY() > groundLevel) {
            player.setPosY(groundLevel); // Tetapkan posisi Y ke batas bawah
            player.setVelocityY(0);     // Hentikan gerakan vertikal
            gameOver = true; // SET GAME OVER
            pipesCooldown.stop();
        }

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // 2. Deteksi Tabrakan Pipa (Game Over)
            if(collision(pipe)){
                gameOver = true; // SET GAME OVER
                pipesCooldown.stop();
            }

            // 3. Penghitungan Skor
            if(!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()){
                if(pipe.getImage() == upperPipeImage) {
                    score++;
                    System.out.println("Score: " + score);
                    // BARU: Perbarui JLabel saat skor berubah
                    if(view != null) {
                        view.updateScoreLabel();
                    }
                }
                pipe.setPassed(true);
            }
        }
    }

    public void resetGame(){
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        pipes.clear(); // Hapus semua pipa yang ada

        score = 0;
        gameOver = false;

        // Mulai kembali timers
        gameLoop.start();
        pipesCooldown.start();

        //Perbarui JLabel saat game di-reset
        if(view != null) {
            view.updateScoreLabel();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        if(view != null){
            view.repaint();
            view.updateScoreLabel();
        }
    }
    @Override
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        if(!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocityY(-10);
        }
        //Restart Game
        if(gameOver && e.getKeyCode() == KeyEvent.VK_R){
            resetGame();
        }
    }
    public void keyReleased(KeyEvent e){}
}
