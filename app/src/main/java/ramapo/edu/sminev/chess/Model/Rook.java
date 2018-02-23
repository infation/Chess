package ramapo.edu.sminev.chess.Model;

import ramapo.edu.sminev.chess.R;

/**
 * Created by sminev on 2/22/18.
 */

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
}
