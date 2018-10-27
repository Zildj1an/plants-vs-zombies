package tp.Logic;

import tp.Logic.Lists.PeashooterList;
import tp.Logic.Lists.SunflowerList;
import tp.Logic.Lists.ZombieList;
import tp.Logic.objects.*;
import tp.p1.Logic.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    public static final int ROWS = 4;
    public static final int COLUMNS = 8;
    private static final int DEFAULT_SEED = 0;

    private SunflowerList sunflowerList;
    private PeashooterList peashooterList;
    private ZombieList zombieList;
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
        cycles++;
        sunflowerList.update();
        peashooterList.update();

        if (zombieManager.isZombieAdded() && canZombieAdded()) {
            int row;
            do {
                row = rand.nextInt(ROWS);
            }while(!isEmpty(row, COLUMNS-1));
            zombieList.add(new Zombie(row, COLUMNS - 1, this));
            zombieManager.updateRemainingZombies();

        }

        zombieList.update();

        if(zombieList.size() == 0 && zombieManager.getRemainingZombies() == 0)
            updateWinner(Player.PLAYER);

    }

    public boolean addPeashooter(int x, int y){
        boolean added = false;

        if (isEmpty(x, y) && suncoinManager.suncoins >= Peashooter.getCost() && isValidPosition(x,y)) {
            peashooterList.add(new Peashooter(x, y, this));
            suncoinManager.suncoins -= Peashooter.getCost();
            added = true;
        }

        return added;
    }

    public boolean addSunflower(int x, int y){
        boolean added = false;

        if (isEmpty(x, y) && suncoinManager.suncoins >= Sunflower.getCost() && isValidPosition(x, y)) {
            sunflowerList.add(new Sunflower(x, y, this));
            suncoinManager.suncoins -= Sunflower.getCost();
            added = true;
        }
        return added;
    }

    public List<String> listPlantsAvailable(){
        List<String> l = new ArrayList<>();
        l.add(Peashooter.getInfo());
        l.add(Sunflower.getInfo());

        return l;
    }

    public void reset(){
        initialize();
    }

    public boolean isEmpty(int x, int y){
        return getSFInPosition(x, y) == null && getZInPosition(x, y) == null && getPSInPosition(x, y) == null;
    }

    private boolean isValidPosition(int x, int y){
        return x < ROWS && y < COLUMNS && x >= 0 && y >= 0;
    }

    public Sunflower getSFInPosition(int x, int y){
        return sunflowerList.search(x,y);
    }

    public Peashooter getPSInPosition(int x, int y){
        return peashooterList.search(x,y);
    }

    public Zombie getZInPosition(int x, int y){
        return zombieList.search(x,y);
    }

    public void incrementSuncoins(int n){
        this.suncoinManager.suncoins += n;
    }

    public void removeSunflower(Sunflower sf){
        sunflowerList.remove(sf);
    }

    public void removePeasooter(Peashooter ps){
        peashooterList.remove(ps);
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
        this.sunflowerList = new SunflowerList();
        this.peashooterList = new PeashooterList();
        this.zombieList = new ZombieList();
        this.suncoinManager = new SuncoinManager();
        this.rand = new Random(seed);
        this.zombieManager = new ZombieManager(level, rand);
        this.cycles = 0;
        this.winner = Player.NONE;
    }

    public String toString(){
        StringBuilder buff = new StringBuilder();
        GamePrinter gamePrinter = new GamePrinter(this, ROWS, COLUMNS);

        if(cycles == 0)
            buff.append("Random seed used: ").append(this.seed).append("\r\n");

        buff.append("Number of cycles: ").append(this.cycles).append("\r\n");
        buff.append("Sun coins: ").append(suncoinManager.suncoins).append("\r\n");
        buff.append("Remaining zombies: ").append(zombieManager.getRemainingZombies()).append("\r\n");

        buff.append(gamePrinter.toString());

        return buff.toString();
    }
}
