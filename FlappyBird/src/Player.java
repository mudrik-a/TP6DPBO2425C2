import java.awt.*;

public class Player {
    private int posX;
    private int posY;
    private int witdth;
    private int height;
    private Image image;
    private int velocityY;

    public Player(int posX,int posY,int witdth,int height,Image image){
        this.posX = posX;
        this.posY = posY;
        this.witdth = witdth;
        this.height = height;
        this.image = image;

        this.velocityY = -0;
    }
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWitdth() {
        return witdth;
    }

    public void setWitdth(int witdth) {
        this.witdth = witdth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public int getVelocityY(){
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}
