package tp.logic.objects;

public enum Player {
    PLAYER("Player"), ZOMBIES("Zombies"), NONE("none");

    private String name;

    Player(String s){
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
