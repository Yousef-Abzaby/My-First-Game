package Entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    // declaring the variables that will be used to store the images of the player
    public BufferedImage standing, up1, up2, down1, down2, left1, left2, right1, right2;
    // to store the direction
    public String direction;
    // for moving animation
    int spriteNum = 1;
    int spriteCounter = 0;
}
