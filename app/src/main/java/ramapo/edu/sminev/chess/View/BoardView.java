package ramapo.edu.sminev.chess.View;


import android.app.Activity;
import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import ramapo.edu.sminev.chess.R;

public class BoardView extends View{
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private boolean[][] cellChecked;

    public BoardView(Context context) {
        this(context, null);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        blackPaint.setColor(Color.rgb(129,92,10));
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;

        cellChecked = new boolean[numColumns][numRows];

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.rgb(197,164,142));

        for (int i = 0; i < numColumns; i++) {

            for (int j = 0; j < numRows; j++) {
                if ((j+i)%2==0) {
                    canvas.drawRect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight,
                            blackPaint);

                }
            }
        }

        for(int i = 0; i < numRows; i++){

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);
            cellChecked[column][row] = !cellChecked[column][row];
            invalidate();
        }

        return true;
    }
}
/*public class BoardView{

    public BoardView(Activity a){
        GridLayout board= a.findViewById(R.id.board);
        board.setColumnCount(8);
        board.setRowCount(8);
        for(int i = 0; i < 8;i ++){
            LinearLayout row = new LinearLayout(a);
            row.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0; j<8; j++){
                ImageButton b = new ImageButton(a);
                if((i+j)%2==0){
                    b.setBackgroundResource(R.drawable.black_checker);
                }
                else{
                    b.setBackgroundResource(R.drawable.white_checker);
                }
                b.setId(i*8+j);
                row.addView(b);
            }
            board.addView(row);
        }
    }

}*/
