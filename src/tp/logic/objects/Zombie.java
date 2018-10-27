package tp.logic.objects;

import tp.logic.Game;

public class Zombie {
    private static final int HEALTH = 5;
    private static final int FREQUENCY = 2;
    private static final int DAMAGE = 1;

    private Game game;
    private int x;
    private int y;
    private int health;
    private int cycles;

    public Zombie(int x, int y, Game game){
        this.game = game;
        this.x = x;
        this.y = y;
        this.health = HEALTH;
        this.cycles = 0;
    }

    public void update(){
        Sunflower sf;
        Peashooter ps;
        if(cycles >= FREQUENCY && game.isEmpty(x, y-1)){
            y--;
            cycles = 0;
        }else if ((ps = game.getPSInPosition(x, y-1)) != null){
            ps.decreaseHealth(DAMAGE);
        }else if ((sf = game.getSFInPosition(x, y-1)) != null){
            sf.decreaseHealth(DAMAGE);
        }

        if(y == 0)
            game.updateWinner(Player.ZOMBIES);
        cycles++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void decreaseHealth(int damage){
        this.health-= damage;
        if (health == 0)
            game.removeZombie(this);
    }

    public String toString(){
        return "Z [" + health + "]";
    }
}
