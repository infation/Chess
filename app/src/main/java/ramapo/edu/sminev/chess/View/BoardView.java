package ramapo.edu.sminev.chess.View;

import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import ramapo.edu.sminev.chess.Model.BoardState;
import ramapo.edu.sminev.chess.Model.Piece;
import ramapo.edu.sminev.chess.R;

public class BoardView {

    public static final int TAN = Color.rgb(172,150,120);
    public static final int BROWN = Color.rgb(129,92,10);
    private LinearLayout board;

    public BoardView(Activity activity){

        board = activity.findViewById(R.id.board);

        for(int i = 0; i < 8; i++){
            LinearLayout row = new LinearLayout(activity);
            row.setWeightSum(8);
            row.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            params.weight = 1;
            row.setLayoutParams(params);
            for(int j = 0; j < 8; j++){
                ImageButton square = new ImageButton(activity);
                square.setLayoutParams(params);
                //square.setImageResource(R.drawable.black_pawn);
                if ((j+i)%2==0)
                    square.setBackgroundColor(TAN);
                else
                    square.setBackgroundColor(BROWN);

                row.addView(square);
                int id = (i*8) + j;
                square.setId(id);
                //board.addView(square);
            }
            board.addView(row);
        }
    }


    public void update(BoardState boardState){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j< 8; j++){
                if(boardState.getSquare(i,j).getType() != null) {
                    ImageButton piece = board.findViewById((i * 8) + j);
                    piece.setImageResource(boardState.getSquare(i,j).getDrawableId());
                }
            }
        }
    }

    public void setPiecesView(){

    }


}