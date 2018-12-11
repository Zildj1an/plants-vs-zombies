package tp.logic.objects;

import tp.logic.Game;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected Game game;
    protected int health;
    protected int cycle;
    protected char symbol;
    protected String name;

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public abstract void update();

    public abstract void decreaseHealth(int damage);

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setGame(Game g){
        this.game = g;
    }

    public String toString(){
            return symbol+ "["+ health +"]";
    }

    public String debugPrint() {

        return symbol + "[l:" + health + ",x:" + x + ",y:" + y +",t:" + cycle + "]";

    }

    public void store(BufferedWriter out) throws IOException {
        out.write(symbol + ":" + health + ":" + x + ":" + y + ":" + cycle);
    }

    //---------------------------
    public String getName(){
        return name;
    }

    public void setHealth(int h){
        this.health = h;
    }

    public void setCycle(int c){
        this.cycle = c;
    }
}
