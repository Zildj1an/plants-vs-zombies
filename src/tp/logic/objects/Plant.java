package tp.logic.objects;

public abstract class Plant extends GameObject {

    private int cost;

    public Plant(int cost){
        this.cost = cost;
    }

    @Override
    public void decreaseHealth(int damage) {
        health -= damage;
        if (health == 0)
            game.removePlant(this);
    }

    public int getCost(){
        return this.cost;
    }

    public abstract String getInfo();
}
