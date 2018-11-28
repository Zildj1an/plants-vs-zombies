package tp.logic;

import tp.logic.lists.PassiveGameObjectList;
import tp.logic.objects.PasiveGameObject;
import tp.logic.objects.Sun;

import java.util.Random;

public class SunManager {
    private static final int FRECUENCY = 5;
    private int cycle;

    public int suncoins;
    private PassiveGameObjectList suns;
    private Random rand;

    public SunManager(Random rand){
        this.suncoins = 50;
        this.suns = new PassiveGameObjectList();
        this.rand = rand;
        cycle = 0;
    }

    public void addSun(Sun sun, int x, int y){
        if(suns.isPositionEmpty(x,y)){
            sun.setPosition(x,y);
            suns.add(sun);
        }
    }

    public void update(){
        if (cycle == FRECUENCY){
            int row, column;
            Sun s = new Sun();
            do{
                row = rand.nextInt(Game.ROWS);
                column = rand.nextInt(Game.COLUMNS);
                s.setPosition(row, column);
                //TODO possible infinite loop
            }while(!suns.isPositionEmpty(row, column));
            suns.add(s);
            cycle = 0;
        }else
            cycle++;
    }

    public String positionToString(int i, int j){
        PasiveGameObject s;

        if ((s = suns.search(i, j)) != null)
            return s.toString();
        return "";
    }

    public boolean catchSun(int x, int y){
        Sun sun = (Sun) suns.search(x, y);

        if(sun != null){
            suncoins += sun.getValue();
            suns.remove(sun);
            return true;
        }

        return false;
    }
}
