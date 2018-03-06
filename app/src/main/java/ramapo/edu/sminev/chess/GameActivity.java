package ramapo.edu.sminev.chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Vector;

import ramapo.edu.sminev.chess.Model.GameState;
import ramapo.edu.sminev.chess.Model.Location;
import ramapo.edu.sminev.chess.Model.Piece;
import ramapo.edu.sminev.chess.View.BoardView;
import ramapo.edu.sminev.chess.View.GraveyardView;

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
        GraveyardView.initializeLayout(this);
        //ViewGroup grid = findViewById(R.id.board);
        //grid.addView(boardView);

    }


    public View.OnClickListener buttonHandler = (new View.OnClickListener() {
        public void onClick(View view){
            if(!clickCount) {

                if(GameState.isCorrectSelection(Location.parseId(view.getId()))) {
                    oldLoc = Location.parseId(view.getId());
                    clickCount = true;
                    BoardView.showMoves(oldLoc);
                }
            }
            else{
                GameState.updateState(oldLoc, Location.parseId(view.getId()));
                GraveyardView.updateView(GameActivity.this);
                if(GameState.isCheckMate()){
                    Toast.makeText(GameActivity.this, "CheckMate!!!!!!!", Toast.LENGTH_LONG).show();
                    //BoardView.initializeLayout(GameActivity.this);
                }
                if(GameState.isCheck()){
                    Toast.makeText(GameActivity.this, "King in check", Toast.LENGTH_SHORT).show();
                }

                //boardView.updateView(boardState);
                clickCount = false;
            }
        }

    });

}

