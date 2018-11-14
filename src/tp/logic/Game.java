package tp.logic;

import tp.logic.factories.PlantFactory;
import tp.logic.factories.ZombieFactory;
import tp.logic.lists.GameObjectList;
import tp.logic.objects.*;

import java.util.Random;

public class Game {

    public static final int ROWS = 4;
    public static final int COLUMNS = 8;
    private static final int DEFAULT_SEED = 0;

    private GameObjectList plantList;
    private GameObjectList zombieList;
    private SuncoinManager suncoinManager;
    private ZombieManager zombieManager;
    private int cycles;
    private Level level;
    private int seed;
    private Random rand;
    private Player winner;


    public Game(Level level){
        this(level, DEFAULT_SEED);
    }

    public Game(Level level, int seed){
        this.level = level;
        this.seed = seed;
        initialize();
    }

    public void update(){
        plantList.update();
        zombieList.update();

        if (zombieManager.isZombieAdded() && canZombieAdded()) {
            int row;
            do {
                row = rand.nextInt(ROWS);
            }while(!isEmpty(row, COLUMNS-1));

            Zombie z = zombieManager.getRandomTypeZombie();
            z.setGame(this);
            z.setPosition(row, COLUMNS-1);
            zombieList.add(z);
            zombieManager.updateRemainingZombies();
        }

        cycles++;

        if(zombieList.size() == 0 && zombieManager.getRemainingZombies() == 0)
            updateWinner(Player.PLAYER);

    }

    public boolean addPlant(String plant, int x, int y){
        Plant p = PlantFactory.getPlant(plant);
        boolean added = false;

        if (p != null && isEmpty(x, y) && suncoinManager.suncoins >= p.getCost() && isValidPosition(x, y)){
            p.setPosition(x,y);
            p.setGame(this);
            suncoinManager.suncoins-= p.getCost();
            plantList.add(p);
            added = true;
        }


        return added;
    }

    //Method only for debug purpose
    public boolean addZombie(String zombie, int x, int y){
        Zombie z = ZombieFactory.getZombie(zombie);

        if (z != null){
            z.setPosition(x,y);
            z.setGame(this);
            zombieList.add(z);
        }
        return z != null;
    }

    public void reset(){
        initialize();
    }

    public boolean isEmpty(int x, int y){
        return getPInPosition(x, y) == null && getZInPosition(x, y) == null;
    }

    private boolean isValidPosition(int x, int y){
        return x < ROWS && y < COLUMNS-1 && x >= 0 && y >= 0;
    }

    public Plant getPInPosition(int x, int y){
        return (Plant) plantList.search(x, y);
    }

    public Zombie getZInPosition(int x, int y){
        return (Zombie) zombieList.search(x, y);
    }

    public void incrementSuncoins(int n){
        this.suncoinManager.suncoins += n;
    }

    public void removePlant(Plant p){
        plantList.remove(p);
    }

    public void removeZombie(Zombie z){
        zombieList.remove(z);
    }

    private boolean canZombieAdded(){
        boolean empty = false;
        int i = 0;

        while(i < ROWS && !empty){
            if(isEmpty(i,COLUMNS-1))
                empty = true;
            i++;
        }
        return empty;
    }

    public Player hasWinner(){
        return winner;
    }

    public void updateWinner(Player p){
        this.winner = p;
    }

    private void initialize(){
        this.plantList = new GameObjectList();
        this.zombieList = new GameObjectList();
        this.suncoinManager = new SuncoinManager();
        this.rand = new Random(seed);
        this.zombieManager = new ZombieManager(level, rand);
        this.cycles = 0;
        this.winner = Player.NONE;
    }

    public String toString(){
        StringBuilder buff = new StringBuilder();

        if(cycles == 0)
            buff.append("Random seed used: ").append(this.seed).append(System.getProperty("line.separator"));

        buff.append(info());

        return buff.toString();
    }

    public String debugInfo(){
        StringBuilder buff = new StringBuilder();

        buff.append(info());
        buff.append("Level: ").append(level.toString()).append(System.getProperty("line.separator"));
        buff.append("Seed: ").append(seed);


        return buff.toString();
    }

    private String info(){
        StringBuilder buff = new StringBuilder();

        buff.append("Number of cycles: ").append(this.cycles).append(System.getProperty("line.separator"));
        buff.append("Sun coins: ").append(suncoinManager.suncoins).append(System.getProperty("line.separator"));
        buff.append("Remaining zombies: ").append(zombieManager.getRemainingZombies()).append(System.getProperty("line.separator"));

        return buff.toString();
    }

}
