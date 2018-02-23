package ramapo.edu.sminev.chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ramapo.edu.sminev.chess.Model.Coords;
import ramapo.edu.sminev.chess.Model.GameState;
import ramapo.edu.sminev.chess.Model.Piece;
import ramapo.edu.sminev.chess.View.BoardView;

public class GameActivity extends AppCompatActivity{

    //private BoardView boardView;
    private boolean clickCount = false;
    private int[] oldLoc = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //boardView= new BoardView(this);

        GameState.initializeGame();
        BoardView.initializeLayout(this);
        //ViewGroup grid = findViewById(R.id.board);
        //grid.addView(boardView);

    }


    public View.OnClickListener buttonHandler = (new View.OnClickListener() {
        public void onClick(View view){
            if(!clickCount) {
               oldLoc[0] = Piece.parseId(view.getId())[0];
               oldLoc[1] = Piece.parseId(view.getId())[1];
               clickCount = true;
               //boardView.showMoves(oldPosId);
                // ImageButton b = view.findViewById(id);
                //b.setImageResource(android.R.color.transparent);
            }
            else{
                //if(GameState.getBoard()[])
                int newLoc[] = new int[2];
                newLoc[0] = Piece.parseId(view.getId())[0];
                newLoc[1] = Piece.parseId(view.getId())[1];
                GameState.updateState(oldLoc[0], oldLoc[1], newLoc[0], newLoc[1]);
                //boardView.updateView(boardState);
                clickCount = false;
            }
        }

    });

    public void checkRules(){

    }

}

