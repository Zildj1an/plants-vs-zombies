package tp.logic.objects;

public class Sun extends PasiveGameObject {
    private static final char SYMBOL = '*';
    private static final int VALUE = 10;

    public Sun(){
        super.symbol = SYMBOL;
    }

    public int getValue(){
        return VALUE;
    }
}
