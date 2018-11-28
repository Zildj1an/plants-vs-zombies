package tp.logic.objects;

import tp.logic.Game;

public class CherryBoom extends Plant{

    private static final int COST = 50;
    private static final int HEALTH = 2;
    private static final int FREQUENCY = 2;
    private static final int DAMAGE = 10;

    private static final char SYMBOL = 'C';

    public CherryBoom() {
        super(COST);
        super.health = HEALTH;
        super.cycle = FREQUENCY;
        super.symbol = SYMBOL;
    }

    @Override
    public String getInfo() {
        return "[" + SYMBOL + "]herryBoom: Cost: " + COST + " suncoins Harm: " + DAMAGE;
    }

    @Override
    public void update() {
        ActiveGameObject z;

        if (cycle == 0){
            for(int i = Math.max(x-1, 0); i <= Math.min(x+1, Game.ROWS); i++){
                for (int j = Math.max(y-1, 0); j <= Math.min(y+1, Game.COLUMNS); j++){
                    if ((z = game.getActiveObjectPosition(i,j)) != null && !z.isPlant()){
                        z.decreaseHealth(DAMAGE);
                    }
                }
            }
            game.removeActiveGameObject(this);
        }else
            cycle--;
    }
}
