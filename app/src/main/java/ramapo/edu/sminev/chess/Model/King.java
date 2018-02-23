package ramapo.edu.sminev.chess.Model;

import java.util.Vector;

import ramapo.edu.sminev.chess.R;

public class King extends Piece {

    public King(int a_color, int a_row, int a_col){
        super();
        this.setType(PieceType.KING);
        setColor(a_color);
        if(a_color == 0){
            setDrawableId(R.drawable.black_king);
        }
        else{
            setDrawableId(R.drawable.white_king);
        }
    }

    /*public Vector<Coords> getMoves(){
        Vector<Coords> moves = new Vector<>();
        for(int row = getCoords().getRow()-1; row < getCoords().getRow()+2; row++){

            if(row < 0 || row > 7) continue;

            for(int col = getCoords().getCol()-1; col < getCoords().getCol()+2; col++){
                //If outside of bounds of board
                if(col < 0 || col > 7) continue;
                if(row == getCoords().getRow() && col == getCoords().getCol()) continue;
                Vector<Piece> pieces = GameState.getPlayers()[this.getColor()].getPieces();
                for(int i = 0; i < pieces.size(); i++){
                    if(pieces.get(i).getCoords().isEqual(row,col)) break;
                }


                //moves.add(new Coords(row, col));
            }
        }
        return moves;
    }*/
}
