package ramapo.edu.sminev.chess.Model;

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
}
