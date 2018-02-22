package ramapo.edu.sminev.chess.Model;


import ramapo.edu.sminev.chess.R;

public class Pawn extends Piece {

    public Pawn(int a_color){
        super();
        this.setType(PieceType.PAWN);
        setColor(a_color);
        if(a_color == 0){
            setDrawableId(R.drawable.black_pawn);
        }
        else{
            setDrawableId(R.drawable.white_pawn);
        }
    }

    public void play(){

    }

}
