package ramapo.edu.sminev.chess.Model;

import ramapo.edu.sminev.chess.R;

/**
 * Created by sminev on 2/22/18.
 */

public class King extends Piece {

    public King(int a_color){
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
}
