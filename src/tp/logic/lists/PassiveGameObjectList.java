package tp.logic.lists;

import tp.logic.objects.PasiveGameObject;

public class PassiveGameObjectList {
    private static final int INITIAL_SIZE = 5;

    private PasiveGameObject[] list;
    private int count;

    public PassiveGameObjectList() {
        this(INITIAL_SIZE);
    }

    public PassiveGameObjectList(int size) {
        this.count = 0;
        this.list = new PasiveGameObject[size];
    }

    public boolean add(PasiveGameObject obj) {

        if (count == list.length)
            resize();

        list[count++] = obj;

        return true;
    }

    public boolean remove(PasiveGameObject obj) {
        boolean removed = false;
        int pos;

        if ((pos = searchPosition(obj)) != -1) {
            removed = true;
            for (int i = pos; i < count; i++) {
                list[i] = list[i + 1];
            }
            count--;
        }

        return removed;
    }

    public PasiveGameObject search(PasiveGameObject obj) {
        int pos = searchPosition(obj);
        return pos != -1 ? list[pos] : null;
    }

    public PasiveGameObject search(int x, int y) {
        int i = 0;
        boolean found = false;

        while (!found && i < count) {
            if (list[i].getX() == x && list[i].getY() == y)
                found = true;
            else
                i++;
        }

        return found ? list[i] : null;
    }

    private int searchPosition(PasiveGameObject obj) {
        int i = 0;
        boolean found = false;

        if (obj != null) {
            while (!found && i < count) {
                if (list[i].equals(obj)) {
                    found = true;
                } else
                    i++;
            }
        }

        return found ? i : -1;
    }

    private void resize() {
        PasiveGameObject[] l = new PasiveGameObject[count + INITIAL_SIZE];
        System.arraycopy(list, 0, l, 0, count);
        this.list = l;
    }

    public int size() {
        return this.count;
    }

    public boolean isPositionEmpty(int x, int y){

        return search(x,y) == null;
    }
}
