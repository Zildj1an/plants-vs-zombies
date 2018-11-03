package tp.logic.objects;

import tp.logic.Game;

public class Athlete extends Zombie {
    private static final int HEALTH = 5;
    private static final int FREQUENCY = 2;
    private static final int DAMAGE = 1;

    public Athlete(){
        super(FREQUENCY, DAMAGE);
        super.health = HEALTH;
        super.cycle = 0;
    }

    public Athlete(int x, int y, Game game){
        super(FREQUENCY, DAMAGE);
        super.game = game;
        super.x = x;
        super.y = y;
        super.health = HEALTH;
        super.cycle = 0;
    }
}
