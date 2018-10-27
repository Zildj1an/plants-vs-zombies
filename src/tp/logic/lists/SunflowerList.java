package tp.logic.lists;

import tp.logic.objects.Sunflower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SunflowerList {
    List<Sunflower> list = new ArrayList<>();

    public void add(Sunflower sunflower){
        list.add(sunflower);
    }

    public void remove(Sunflower sunflower){
        list.remove(sunflower);
    }

    public void update(){
        for(Sunflower sf : list){
            sf.update();
        }
    }

    public int size(){
        return list.size();
    }

    public Sunflower search(int x, int y){
        Iterator<Sunflower> it = this.list.iterator();
        boolean found = false;
        Sunflower sf = null;

        while (!found && it.hasNext()){
            sf = it.next();

            if(sf.getX() == x && sf.getY() == y)
                found = true;
        }

        return found ? sf : null;
    }
}
