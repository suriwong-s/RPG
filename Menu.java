import java.awt.*;
import java.awt.event.*;


public class Menu extends GameState{

    private Background bg;
    private int currentChoice = 0;
    private String[] options = {
        "I'm going SOLO",
        "Multiplayer",
        "Exit"
    };
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public Menu(GameStateManager gsm){
        this.gsm = gsm;
        try {
            bg = new Background("cloud.gif", 1);
            bg.setVector(-0.1, 0);

            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Monospaced Bold", Font.PLAIN, 28);

            font = new Font("Arail", Font.PLAIN, 15);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void start(){

    }

    public void update(){
        bg.update();
    }

    public void draw(Graphics2D g){
        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("The Last Age Of Magic", 80, 70);

        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if(i == currentChoice){
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.YELLOW);
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }
    }
    
    public void select(){
        if(currentChoice == 0){
            gsm.setState(GameStateManager.LEVELSTATE);
        }
        if(currentChoice == 1){
            gsm.setState(GameStateManager.LEVELSTATE);
        }
        if(currentChoice == 2){
            System.exit(0);
        }
    }
    public void keyPressed(int k){
        if(k == KeyEvent.VK_ENTER){
            select();
        }
        if(k == KeyEvent.VK_UP){
            currentChoice--;
            if(currentChoice == -1){
                currentChoice = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN){
            currentChoice++;
            if(currentChoice == options.length){
                currentChoice = 0;
            }
        }
    }
    public void keyReleased(int k){}


}