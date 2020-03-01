import java.io.*;
import java.awt.*;

public class TileMap {
    private int x;
    private int y;

    private int tileSize;
    private int[][] map;
    private int mapWidth;
    private int mapHeight;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTile(int row, int col) {
        return map[row][col];
    }

    public int getColTile(int x) {
        return x / tileSize;
    }

    public int getRowTile(int y) {
        return y / tileSize;
    }

    public int getTileSize() {
        return this.tileSize;
    }

    public TileMap(String s, int tileSize) {
        this.tileSize = tileSize;
        try {
            BufferedReader file = new BufferedReader(new FileReader(s));

            mapWidth = Integer.parseInt(file.readLine());
            mapHeight = Integer.parseInt(file.readLine());
            map = new int[mapHeight][mapWidth];

            String delimiers = " ";
            for (int y = 0; y < mapHeight; y++) {
                String line = file.readLine();
                String[] tokens = line.split(delimiers);
                for (int x = 0; x < mapWidth; x++) {
                    map[y][x] = Integer.parseInt(tokens[x]);
                }
            }
        } catch (Exception e) {
        }
    }

    public void collission() {

    }

    public void update() {
    }

    public void draw(Graphics2D g) {
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                int rc = map[row][col];

                if (rc == 0) {
                    g.setColor(Color.GREEN);
                }
                if (rc == 1) {
                    g.setColor(Color.BLUE);
                }
                g.fillRect(x + col * tileSize, y + row * tileSize, tileSize, tileSize);

            }
        }

    }

}