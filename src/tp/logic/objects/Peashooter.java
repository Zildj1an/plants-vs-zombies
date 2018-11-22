package tp.logic.objects;


import tp.logic.Game;

public class Peashooter extends Plant{
    private static final int COST = 50;
    private static final int HEALTH = 3;
    private static final int FREQUENCY = 1;
    private static final int DAMAGE = 1;

    private static final char SYMBOL = 'P';

    public Peashooter(){
        super(COST);
        super.health = HEALTH;
        super.cycle = 0;
        super.symbol = SYMBOL;
    }

    public void update(){
        if(cycle == 0) {
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
            cycle = FREQUENCY;
        }
        cycle--;
    }

    public void decreaseHealth(int damage){
        this.health -= damage;
        if (health == 0)
            game.removePlant(this);
    }

    public String getInfo(){
        return "[" + SYMBOL + "]eashooter: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }
}
