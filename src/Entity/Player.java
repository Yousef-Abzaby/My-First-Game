package Entity;

import Main.KeyHandler;
import Main.MyPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    MyPanel panel;
    KeyHandler keyHandler;

    public Player(MyPanel panel, KeyHandler keyHandler) {
        this.panel = panel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "none";
    }

    // method for getting the images of the player
    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/right2.png")));
            standing = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/notMoving.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        }
        if(keyHandler.downPressed) {
            direction = "down";
            y += speed;
        }
        if(keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        }
        if(keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }
        if(!keyHandler.rightPressed && !keyHandler.leftPressed && !keyHandler.downPressed && !keyHandler.upPressed) {
            direction = "none";
        }
        spriteCounter++;
        if(spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2d) {

//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(x, y, panel.TILE_SIZE, panel.TILE_SIZE);
        BufferedImage image = null;
        // switch statement for updating the image based on the direction
        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                else if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "none":
                image = standing;
        }
        g2d.drawImage(image, x, y, panel.TILE_SIZE, panel.TILE_SIZE, null);
    }
}
