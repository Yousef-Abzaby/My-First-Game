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
        mapTileNum = new int[panel.worldRow][panel.worldCol];

        getTileImage();
        loadMapTiles("Maps/modified_world_map.txt");
    }

    public void getTileImage() {
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Bricks.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Ski.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Tree.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Sand.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tiles/Earth.png"));
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
            while (col < panel.worldCol && row < panel.worldRow) {
                String line = reader.readLine(); // to read the lines of the map
                while (col < panel.worldCol) {
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
        int worldCol = 0;
        int WorldRow = 0;
        while (worldCol < panel.worldCol && WorldRow < panel.worldRow) {

            int tileNum = mapTileNum[WorldRow][worldCol];

            int WorldX = worldCol * panel.TILE_SIZE;
            int WorldY = WorldRow * panel.TILE_SIZE;
            int ScreenX = WorldX - panel.player.worldX + panel.player.screenX;
            int ScreenY = WorldY - panel.player.worldY + panel.player.screenY;

            if(     WorldX + panel.TILE_SIZE > panel.player.worldX - panel.player.screenX &&
                    WorldX - panel.TILE_SIZE< panel.player.worldX + panel.player.screenX &&
                    WorldY + panel.TILE_SIZE> panel.player.worldY - panel.player.screenY &&
                    WorldY - panel.TILE_SIZE< panel.player.worldY + panel.player.screenY) {
                g2d.drawImage(tiles[tileNum].image, ScreenX, ScreenY, panel.TILE_SIZE, panel.TILE_SIZE, null);
            }
            worldCol++;

            if (worldCol == panel.worldCol) {
                worldCol = 0;
                WorldRow++;
            }
        }
    }
}
