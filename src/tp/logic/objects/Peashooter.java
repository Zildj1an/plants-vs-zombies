package tp.logic.objects;


import tp.logic.Game;

public class Peashooter extends Plant{
    private static final int COST = 50;
    private static final int HEALTH = 3;
    private static final int FREQUENCY = 1;
    private static final int DAMAGE = 1;


    public Peashooter(){
        super(COST);
        super.health = HEALTH;
        super.cycle = 0;
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
            game.removePlant(this);
    }

    public String getInfo(){
        return "[P]eashooter: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }

    public String toString(){
        return "P[" + health + "]";
    }
}
