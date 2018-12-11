package tp.logic;

import tp.exceptions.FileContentsException;
import tp.exceptions.GameObjectException;
import tp.logic.factories.PlantFactory;
import tp.logic.factories.ZombieFactory;
import tp.logic.lists.GameObjectList;
import tp.logic.objects.*;
import tp.logic.printers.GamePrinter;
import tp.logic.printers.ReleasePrinter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class Game {

    public static final int ROWS = 4;
    public static final int COLUMNS = 8;
    private static final int DEFAULT_SEED = 0;
    public static final String VERSION = "Plants Vs Zombies v3.0";
    private static final String WRONG_PREFIX_MSG = "unknown game attribute: ";
    private static final String LINE_TOO_LONG_MSG = "too many words on line commencing: ";
    private static final String LINE_TOO_SHORT_MSG = "missing data on line commencing: ";

    private GameObjectList plantList;
    private GameObjectList zombieList;
    private SuncoinManager suncoinManager;
    private ZombieManager zombieManager;
    private int cycles;
    private Level level;
    private int seed;
    private Random rand;
    private Player winner;
    private boolean exit;
    private GamePrinter printMode;


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

    public boolean addPlant(Plant p, int x, int y) throws Exception {

        if (p == null) throw new Exception("Invalid type plant");
        if (suncoinManager.suncoins < p.getCost()) throw new Exception("Failed to add " + p.getName() + ": not enough suncoins to buy it");
        if (!isValidPosition(x, y)) throw new Exception("Failed to add " + p.getName() + ": (" + x + " , " + y + ") is an invalid position");
        if (!isEmpty(x, y)) throw new Exception("Failed to add " + p.getName() + ": (" + x + " , " + y + ") is already occupied");

        p.setPosition(x, y);
        p.setGame(this);
        suncoinManager.suncoins-= p.getCost();

        return plantList.add(p);

    }

    public boolean addZombie(Zombie z, int x, int y) throws Exception {

        if (z == null) throw new Exception("Invalid type zombie");
        if (!isEmpty(x, y)) throw  new Exception("Failed to add " + z.getName() + ": (" + x + " , " + y + ") is already occupied");
        if (!isValidPositionZombie(x, y)) throw  new Exception("Failed to add " + z.getName() + ": (" + x + " , " + y + ") is an invalid position");

        z.setPosition(x,y);
        z.setGame(this);

        return zombieList.add(z);
    }

    public void save(BufferedWriter out) throws IOException {
        out.write("cycle: " + cycles);
        out.newLine();
        out.write("sunCoins: " + suncoinManager.suncoins);
        out.newLine();
        out.write("level: " + level);
        out.newLine();
        out.write("plantList: ");
        plantList.store(out);
        out.newLine();
        out.write("zombieList: ");
        zombieList.store(out);
    }

    public void load(BufferedReader in) throws IOException, FileContentsException {
        int cycle;
        int sunCoins;
        Level level;
        GameObjectList plantList;
        GameObjectList zombieList;

        try{
            if((cycle = Integer.parseInt(loadLine(in,"cycle", false)[0])) < 0) throw new FileContentsException("Invalid number of cycles");
            if((sunCoins = Integer.parseInt(loadLine(in, "sunCoins", false)[0])) < 0) throw new FileContentsException("Invalid number of suncoins");
            level = Level.valueOf(loadLine(in, "level", false)[0]);


            plantList = new GameObjectList(loadLine(in, "plantList", true),this);

            //TODO check correct information
            for (int i = 0; i < plantList.size(); i++)
                if(!(plantList.get(i) instanceof Plant)) throw new FileContentsException("Invalid list of plants");

            zombieList = new GameObjectList(loadLine(in, "zombieList", true), this);
            for (int i = 0; i < zombieList.size(); i++) {
                if (!(zombieList.get(i) instanceof Zombie))
                    throw new FileContentsException("Invalid list of zombies");
            }

            if (plantList.size() + plantList.size() > Game.COLUMNS*Game.ROWS)
                throw new FileContentsException("Invalid number of plants and zombies");


        }catch (NumberFormatException e){
            throw new FileContentsException("Invalid syntax");
        }catch (IllegalArgumentException e){
            throw new FileContentsException("Invalid level");
        }catch (GameObjectException e){
            throw new FileContentsException(e.getMessage());
        }

        this.cycles = cycle;
        this.suncoinManager.suncoins = sunCoins;
        this.level = level;
        this.plantList = plantList;
        this.zombieList = zombieList;
    }

    public String[] loadLine(BufferedReader inStream, String prefix, boolean isList) throws IOException, FileContentsException {
        String line = inStream.readLine().trim();
        // absence of the prefix is invalid
        if (!line.startsWith(prefix + ":") )
            throw new FileContentsException(WRONG_PREFIX_MSG + prefix);
        // cut the prefix and the following colon off the line
        // then trim it to get the attribute contents
        String contentString = line.substring(prefix.length()+1).trim();
        String[] words;
        // the attribute contents are not empty
        if (!contentString.equals("")) {
            if (!isList ) {
                // split non−list attribute contents into words
                // using 1−or−more−white−spaces as separator
                words = contentString.split("\\s+");
                // a non−list attribute with contents of more than one word is invalid
                if (words.length != 1)
                    throw new FileContentsException(LINE_TOO_LONG_MSG + prefix);
            } else
                // split list attribute contents into words
                // using comma+0−or−more−white−spaces as separator
                words = contentString.split(",\\s*");
            // the attribute contents are empty
        } else {
            // a non−list attribute with empty contents is invalid
            if (! isList )
                throw new FileContentsException(LINE_TOO_SHORT_MSG + prefix);
            // a list attibute with empty contents is valid;
            // use a zero−length array to store its words
            words = new String[0];
        }
        return words;
    }

    public void reset(){
        initialize();
    }

    public void exit(){
        exit = true;
    }

    public boolean isEmpty(int x, int y){
        return getPInPosition(x, y) == null && getZInPosition(x, y) == null;
    }

    private boolean isValidPosition(int x, int y){
        return x < ROWS && y < COLUMNS-1 && x >= 0 && y >= 0;
    }

    private boolean isValidPositionZombie(int x, int y){
        return x < ROWS && y < COLUMNS && x >= 0 && y >= 0;
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

    public boolean hasFinished(){
        return exit;
    }


    public void setModePrint(GamePrinter mode){
        this.printMode = mode;
    }

    public GamePrinter getModePrint(){
        return this.printMode;
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
        this.exit = false;
        this.printMode = new ReleasePrinter();
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
