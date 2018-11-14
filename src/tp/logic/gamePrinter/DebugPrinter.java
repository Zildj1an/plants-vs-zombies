package tp.logic.gamePrinter;

import tp.logic.Game;
import tp.logic.objects.GameObject;
import tp.util.MyStringUtils;

public class DebugPrinter extends BoardPrinter implements GamePrinter{

    private int pos;

    @Override
    public void encodeGame(Game game) {
        board = new String[1][100];
        pos = 0;
        GameObject g;

        for (int i = 0; i < Game.ROWS; i++){
            for (int j = 0; j < Game.COLUMNS; j++){
                if((g = game.getPInPosition(i,j)) != null)
                    board[0][pos++] = g.debugPrint();
                else if ((g = game.getZInPosition(i,j)) != null)
                    board[0][pos++] = g.debugPrint();
            }
        }

    }

    @Override
    public String printGame(Game game) {
        int cellSize = 18;
        int marginSize = 0;
        String vDelimiter = "|";
        String hDelimiter = "-";

        encodeGame(game);

        String rowDelimiter = MyStringUtils.repeat(hDelimiter, ((cellSize * pos) + pos) - 1);
        String margin = MyStringUtils.repeat(space, marginSize);
        String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

        StringBuilder str = new StringBuilder();

        str.append(game.debugInfo());

        str.append(lineDelimiter);
        if (pos != 0)
            str.append(vDelimiter);

        for (int i = 0; i < pos; i++){
            str.append(board[0][i]).append(vDelimiter);
        }
        str.append(lineDelimiter);

        return str.toString();
    }
}
