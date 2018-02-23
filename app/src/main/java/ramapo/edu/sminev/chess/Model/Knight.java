package ramapo.edu.sminev.chess.Model;

import ramapo.edu.sminev.chess.R;

/**
 * Created by sminev on 2/22/18.
 */

public class Knight extends Piece {

    public Knight(int a_color, int a_row, int a_col){
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
}
