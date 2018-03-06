package ramapo.edu.sminev.chess.Model;


import java.util.Vector;

import ramapo.edu.sminev.chess.R;

public class Pawn extends Piece {

    private boolean dmove;

    public Pawn(int a_color){
        super();
        this.setType(PieceType.PAWN);
        dmove = false;
        setColor(a_color);
        if(a_color == 0){
            setDrawableId(R.drawable.black_pawn);
        }
        else{
            setDrawableId(R.drawable.white_pawn);
        }
    }

    public Vector<Location> getPredefinedMoves(Location a_loc) {
        if(getColor() == 0) {
            return processBlackMoves(a_loc);
        }
        else{
            return processWhiteMoves(a_loc);
        }
    }

    public void setDoubleMove(boolean a_dmove){
        dmove = a_dmove;
    }

    public boolean getDoubleMove() { return dmove; }


    private Vector<Location> processWhiteMoves(Location a_loc){
        Vector<Location> moves = new Vector<>();
        for(int i = a_loc.col-1 ; i < a_loc.col+2; i+=2){
            //Out of bounds
            if(i < 0 || i > 7) continue;
            //White color pieces diagonally
            if(GameState.getBoard()[a_loc.row-1][i]!=null && GameState.getBoard()[a_loc.row-1][i].getColor() == 0)
                moves.add(new Location(a_loc.row - 1, i));
            //En passant
            else if(GameState.getBoard()[a_loc.row][i]!=null && GameState.getBoard()[a_loc.row][i].getType() == PieceType.PAWN
                    && GameState.getBoard()[a_loc.row][i].getColor() == 0 && ((Pawn)GameState.getBoard()[a_loc.row][i]).getDoubleMove())
                moves.add(new Location(a_loc.row-1, i));
        }
        //If there is a piece in front of the pawn
        if(GameState.getBoard()[a_loc.row-1][a_loc.col]!=null)
            return moves;
        else
            moves.add(new Location(a_loc.row-1,a_loc.col));
        //If the pawn is in the initial place it can move 1 more square ahead
        if(a_loc.row == 6)
            if(GameState.getBoard()[4][a_loc.col]==null)
                moves.add(new Location(4, a_loc.col));

        return moves;
    }

    private Vector<Location> processBlackMoves(Location a_loc){
        Vector<Location> moves = new Vector<>();
        for(int i = a_loc.col-1 ; i < a_loc.col+2; i+=2){
            //Out of bounds
            if(i < 0 || i > 7) continue;
            //White color pieces diagonally
            if(GameState.getBoard()[a_loc.row+1][i]!=null && GameState.getBoard()[a_loc.row+1][i].getColor() == 1)
                moves.add(new Location(a_loc.row + 1, i));
            //En passant
            else if(GameState.getBoard()[a_loc.row][i]!=null && GameState.getBoard()[a_loc.row][i].getType() == PieceType.PAWN
                    && GameState.getBoard()[a_loc.row][i].getColor() == 1 && ((Pawn)GameState.getBoard()[a_loc.row][i]).getDoubleMove())
                moves.add(new Location(a_loc.row+1, i));
        }
        //If there is a piece in front of the pawn
        if(GameState.getBoard()[a_loc.row+1][a_loc.col]!=null)
            return moves;
        else
            moves.add(new Location(a_loc.row+1,a_loc.col));
        //If the pawn is in the initial place it can move 1 more square ahead
        if(a_loc.row == 1)
            if(GameState.getBoard()[3][a_loc.col]==null)
                moves.add(new Location(3, a_loc.col));

        return moves;
    }


}
