package tp.logic.objects;

import tp.logic.Game;

public abstract class ActiveGameObject extends GameObject{
    protected int health;
    protected int cycle;
    protected Game game;

    public abstract void update();

    public abstract void decreaseHealth(int damage);

    public void setGame(Game g){
        this.game = g;
    }

    public String toString(){
        return symbol+ "["+ health +"]";
    }

    public abstract boolean isPlant();

    public String debugPrint() {
        return symbol + "[l:" + health + ",x:" + x + ",y:" + y +",t:" + cycle + "]";
    }

}
