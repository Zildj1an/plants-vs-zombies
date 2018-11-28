package tp.logic;

import tp.logic.factories.PlantFactory;
import tp.logic.factories.ZombieFactory;
import tp.logic.lists.ActiveGameObjectList;
import tp.logic.lists.PassiveGameObjectList;
import tp.logic.objects.*;

import java.util.Random;

public class Game {

    public static final int ROWS = 4;
    public static final int COLUMNS = 8;
    private static final int DEFAULT_SEED = 0;

    private PassiveGameObjectList pasiveGameObjectList;
    private ActiveGameObjectList activeGameObjectList;

    private SunManager sunManager;
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
        activeGameObjectList.update();
        sunManager.update();

        if (zombieManager.isZombieAdded() && canZombieAdded()) {
            int row;
            do {
                row = rand.nextInt(ROWS);
            }while(!isEmpty(row, COLUMNS-1));

            Zombie z = zombieManager.getRandomTypeZombie();
            z.setGame(this);
            z.setPosition(row, COLUMNS-1);
            activeGameObjectList.add(z);
            zombieManager.updateRemainingZombies();
        }

        cycles++;

        if(activeGameObjectList.countZombies() == 0 && zombieManager.getRemainingZombies() == 0)
            updateWinner(Player.PLAYER);

    }

    public boolean addPlant(String plant, int x, int y){
        Plant p = PlantFactory.getPlant(plant);
        boolean added = false;

        if (p != null && isEmpty(x, y) && sunManager.suncoins >= p.getCost() && isValidPosition(x, y)){
            p.setPosition(x,y);
            p.setGame(this);
            sunManager.suncoins-= p.getCost();
            activeGameObjectList.add(p);
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
            activeGameObjectList.add(z);
        }
        return z != null;
    }

    public void addSun(int x, int y){
        sunManager.addSun(new Sun(), x, y);
    }

    public boolean catchSun(int x, int y){
        return sunManager.catchSun(x,y);
    }

    public void reset(){
        initialize();
    }

    public boolean isEmpty(int x, int y){
        return getActiveObjectPosition(x,y) == null;
    }

    private boolean isValidPosition(int x, int y){
        return x < ROWS && y < COLUMNS-1 && x >= 0 && y >= 0;
    }

    public ActiveGameObject getActiveObjectPosition(int x, int y){
        return activeGameObjectList.search(x,y);
    }

    public void removeActiveGameObject(ActiveGameObject obj){
        activeGameObjectList.remove(obj);
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
        this.pasiveGameObjectList = new PassiveGameObjectList();
        this.activeGameObjectList = new ActiveGameObjectList();
        this.rand = new Random(seed);
        this.sunManager = new SunManager(rand);
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
        buff.append("Sun coins: ").append(sunManager.suncoins).append(System.getProperty("line.separator"));
        buff.append("Remaining zombies: ").append(zombieManager.getRemainingZombies()).append(System.getProperty("line.separator"));

        return buff.toString();
    }

    public String positionToString(int i, int j){
        String sunString = sunManager.positionToString(i,j);

        ActiveGameObject obj = activeGameObjectList.search(i,j);

        if (obj != null)
            return obj.toString() + " " + sunString;
        return " " + sunString;
    }

}
