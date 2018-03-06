package ramapo.edu.sminev.chess.View;

import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ramapo.edu.sminev.chess.GameActivity;
import ramapo.edu.sminev.chess.Model.GameState;
import ramapo.edu.sminev.chess.Model.Player;
import ramapo.edu.sminev.chess.R;

/**
 * Created by sminev on 3/5/18.
 */

public class GraveyardView {

    private static LinearLayout[] graveyards = new LinearLayout[2];

    public static void initializeLayout(GameActivity activity){
        graveyards[0] = activity.findViewById(R.id.human_graveyard);
        graveyards[1] = activity.findViewById(R.id.computer_graveyard);
        updateView(activity);
    }
    public static void updateView(GameActivity activity){
        for(int i = 0; i < 2; i++){
            Player player = GameState.getPlayers()[i];
            graveyards[i].removeAllViews();
            graveyards[i].setWeightSum(16);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            params.weight = 1;
            //humanGraveyard.setLayoutParams(params);
            for(int j = 0; j < 16; j++){
                ImageButton square = new ImageButton(activity);
                square.setBackgroundColor(Color.WHITE);
                square.setLayoutParams(params);
                square.setPadding(2,2,2,2);
                square.setScaleType(ImageView.ScaleType.FIT_CENTER);
                square.setAdjustViewBounds(true);
                if(player.getGraveyard().size() > j){
                    square.setImageResource(player.getGraveyard().get(j).getDrawableId());
                }
                graveyards[i].addView(square);
            }

        }
    }
}
