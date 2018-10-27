package tp.Logic.Lists;

import tp.Logic.objects.Zombie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZombieList {
    List<Zombie> list = new ArrayList<>();

    public void add(Zombie zombie){
        list.add(zombie);
    }

    public boolean remove(Zombie zombie){
        return list.remove(zombie);
    }

    public void update(){
        for (Zombie z : list){
            z.update();
        }
    }

    public Zombie search(int x, int y){
        Iterator<Zombie> it = this.list.iterator();
        boolean found = false;
        Zombie z = null;

        while (!found && it.hasNext()){
            z = it.next();

            if(z.getX() == x && z.getY() == y)
                found = true;
        }

        return found ? z : null;
    }

    public int size(){
        return list.size();
    }
}
