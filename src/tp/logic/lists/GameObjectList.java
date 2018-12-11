package tp.logic.lists;

import tp.exceptions.GameObjectException;
import tp.logic.Game;
import tp.logic.factories.PlantFactory;
import tp.logic.factories.ZombieFactory;
import tp.logic.objects.GameObject;

import java.io.BufferedWriter;
import java.io.IOException;

public class GameObjectList {
    private static final int INITIAL_SIZE = 5;

    private GameObject[] list;
    private int count;

    public GameObjectList(){
        this(INITIAL_SIZE);
    }

    public GameObjectList(int size){
        this.count = 0;
        this.list = new GameObject[size];
    }

    public GameObjectList(String[] list, Game g) throws GameObjectException{
        this();

        for (String s : list) {
            String[] dataObjectInfo = s.split(":");
            GameObject obj;

            if((obj = PlantFactory.getPlant(dataObjectInfo[0].toLowerCase())) == null && (obj = ZombieFactory.getZombie(dataObjectInfo[0].toLowerCase())) == null)
                throw new GameObjectException("Invalid type of game object");

            obj.setHealth(Integer.parseInt(dataObjectInfo[1]));
            obj.setPosition(Integer.parseInt(dataObjectInfo[2]),Integer.parseInt(dataObjectInfo[3]));
            obj.setCycle(Integer.parseInt(dataObjectInfo[4]));
            obj.setGame(g);

            add(obj);
        }
    }

    public boolean add(GameObject obj){

        if(count == list.length)
            resize();

        list[count++] = obj;

        return true;
    }

    public boolean remove(GameObject obj){
        boolean removed = false;
        int pos;

        if((pos = searchPosition(obj)) != -1){
            removed = true;
            for(int i = pos; i < count-1; i++){
                list[i] = list[i+1];
            }
            count--;
        }

        return removed;
    }

    public GameObject search(GameObject obj){
        int pos = searchPosition(obj);
        return pos != -1? list[pos] : null;
    }

    public GameObject search(int x, int y){
        int i = 0;
        boolean found = false;

        while(!found && i < count) {
            if (list[i].getX() == x && list[i].getY() == y)
                found = true;
            else
                i++;
        }

        return found ? list[i] : null;
    }

    private int searchPosition(GameObject obj){
        int i = 0;
        boolean found = false;

        if (obj != null){
            while (!found && i < count){
                if (list[i].equals(obj)){
                    found = true;
                }else
                    i++;
            }
        }

        return found? i : -1;
    }

    private void resize(){
        GameObject[] l = new GameObject[count+ INITIAL_SIZE];
        System.arraycopy(list, 0, l, 0, count);
        this.list = l;
    }

    public int size(){
        return this.count;
    }

    public void update(){
        for (int i = 0; i < count; i++)
            list[i].update();
    }

    public void store(BufferedWriter bf) throws IOException {
        for (int i = 0; i < count; i++) {
            list[i].store(bf);
            if (i != count-1)
                bf.write(',');
        }
    }

    public GameObject get(int i){
        if (i >= count)
            return null;

        return this.list[i];
    }
}
