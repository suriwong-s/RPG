import java.awt.*;

public class Enemy{
    private int [][]map;
    private double x;
    private double y;
    private double r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private int hp;
    private int type;
    private int rank;

    private int width;
    private int height;

    private int mapWidth;
    private int mapHeight;

    private Color color;

    private boolean ready;
    private boolean dead;

    private boolean topLeft;
    private boolean topRight;
    private boolean bottomLeft;
    private boolean bottomRight;

    private TileMap tileMap;

    public Enemy(int type, int rank){
        this.type = type;
        this.rank = rank;
        this.tileMap = tileMap;
        this.width = 20;
        this.height = 20;
        this.mapHeight = 20;
        this.mapWidth = 20;
        this.dx = 0;
        this.dy = 0;
        this.x = 300;
        this.y = 50;

        if(type == 1){
            color = Color.RED;
            if(rank == 1){
                speed = 0.5;
                r = 5;
                hp = 1;
            }
        }
        x = Math.random() * GamePanel.WIDTH /2 + GamePanel.WIDTH /4;
        y = -r;

        double angle = Math.random() * 140 + 20;
        rad = Math.toRadians(angle);

        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        ready = false;
        dead = false;
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
    public boolean isDead(){
        return dead;
    }

    public void hit(){
        hp--;
        if(hp <= 0){
            dead = true;
        }
    }
    public void update(){
        x += dx;
        y += dy;
        if(!ready){
            if(x > r && x < GamePanel.WIDTH - r &&
                y > r && y < GamePanel.HEIGHT - r){
                    ready = true;
            }
        }

        if(x < r && dx < 0 ){
            dx = -dx;
        }
        if(y < r && dy < 0){
            dy = -dy;
        }
        if(x > GamePanel.WIDTH - r && dx > 0){
            dx = -dx;
        }
        if(y > GamePanel.HEIGHT - r && dy > 0){
            dy = -dy;
        }

        x += dx;
        y += dy;
       
    }

    public void collision() {

        int currentCol = tileMap.getColTile((int) x);
        int currentRow = tileMap.getRowTile((int) y);

        double toX = x + dx;
        double toY = y + dy;

        double tempX = x;
        double tempY = y;

        calculateCorners(x, toY);
        if (dy < 0) {
            if (topLeft || topRight) {
                dy = 0;
                tempY = currentRow * tileMap.getTileSize() + height / 2;
            } else {
                tempY += dy;
            }
        }
        if (dy > 0) {
            if (bottomLeft || bottomRight) {
                dy = 0;
                tempY = (currentRow + 1) * tileMap.getTileSize() - height / 2;
            } else {
                tempY += dy;
            }
        }
        calculateCorners(toX, y);
        if (dx < 0) {
            if (topLeft || bottomLeft) {
                dx = 0;
                tempX = currentCol * tileMap.getTileSize() + width / 2;
            } else {
                tempX += dx;
            }
        }
        if (dx > 0) {
            if (topRight || bottomRight) {
                dx = 0;
                tempX = (currentCol + 1) * tileMap.getTileSize() - width / 2;
            } else {
                tempX += dx;
            }
        }
        x = tempX;
        y = tempY;

        // titleMap.setx((int)());
    }

    public void calculateCorners(double x, double y) {
        int left = tileMap.getColTile((int) (x - width / 2));
        int right = tileMap.getColTile((int) (x + width / 2) - 1);
        int top = tileMap.getRowTile((int) (y - height / 2));
        int bottom = tileMap.getRowTile((int) (y + height / 2) - 1);
        topLeft = tileMap.getTile(top, left) == 0;
        topRight = tileMap.getTile(top, right) == 0;
        bottomLeft = tileMap.getTile(bottom, left) == 0;
        bottomRight = tileMap.getTile(bottom, right) == 0;
    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval((int)(x -r), (int)(y - r), 2 * (int)r, 2 * (int) r);

        //border
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int)(x - r), (int)(y - r), 2 * (int)r, 2 * (int)r);
        g.setStroke(new BasicStroke(1));

    }
    



}