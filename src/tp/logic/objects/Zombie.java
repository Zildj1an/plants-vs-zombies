package tp.logic.objects;

public abstract class Zombie extends GameObject{

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
            game.removeZombie(this);
    }

    public String toString(){
        return "Z[" + health + "]";
    }

    public void update(){
        cycle++;
        GameObject p;
        if(cycle >= frequency && game.isEmpty(x, y-1)){
            y--;
            cycle = 0;
        }else if ((p = game.getPInPosition(x, y-1)) != null){
            p.decreaseHealth(damage);
        }

        if(y == 0)
            game.updateWinner(Player.ZOMBIES);
    }
}
