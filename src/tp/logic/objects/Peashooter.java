package tp.logic.objects;

import tp.logic.Game;

public class Peashooter {
    private static final int COST = 50;
    private static final int HEALTH = 3;
    private static final int FREQUENCY = 1;
    private static final int DAMAGE = 1;

    private int x;
    private int y;
    private Game game;
    private int health;
    private int cycle;

    public Peashooter(int x, int y, Game game){
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
        cycle++;
        if(cycle == FREQUENCY) {
            int i = y + 1;
            boolean found = false;
            Zombie z;

            while (i < Game.COLUMNS && !found) {
                if ((z = game.getZInPosition(x, i)) != null) {
                    found = true;
                    z.decreaseHealth(DAMAGE);
                }
                i++;
            }
            cycle = 0;
        }
    }

    public void decreaseHealth(int damage){
        this.health -= damage;
        if (health == 0)
            game.removePeasooter(this);
    }

    public static int getCost(){
        return COST;
    }

    public static String getInfo(){
        return "[P]eashooter: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }

    public String toString(){
        return "P[" + health + "]";
    }
}
