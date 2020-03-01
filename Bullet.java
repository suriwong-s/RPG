import java.awt.*;

public class Bullet {
    private double x;
    private double y;
    private int r;
    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private int width;
    private int height;
    private boolean topLeft;
    private boolean topRight;
    private boolean bottomLeft;
    private boolean bottomRight;

    private TileMap tileMap;
    private Color color;
    private Player bullet;
    private int[][] map;
    private int mapHeight;
    private int mapWidth;

    public Bullet(double angle, int x, int y) {
        this.x = x;
        this.y = y;
        this.r = 2;
        this.rad = Math.toRadians(angle);
        this.dx = Math.cos(rad);
        this.dy = Math.sin(rad);
        this.speed = 3;
        this.color = Color.YELLOW;
        this.width = 20;
        this.height = 20;
        this.mapHeight = 15;
        this.mapWidth = 20;

    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getR(){
        return r;
    }
    
    public boolean update() {
        x += dx;
        y += dy;
        if (y < -r || y > GamePanel.WIDTH + r || y < -r || y > GamePanel.HEIGHT + r) {
            return true;
        }
        return false;
    }

    public void calculateCorners(double x, double y) {
        int left = tileMap.getColTile((int) (x - width / 2));
        int right = tileMap.getColTile((int) (x + width / 2) - 1);
        int top = tileMap.getRowTile((int) (y - height / 2));
        int bottom = tileMap.getRowTile((int) (y + height / 2) - 1);
        topLeft = tileMap.getTile(top, left) == 0;
        topRight = tileMap.getTile(bottom, right) == 0;
        bottomLeft = tileMap.getTile(bottom, left) == 0;
        bottomRight = tileMap.getTile(bottom, right) == 0;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillOval((int) (this.x - r), (int) (this.y - r), 2 * r, 2 * r);

    }
}