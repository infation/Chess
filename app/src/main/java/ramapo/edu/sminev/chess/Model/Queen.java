package ramapo.edu.sminev.chess.Model;

import java.util.Vector;

import ramapo.edu.sminev.chess.R;

/**
 * Created by sminev on 2/22/18.
 */

public class Queen extends Piece {
    public Queen(int a_color){
        super();
        this.setType(PieceType.QUEEN);
        setColor(a_color);
        if(a_color == 0){
            setDrawableId(R.drawable.black_queen);
        }
        else{
            setDrawableId(R.drawable.white_queen);
        }
    }

    public Vector<Location> getPredefinedMoves(Location a_loc){
        Vector<Location> moves = processUpperLeft(a_loc);
        moves.addAll(processUpperRight(a_loc));
        moves.addAll(processBottomLeft(a_loc));
        moves.addAll(processBottomRight(a_loc));
        moves.addAll(processTop(a_loc));
        moves.addAll(processRight(a_loc));
        moves.addAll(processLeft(a_loc));
        moves.addAll(processBottom(a_loc));
        return moves;
    }

    private Vector<Location> processUpperLeft(Location a_loc){
        Vector<Location> moves = new Vector<>();
        int j = a_loc.col-1;
        for(int i = a_loc.row-1; i >= 0; i--){
            if(j < 0) break;
            if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getColor()) break;
            if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getOppositeColor()){
                moves.add(new Location(i,j));
                break;
            }
            moves.add(new Location(i, j));
            j--;
        }
        return moves;
    }
    private Vector<Location> processUpperRight(Location a_loc){
        Vector<Location> moves = new Vector<>();
        int j = a_loc.col+1;
        for(int i = a_loc.row-1; i >= 0; i--){
            if(j > 7) break;
            if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getColor()) break;
            if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getOppositeColor()){
                moves.add(new Location(i,j));
                break;
            }
            moves.add(new Location(i, j));
            j++;
        }
        return moves;
    }
    private Vector<Location> processBottomLeft(Location a_loc){
        Vector<Location> moves = new Vector<>();
        int j = a_loc.col-1;
        for(int i = a_loc.row+1; i <= 7; i++){
            if(j < 0) break;
            if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getColor()) break;
            if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getOppositeColor()){
                moves.add(new Location(i,j));
                break;
            }
            moves.add(new Location(i, j));
            j--;
        }
        return moves;
    }
    private Vector<Location> processBottomRight(Location a_loc){
        Vector<Location> moves = new Vector<>();
        int j = a_loc.col+1;
        for(int i = a_loc.row+1; i <= 7; i++){
            if(j > 7) break;
            if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getColor()) break;
            if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getOppositeColor()){
                moves.add(new Location(i,j));
                break;
            }
            moves.add(new Location(i, j));
            j++;
        }
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
