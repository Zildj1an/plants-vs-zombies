package tp.logic.objects;

public abstract class Zombie extends ActiveGameObject {

    private int frequency;
    private int damage;

    public Zombie(int frequency, int damage){
        this.frequency = frequency;
        this.damage = damage;
    }

    @Override
    public void decreaseHealth(int damage) {
        health -= damage;
        if (health <= 0)
            game.removeActiveGameObject(this);
    }

    public void update(){
        ActiveGameObject p;

        if(cycle <= 0 && game.isEmpty(x, y-1)){
            y--;
            cycle = frequency;
        }else if ((p = game.getActiveObjectPosition(x, y-1)) != null && p.isPlant()){
            p.decreaseHealth(damage);
        }

        cycle--;

        if(y == 0)
            game.updateWinner(Player.ZOMBIES);
    }

    @Override
    public boolean isPlant() {
        return false;
    }

    public abstract String getInfo();
}
