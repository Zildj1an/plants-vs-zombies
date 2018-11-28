package tp.logic.lists;

import tp.logic.objects.ActiveGameObject;

public class ActiveGameObjectList {
    private static final int INITIAL_SIZE = 5;

    private ActiveGameObject[] list;
    private int count;

    public ActiveGameObjectList(){
        this(INITIAL_SIZE);
    }

    public ActiveGameObjectList(int size){
        this.count = 0;
        this.list = new ActiveGameObject[size];
    }

    public boolean add(ActiveGameObject obj){

        if(count == list.length)
            resize();

        list[count++] = obj;

        return true;
    }

    public boolean remove(ActiveGameObject obj){
        boolean removed = false;
        int pos;

        if((pos = searchPosition(obj)) != -1){
            removed = true;
            for(int i = pos; i < count; i++){
                list[i] = list[i+1];
            }
            count--;
        }

        return removed;
    }

    public ActiveGameObject search(ActiveGameObject obj){
        int pos = searchPosition(obj);
        return pos != -1? list[pos] : null;
    }

    public ActiveGameObject search(int x, int y){
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

    private int searchPosition(ActiveGameObject obj){
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
        ActiveGameObject[] l = new ActiveGameObject[count+ INITIAL_SIZE];
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

    //TODO ask this
    public int countZombies(){
        int num = 0;

        for (int i = 0; i < count; i++){
            if (!list[i].isPlant())
                num++;
        }

        return num;
    }
}
