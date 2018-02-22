package ramapo.edu.sminev.chess.Model;

import ramapo.edu.sminev.chess.R;

/**
 * Created by sminev on 2/22/18.
 */

public class Bishop extends Piece {

    public Bishop(int a_color){
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
}