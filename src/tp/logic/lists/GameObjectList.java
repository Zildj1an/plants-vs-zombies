package tp.logic.lists;

import tp.logic.objects.GameObject;

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

    public GameObject searchPosition(int x, int y){
        return null;
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
}
