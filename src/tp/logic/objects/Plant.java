package tp.logic.objects;

public abstract class Plant extends ActiveGameObject {

    private int cost;

    public Plant(int cost){
        this.cost = cost;
    }

    @Override
    public boolean isPlant() {
        return true;
    }

    @Override
    public void decreaseHealth(int damage) {
        health -= damage;
        if (health == 0)
            game.removeActiveGameObject(this);
    }

    public int getCost(){
        return this.cost;
    }

    public abstract String getInfo();
}
