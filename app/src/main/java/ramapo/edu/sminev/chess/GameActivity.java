package ramapo.edu.sminev.chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Vector;

import ramapo.edu.sminev.chess.Model.GameState;
import ramapo.edu.sminev.chess.Model.Location;
import ramapo.edu.sminev.chess.Model.Piece;
import ramapo.edu.sminev.chess.View.BoardView;

public class GameActivity extends AppCompatActivity{

    //private BoardView boardView;
    private boolean clickCount = false;
    private Location oldLoc;

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
               oldLoc = Location.parseId(view.getId());
               clickCount = true;
               BoardView.showMoves(oldLoc);
               //boardView.showMoves(oldPosId);
                // ImageButton b = view.findViewById(id);
                //b.setImageResource(android.R.color.transparent);
            }
            else{
                GameState.updateState(oldLoc, Location.parseId(view.getId()));
                //boardView.updateView(boardState);
                clickCount = false;
            }
        }

    });

    public void checkRules(){

    }

}

