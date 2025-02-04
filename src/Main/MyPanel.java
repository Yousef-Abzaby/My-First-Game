package Main;

import Entity.Player;
import tile.TileManager;

import java.awt.*;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {
    final int ORIGINAL_TILE_SIZE = 16;
    final int SCALE = 3;
    public int TILE_SIZE = SCALE * ORIGINAL_TILE_SIZE;
    public final int MAX_SCREEN_COLUMNS = 16;
    public final int MAX_SCREEN_ROWS = 12;
    public final int screenWidth = TILE_SIZE * MAX_SCREEN_COLUMNS;
    public final int screenHeight = TILE_SIZE * MAX_SCREEN_ROWS;

    // FPS
    final int FPS = 60;

    Thread myThread;
    KeyHandler keyHandler = new KeyHandler();
    TileManager tileManager = new TileManager(this);
    Player player = new Player(this, keyHandler);

    MyPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        // to make the Panel listen to updates and implements the keyHandler functions
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void StartGameThread() {
        this.myThread = new Thread(this);
        this.myThread.start();
    }

    public void run() {
        double TimeInterval = 1000000000 / FPS; // also divides a second into 60 parts
        double delta = 0; // to calculate the delta between each frame update
        long lastTime = System.nanoTime(); // to get the current time at the start of the loop
        long currentTime;


        while (myThread != null) {
            currentTime = System.nanoTime(); // to get the current time
            delta += (currentTime - lastTime) / TimeInterval; // assign the change in time to delta and normalize it relative to desired FPS
            lastTime = currentTime; // updates last time
            if (delta >= 1) { // ensures that enough time has passed
                update();
                repaint();
                delta--; // to account for the frame that was just painted
            }
        }
    }

    // the Update method

    public void update() {

        player.update();
    }

    // the drawing method

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // default for running the repaint method
        Graphics2D g2d = (Graphics2D) g; // to access the 2d functions
        tileManager.draw(g2d); // to draw the background
        player.draw(g2d); // to draw the player
        g2d.dispose(); // to save soe memory
    }
}
