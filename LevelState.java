import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;

public class LevelState extends GameState{

    private TileMap tileMap;
    private static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    //public static Enemy enemies;

    public LevelState(GameStateManager gsm){
        this.gsm = gsm;
    }
    public void start(){
        //tileMap = new TileMap(20);
        //tileMap.loadMap("TileMap.txt");
        //player = new Player(tileMap);
        //player.setPosition(100, 100);
        tileMap = new TileMap("TileMap.txt", 30);
        player = new Player(tileMap);
        bullets = new ArrayList<>();
        //enemies = new Enemy(1, 1);
        enemies = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            enemies.add(new Enemy(1, 1));
        }

    }

    public void update(){
        tileMap = new TileMap("TileMap.txt", 30);
        //tileMap.update();
        player.update();
        //enemies.update();
        //bullets
        for (int i = 0; i < bullets.size(); i++) {
            boolean remove = bullets.get(i).update();
            if (remove) {
                bullets.remove(i);
                i--;
            }
        }
        ///enemies
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).update();
        }
        for(int i = 0; i < bullets.size(); i++){
            Bullet b = bullets.get(i);
            double bx = b.getX();
            double by = b.getY();
            double br = b.getR();

            for(int j = 0; j < enemies.size(); j++){
                Enemy e = enemies.get(j);
                double ex = e.getX();
                double ey = e.getY();
                double er = e.getR();

                double dx = bx - ex;
                double dy = by - ey;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if(dist < br + er){
                    e.hit();
                    bullets.remove(i);
                    i--;
                    break;
                }
            }
        }
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).isDead()){
                enemies.remove(i);
                i++;
            }
        }
        
    }
    public void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);
        //tileMap.draw(g);
        //player.draw(g);
        tileMap = new TileMap("TileMap.txt", 30);
        tileMap.draw(g);
        player.draw(g);
        //enemies.draw(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }
        for(int i = 0; i < enemies.size(); i++){
           enemies.get(i).draw(g);
        }

    }

    public void keyPressed(int k){
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(true);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(true);
        }
        if (k == KeyEvent.VK_Z) {
            player.setFiring(true);
        }
        if(k == KeyEvent.VK_Q){
            JOptionPane.showMessageDialog(null, "Do you want to exit the game?");
            System.exit(0);
        }
    }
    public void keyReleased(int k){
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(false);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }
        if (k == KeyEvent.VK_Z) {
            player.setFiring(false);
        }
    }

}