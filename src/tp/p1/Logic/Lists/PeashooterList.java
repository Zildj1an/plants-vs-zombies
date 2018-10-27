package tp.p1.Logic.Lists;

import tp.p1.Logic.objects.Peashooter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PeashooterList {
    List<Peashooter> list = new ArrayList<>();

    public void add(Peashooter peashooter){
        list.add(peashooter);
    }

    public boolean remove(Peashooter peashooter){
        return list.remove(peashooter);
    }

    public void update(){
        for(Peashooter ps : list){
            ps.update();
        }
    }

    public Peashooter search(int x, int y){
        Iterator<Peashooter> it = this.list.iterator();
        boolean found = false;
        Peashooter pS = null;

        while (!found && it.hasNext()){
            pS = it.next();

            if(pS.getX() == x && pS.getY() == y)
                found = true;
        }

        return found ? pS: null;
    }

    public int size(){
        return list.size();
    }
}
