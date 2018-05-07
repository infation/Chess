package ramapo.edu.sminev.chess;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.Timer;
import java.util.Vector;

import ramapo.edu.sminev.chess.Model.Bishop;
import ramapo.edu.sminev.chess.Model.GameState;
import ramapo.edu.sminev.chess.Model.Knight;
import ramapo.edu.sminev.chess.Model.Location;
import ramapo.edu.sminev.chess.Model.Piece;
import ramapo.edu.sminev.chess.Model.Queen;
import ramapo.edu.sminev.chess.Model.Rook;
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


    public void showPopup(final Location newLoc) {
        CharSequence pieces[] = new CharSequence[] {"Queen", "Knight", "Rook", "Bishop"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a piece for promotion");
        builder.setItems(pieces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                GameState.updateStateForPromotion(oldLoc, newLoc, promoteTo(which));
                GraveyardView.updateView(GameActivity.this);
                if (GameState.isCheckMate()) {
                    Toast.makeText(GameActivity.this, "CheckMate!!!!!!!", Toast.LENGTH_LONG).show();
                    //BoardView.initializeLayout(GameActivity.this);
                }
                if (GameState.isCheck()) {
                    Toast.makeText(GameActivity.this, "King in check", Toast.LENGTH_SHORT).show();
                }
                GameState.getPlayers()[0].play();
                GraveyardView.updateView(GameActivity.this);
                if (GameState.isCheckMate()) {
                    Toast.makeText(GameActivity.this, "CheckMate!!!!!!!", Toast.LENGTH_LONG).show();
                    //BoardView.initializeLayout(GameActivity.this);
                }
                if (GameState.isCheck()) {
                    Toast.makeText(GameActivity.this, "King in check", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }

    public Piece promoteTo(int which){
        switch(which){
            case 0:
                return new Queen(GameState.getTurn());
            case 1:
                return new Knight(GameState.getTurn());
            case 2:
                return new Rook(GameState.getTurn());
            case 3:
                return new Bishop(GameState.getTurn());
        }
        return null;
    }

    /*public void showPopup(){
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.promotion_popup, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.FILL_PARENT);

        ImageButton queenButton = popupView.findViewById(R.id.queen);
        queenButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }});

        ImageButton rookButton = popupView.findViewById(R.id.rook);
        rookButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }});

        ImageButton bishopButton = popupView.findViewById(R.id.bishop);
        bishopButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }});


        ImageButton knightButton = popupView.findViewById(R.id.knight);
        knightButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }});

        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.showAsDropDown(BoardView.getView());
    }
*/
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
                if(GameState.getTurn()== 1) {
                    if (GameState.isPromotionMove(oldLoc, Location.parseId(view.getId())))
                        showPopup(Location.parseId(view.getId()));
                    else {
                        GameState.updateState(oldLoc, Location.parseId(view.getId()));
                        GraveyardView.updateView(GameActivity.this);
                    }
                    if(GameState.isDraw()){
                        Toast.makeText(GameActivity.this, "Draw!!!", Toast.LENGTH_LONG).show();
                        /*try {
                            Thread.sleep(5000);
                            Intent intent = new Intent(GameActivity.this, EndGame.class);
                            GameActivity.this.startActivity(intent);
                        } catch (InterruptedException e) {
                        }*/
                    }

                    if (GameState.isCheckMate()) {
                        Toast.makeText(GameActivity.this, "Check-Mate!!!", Toast.LENGTH_LONG).show();
                        //BoardView.initializeLayout(GameActivity.this);
                        /*try {
                            Thread.sleep(5000);
                            Intent intent = new Intent(GameActivity.this, EndGame.class);
                            GameActivity.this.startActivity(intent);
                        } catch (InterruptedException e) {
                        }*/
                    }
                }
                if (GameState.getTurn() == 0){
                    GameState.getPlayers()[0].play();
                    GraveyardView.updateView(GameActivity.this);
                    if(GameState.isDraw()){
                        Toast.makeText(GameActivity.this, "Draw!!!", Toast.LENGTH_LONG).show();
                        /*try {
                            Thread.sleep(5000);
                            Intent intent = new Intent(GameActivity.this, EndGame.class);
                            GameActivity.this.startActivity(intent);
                        } catch (InterruptedException e) {
                        }*/
                    }

                    if (GameState.isCheckMate()) {
                        Toast.makeText(GameActivity.this, "Check-Mate!!!", Toast.LENGTH_LONG).show();
                        //BoardView.initializeLayout(GameActivity.this);
                        /*try {
                            Thread.sleep(5000);
                            Intent intent = new Intent(GameActivity.this, EndGame.class);
                            GameActivity.this.startActivity(intent);
                        } catch (InterruptedException e) {
                        }*/

                    }
                    if (GameState.isCheck()) {
                        Toast.makeText(GameActivity.this, "King in check", Toast.LENGTH_SHORT).show();
                    }
                }
                //boardView.updateView(boardState);
                clickCount = false;
            }
        }

    });

    public void endGame(){
        Intent intent = new Intent(GameActivity.this, EndGame.class);
        GameActivity.this.startActivity(intent);
    }

}


