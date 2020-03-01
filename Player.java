import java.awt.*;

public class Player {
    String name;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private int width;
    private int height;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private double moveSpeed;
    private double maxSpeed;
    private double stopSpeed;

    private TileMap tileMap;

    private boolean topLeft;
    private boolean topRight;
    private boolean bottomLeft;
    private boolean bottomRight;

    private Color color1;
    private Color color2;

    private int lives;
    private boolean firing;
    private long firingTimer;
    private long firingDelay;

    public Player(TileMap tileMap) {
        this.tileMap = tileMap;
        this.width = 20;
        this.height = 20;
        this.moveSpeed = 0.8;
        this.maxSpeed = 4.2;
        this.stopSpeed = 0.30;
        this.color1 = Color.WHITE;
        this.color2 = Color.RED;
        this.lives = 3;
        this.dx = 0;
        this.dy = 0;
        this.firing = false;
        this.firingTimer = System.nanoTime();
        this.firingDelay = 200;
        this.x = 50;
        this.y = 200;

    }

    public void setLeft(boolean b) {
        this.left = b;
    }

    public void setRight(boolean b) {
        this.right = b;
    }

    public void setUp(boolean b) {
        this.up = b;
    }

    public void setDown(boolean b) {
        this.down = b;
    }

    public void setFiring(boolean b) {
        this.firing = b;
    }

    public void update() {
        // movement
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (up) {
            dy -= moveSpeed;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else if (down) {
            dy += moveSpeed;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= stopSpeed;
                if (dy > 0) {
                    dy = 0;
                }
            } else if (dy < 0) {
                dy += stopSpeed;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        x += dx;
        y += dy;
        // firing
        if (firing) {
            long elapsed = (System.nanoTime() - firingTimer / 1000000);
            if (elapsed > firingDelay) {
                LevelState.bullets.add(new Bullet(270, (int) x, (int) y));
                firingTimer = System.nanoTime();
            }
        }
        collision();
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

    }

    public void draw(Graphics2D g) {
        int tx = tileMap.getX();
        int ty = tileMap.getY();

        g.setColor(color1);
        g.fillRect((int) (tx + x - width / 2), (int) (ty + y - height / 2), width, height);

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

}