package tp.p1.Logic;

import tp.p1.Logic.objects.Peashooter;
import tp.p1.Logic.objects.Sunflower;
import tp.p1.Logic.objects.Zombie;
import tp.p1.util.MyStringUtils;

public class GamePrinter {
    int dimX;
    int dimY;
    String[][] board;
    final String space = " ";

    public GamePrinter(Game game, int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;

        encodeGame(game);
    }

    private void encodeGame(Game game) {
        board = new String[dimX][dimY];
        Sunflower sf;
        Peashooter ps;
        Zombie z;
        for(int i = 0; i < dimX; i++) {
            for(int j = 0; j < dimY; j++) {

                if((sf = game.getSFInPosition(i,j)) != null)
                    board[i][j] = sf.toString();
                else if ((ps = game.getPSInPosition(i,j)) != null)
                    board[i][j] = ps.toString();
                else if ((z = game.getZInPosition(i,j)) != null)
                    board[i][j] = z.toString();
                else
                    board[i][j] =  space;
            }
        }
    }

    public String toString() {

        int cellSize = 7;
        int marginSize = 2;
        String vDelimiter = "|";
        String hDelimiter = "-";

        String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
        String margin = MyStringUtils.repeat(space, marginSize);
        String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

        StringBuilder str = new StringBuilder();

        str.append(lineDelimiter);

        for(int i=0; i<dimX; i++) {
            str.append(margin).append(vDelimiter);
            for (int j=0; j<dimY; j++) {
                str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
            }
            str.append(lineDelimiter);
        }
        return str.toString();
    }
}