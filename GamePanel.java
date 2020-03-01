import java.util.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;


public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 350;
    public static final int SCALE = 2;

    private Thread thread;
    private boolean isRunning;

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    private int FPS = 60;
    private int targetTime = 1000 / FPS;

    //private TileMap tileMap;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setVisible(true);
        setFocusable(true);
        requestFocus();
    }
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void run() {
        start();

        long startTime;
        long urdTime;
        long waitTime;

        while (isRunning) {
            startTime = System.nanoTime();
            update();
            drawToScreen();
            draw();

            urdTime = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - urdTime;

            try {
                Thread.sleep(waitTime);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public void start() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        gsm = new GameStateManager();
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();

    }

    private void draw() {
        gsm.draw(g);
    }

    private void update() {
        gsm.update();
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }





}