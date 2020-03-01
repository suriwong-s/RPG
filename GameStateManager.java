import java.util.ArrayList;
import java.awt.*;

public class GameStateManager{
    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENU = 0;
    public static final int LEVELSTATE = 1;

    public GameStateManager(){
        gameStates = new ArrayList<GameState>();
        currentState = MENU;
        gameStates.add(new Menu(this));
        gameStates.add(new LevelState(this));
    }

    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).start();
    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void draw(Graphics2D g){
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }





}