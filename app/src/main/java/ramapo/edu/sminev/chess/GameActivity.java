package ramapo.edu.sminev.chess;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import ramapo.edu.sminev.chess.Model.BoardState;
import ramapo.edu.sminev.chess.View.BoardView;

public class GameActivity extends AppCompatActivity{

    private BoardView boardView;
    private BoardState boardState;
    private boolean clickCount = false;
    private int oldPosId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        boardView= new BoardView(this);
        boardState = new BoardState();
        boardView.updateView(boardState);
        //ViewGroup grid = findViewById(R.id.board);
        //grid.addView(boardView);

    }


    public View.OnClickListener buttonHandler = (new View.OnClickListener() {
        public void onClick(View view){
            if(!clickCount) {
               oldPosId = view.getId();
               clickCount = true;
               //boardView.showMoves(oldPosId);
                // ImageButton b = view.findViewById(id);
                //b.setImageResource(android.R.color.transparent);
            }
            else{
                boardState.updateState(oldPosId, view.getId());
                boardView.updateView(boardState);
                clickCount = false;
            }
        }

    });

}

