package tp.logic.objects;

import tp.logic.Game;

public class CherryBoom extends Plant{

    private static final int COST = 50;
    private static final int HEALTH = 2;
    private static final int FREQUENCY = 2;
    private static final int DAMAGE = 10;

    public CherryBoom() {
        super(COST);
        super.health = HEALTH;
        super.cycle = 0;
    }

    @Override
    public String getInfo() {
        return "[C]herryBoom: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }

    @Override
    public void update() {
        cycle++;
        Zombie z;

        if (cycle == FREQUENCY){
            for(int i = Math.max(x-1, 0); i <= Math.min(x+1, Game.ROWS); i++){
                for (int j = Math.max(y-1, 0); j <= Math.min(y+1, Game.COLUMNS); j++){
                    if ((z = game.getZInPosition(i,j)) != null){
                        z.decreaseHealth(DAMAGE);
                    }
                }
            }
            game.removePlant(this);
        }
    }

    public String toString(){
        return "C["+ health +"]";
    }
}
