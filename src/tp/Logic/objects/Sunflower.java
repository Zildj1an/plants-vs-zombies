package tp.Logic.objects;

import tp.Logic.Game;

public class Sunflower {
    private static final int COST = 20;
    private static final int HEALTH = 1;
    private static final int FREQUENCY = 2;
    private static final int DAMAGE = 0;
    private static final int SUNCOINS = 10;

    private int x;
    private int y;
    private Game game;
    private int health;
    private int cycle;

    public Sunflower(int x, int y, Game game){
        this.health = HEALTH;
        this.x = x;
        this.y = y;
        this.cycle = 0;
        this.game = game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update(){
        if (cycle == FREQUENCY){
            game.incrementSuncoins(SUNCOINS);
            cycle = 0;
        }
        cycle++;
    }

    public void decreaseHealth(int damage){
        this.health -= damage;
        if (health == 0)
            game.removeSunflower(this);
    }

    public static int getCost(){
        return COST;
    }

    public static String getInfo(){
        return "[S]unflower: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }

    public String toString(){
        return "S["+ health +"]";
    }
}
