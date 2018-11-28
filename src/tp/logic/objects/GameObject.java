package tp.logic.objects;

public class GameObject {
    protected int x;
    protected int y;
    protected char symbol;

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
