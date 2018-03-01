package ramapo.edu.sminev.chess.View;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import ramapo.edu.sminev.chess.GameActivity;
import ramapo.edu.sminev.chess.Model.GameState;
import ramapo.edu.sminev.chess.Model.Location;
import ramapo.edu.sminev.chess.Model.Piece;
import ramapo.edu.sminev.chess.R;

public class BoardView {

    private static final int TAN = Color.rgb(172,150,120);
    private static final int BROWN = Color.rgb(129,92,10);
    private static LinearLayout board;
    private static TextView turn;

    public BoardView(){

    }

    public static void initializeLayout(GameActivity activity){

        board = activity.findViewById(R.id.board);
        turn = activity.findViewById(R.id.turn);
        turn.setText("Turn: Human");
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

                //Set the id of the particular button
                int id = (i*8) + j;
                square.setId(id);
                //Add Listener
                square.setOnClickListener(activity.buttonHandler);
                row.addView(square);
                //board.addView(square);
            }
            board.addView(row);
        }
        initializePieces();
    }

    /*public static void updateView(Player[] players){
        /*for(int i = 0; i < 8; i++){
            for(int j = 0; j< 8; j++){
                ImageButton piece = board.findViewById((i * 8) + j);
                if(boardState.getSquare(i,j).getType() != null) {

                    piece.setImageResource(boardState.getSquare(i,j).getDrawableId());
                }
                else{
                    piece.setImageResource(android.R.color.transparent);
                }
            }
        }
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < players[i].getPieces().size();j++){
                ImageButton piece = board.findViewById(players[i].getPieces().get(j).getCoords().convertToId());
                piece.setImageResource(players[i].getPieces().get(j).getDrawableId());
            }
        }
    }*/

    private static void initializePieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Location loc = new Location(i, j);
                ImageButton piece = board.findViewById(Location.convertToId(loc));
                if (GameState.getBoard()[i][j] != null) {
                    piece.setImageResource(GameState.getBoard()[i][j].getDrawableId());
                } else {
                    piece.setImageResource(android.R.color.transparent);
                }
            }
        }
    }

    public static void update(Location oldLoc, Location newLoc){
        updateLocation(newLoc);
        clearLocation(oldLoc);
        updateTurnView();
    }

    private static void updateTurnView(){
        if(GameState.getTurn() == 0){
            turn.setText("Turn: Computer");
        }
        else{
            turn.setText("Turn: Human");
        }
    }

    private static void updateLocation(Location loc){
        ImageButton piece = board.findViewById(Location.convertToId(loc));
        piece.setImageResource(GameState.getBoard()[loc.row][loc.col].getDrawableId());
    }

    private static void clearLocation(Location loc){
        ImageButton piece = board.findViewById(Location.convertToId(loc));
        piece.setImageResource(android.R.color.transparent);
    }



    public static void showMoves(Location a_loc){
        Vector<Location> moves = GameState.getBoard()[a_loc.row][a_loc.col].getPredefinedMoves(a_loc);
        GameState.getBoard()[a_loc.row][a_loc.col].simulateMoves(moves,a_loc);
        for(int i = 0; i < moves.size(); i++){
            ImageButton b = board.findViewById(Location.convertToId(moves.get(i)));
            b.setForeground(board.getResources().getDrawable(android.R.drawable.ic_delete));
        }
        //b.setImageResource(android.R.drawable.ic_delete);
        //b.setImageResource(android.R.drawable.ic_delete);

    }

    public static void clearMoves(Location a_loc){
        Vector<Location> moves = GameState.getBoard()[a_loc.row][a_loc.col].getPredefinedMoves(a_loc);
        for(int i = 0; i < moves.size(); i++){
            ImageButton b = board.findViewById(Location.convertToId(moves.get(i)));
            //b.setForeground(board.getResources().getDrawable(android.R.drawable.ic_delete));
            b.setForeground(board.getResources().getDrawable(android.R.color.transparent));
        }

    }


}