package tp.logic.gamePrinter;

import tp.logic.Game;
import tp.logic.objects.Plant;
import tp.logic.objects.Zombie;
import tp.util.MyStringUtils;

public class ReleasePrinter extends BoardPrinter implements GamePrinter{

    @Override
    public String printGame(Game game) {
        int cellSize = 7;
        int marginSize = 2;
        String vDelimiter = "|";
        String hDelimiter = "-";

        String rowDelimiter = MyStringUtils.repeat(hDelimiter, (Game.COLUMNS * (cellSize + 1)) - 1);
        String margin = MyStringUtils.repeat(space, marginSize);
        String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

        StringBuilder str = new StringBuilder();
        str.append(lineDelimiter);

        encodeGame(game);

        for(int i=0; i<Game.ROWS; i++) {
            str.append(margin).append(vDelimiter);
            for (int j=0; j<Game.COLUMNS; j++) {
                str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
            }
            str.append(lineDelimiter);
        }
        return str.toString();
    }

    @Override
    public void encodeGame(Game game) {
        board = new String[Game.ROWS][Game.COLUMNS];
        Plant p;
        Zombie z;
        for(int i = 0; i < Game.ROWS; i++) {
            for(int j = 0; j < Game.COLUMNS; j++) {

                if((p = game.getPInPosition(i,j)) != null)
                    board[i][j] = p.toString();
                else if ((z = game.getZInPosition(i,j)) != null)
                    board[i][j] = z.toString();
                else
                    board[i][j] =  space;
            }
        }
    }
}
