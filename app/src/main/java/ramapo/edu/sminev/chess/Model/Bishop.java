package ramapo.edu.sminev.chess.Model;

import java.util.Vector;

import ramapo.edu.sminev.chess.R;


public class Bishop extends Piece {

    public Bishop(int a_color, int a_row, int a_col){
        super();
        this.setType(PieceType.BISHOP);
        setColor(a_color);
        if(a_color == 0){
            setDrawableId(R.drawable.black_bishop);
        }
        else{
            setDrawableId(R.drawable.white_bishop);
        }
    }

    public Vector<Location> getPredefinedMoves(Location a_loc){
        Vector<Location> moves = processUpperLeft(a_loc);
        moves.addAll(processUpperRight(a_loc));
        moves.addAll(processBottomLeft(a_loc));
        moves.addAll(processBottomRight(a_loc));
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

}
