package ramapo.edu.sminev.chess.Model;

import java.util.Vector;

import ramapo.edu.sminev.chess.R;

/**
 * Created by sminev on 2/22/18.
 */

public class Knight extends Piece {

    public Knight(int a_color){
        super();
        this.setType(PieceType.KNIGHT);
        setColor(a_color);
        if(a_color == 0){
            setDrawableId(R.drawable.black_knight);
        }
        else{
            setDrawableId(R.drawable.white_knight);
        }
    }


    public Vector<Location> getPredefinedMoves(Location a_loc){
        Vector<Location> moves = processTopLeft(a_loc);
        moves.addAll(processTopRight(a_loc));
        moves.addAll(processBottomLeft(a_loc));
        moves.addAll(processBottomRight(a_loc));
        for(int i = 0; i < moves.size(); i++){
            Location loc = moves.get(i);
            if(GameState.getBoard()[loc.row][loc.col]!=null && GameState.getBoard()[loc.row][loc.col].getColor()==getColor()) {
                moves.remove(i);
                i--;
            }
        }
        return moves;
    }

    private Vector<Location> processTopLeft(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        int row = a_loc.row - 2;
        int col = a_loc.col - 1;
        for (int i = 0; i < 2; i++) {
            if (row < 0 || col < 0);
            else
                moves.add(new Location(row, col));
            row++;
            col--;
        }
        return moves;
    }

    private Vector<Location> processTopRight(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        int row = a_loc.row - 2;
        int col = a_loc.col + 1;
        for (int i = 0; i < 2; i++) {
            if (row < 0 || col > 7);
            else
                moves.add(new Location(row, col));
            row++;
            col++;
        }
        return moves;
    }

    private Vector<Location> processBottomLeft(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        int row = a_loc.row + 2;
        int col = a_loc.col - 1;
        for (int i = 0; i < 2; i++) {
            if (row > 7 || col < 0);
            else
                moves.add(new Location(row, col));
            row--;
            col--;
        }
        return moves;
    }

    private Vector<Location> processBottomRight(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        int row = a_loc.row + 2;
        int col = a_loc.col + 1;
        for (int i = 0; i < 2; i++) {
            if (row > 7 || col > 7);
            else
                moves.add(new Location(row, col));
            row--;
            col++;
        }
        return moves;
    }

}
