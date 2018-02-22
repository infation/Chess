package ramapo.edu.sminev.chess;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import ramapo.edu.sminev.chess.View.BoardView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        BoardView boardView= new BoardView(this);

        boardView.setNumColumns(8);
        boardView.setNumRows(8);
        ViewGroup grid = findViewById(R.id.board);
        grid.addView(boardView);
    }

}
