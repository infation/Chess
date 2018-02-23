package ramapo.edu.sminev.chess.Model;

import ramapo.edu.sminev.chess.R;

/**
 * Created by sminev on 2/22/18.
 */

public class Queen extends Piece {
    public Queen(int a_color, int a_row, int a_col){
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
}
