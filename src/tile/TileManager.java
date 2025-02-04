package tile;

import Main.MyPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    MyPanel panel;
    Tile[] tiles;
    int[][] mapTileNum;


    public TileManager(MyPanel panel) {
        this.panel = panel;
        tiles = new Tile[10];
        mapTileNum = new int[panel.MAX_SCREEN_ROWS][panel.MAX_SCREEN_COLUMNS];

        getTileImage();
        loadMapTiles("Maps/map01.txt");
    }

    public void getTileImage() {
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Bricks.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Ski.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMapTiles(String mapPath) {
        // for inserting te map
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(mapPath); // upload
            BufferedReader reader = new BufferedReader(new InputStreamReader(is)); // read

            int col = 0;
            int row = 0;
            while (col < panel.MAX_SCREEN_COLUMNS && row < panel.MAX_SCREEN_ROWS) {
                String line = reader.readLine(); // to read the lines of the map
                while (col < panel.MAX_SCREEN_COLUMNS) {
                    String[] numbers = line.split(" "); // turns the line into an array of string of numbers
                    int x = Integer.parseInt(numbers[col]); // turns the string number into int
                    mapTileNum[row][col] = x; // updates the data of the mapTileNum
                    col++; // to get to the next item
                }
                col = 0;
                row++;

            }
            reader.close();

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2d) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < panel.MAX_SCREEN_COLUMNS && row < panel.MAX_SCREEN_ROWS) {

            int tileNum = mapTileNum[row][col];

            g2d.drawImage(tiles[tileNum].image, x, y, panel.TILE_SIZE, panel.TILE_SIZE, null);
            x += panel.TILE_SIZE;
            col++;

            if (col == panel.MAX_SCREEN_COLUMNS) {
                col = 0;
                x = 0;
                row++;
                y += panel.TILE_SIZE;
            }
        }
    }
}
