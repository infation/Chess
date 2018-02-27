package ramapo.edu.sminev.chess.Model;

import java.util.Vector;

import ramapo.edu.sminev.chess.R;


public class Rook extends Piece{

    public Rook(int color, int a_row, int a_col){
        super();
        this.setType(PieceType.ROOK);
        setColor(color);
        if(color == 0){
            setDrawableId(R.drawable.black_rook);
        }
        else{
            setDrawableId(R.drawable.white_rook);
        }
    }

    public Vector<Location> getPredefinedMoves(Location a_loc){
        Vector<Location> moves = processTop(a_loc);
        moves.addAll(processRight(a_loc));
        moves.addAll(processLeft(a_loc));
        moves.addAll(processBottom(a_loc));
        return moves;
    }

    private Vector<Location> processTop(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        for (int i = a_loc.row-1; i >=0; i--) {
            if(GameState.getBoard()[i][a_loc.col]!=null && GameState.getBoard()[i][a_loc.col].getColor() == getColor()) break;
            if(GameState.getBoard()[i][a_loc.col]!=null && GameState.getBoard()[i][a_loc.col].getColor() == getOppositeColor()){
                moves.add(new Location(i,a_loc.col));
                break;
            }
            moves.add(new Location(i, a_loc.col));
        }
        return moves;
    }

    private Vector<Location> processLeft(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        for (int i = a_loc.col-1; i >=0; i--) {
            if(GameState.getBoard()[a_loc.row][i]!=null && GameState.getBoard()[a_loc.row][i].getColor() == getColor()) break;
            if(GameState.getBoard()[a_loc.row][i]!=null && GameState.getBoard()[a_loc.row][i].getColor() == getOppositeColor()){
                moves.add(new Location(a_loc.row,i));
                break;
            }
            moves.add(new Location(a_loc.row, i));
        }
        return moves;
    }

    private Vector<Location> processRight(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        for (int i = a_loc.col+1; i <= 7; i++) {
            if(GameState.getBoard()[a_loc.row][i]!=null && GameState.getBoard()[a_loc.row][i].getColor() == getColor()) break;
            if(GameState.getBoard()[a_loc.row][i]!=null && GameState.getBoard()[a_loc.row][i].getColor() == getOppositeColor()){
                moves.add(new Location(a_loc.row,i));
                break;
            }
            moves.add(new Location(a_loc.row, i));
        }
        return moves;
    }

    private Vector<Location> processBottom(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        for (int i = a_loc.row+1; i <= 7; i++) {
            if(GameState.getBoard()[i][a_loc.col]!=null && GameState.getBoard()[i][a_loc.col].getColor() == getColor()) break;
            if(GameState.getBoard()[i][a_loc.col]!=null && GameState.getBoard()[i][a_loc.col].getColor() == getOppositeColor()){
                moves.add(new Location(i,a_loc.col));
                break;
            }
            moves.add(new Location(i, a_loc.col));
        }
        return moves;
    }
}
