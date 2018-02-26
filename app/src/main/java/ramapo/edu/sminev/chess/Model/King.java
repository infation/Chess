package ramapo.edu.sminev.chess.Model;

import java.util.Vector;

import ramapo.edu.sminev.chess.R;

public class King extends Piece {

    public King(int a_color, int a_row, int a_col) {
        super();
        this.setType(PieceType.KING);
        setColor(a_color);
        if (a_color == 0) {
            setDrawableId(R.drawable.black_king);
        } else {
            setDrawableId(R.drawable.white_king);
        }
    }

    public Vector<Location> getPredefinedMoves(Location a_loc) {
        Vector<Location> moves = new Vector<>();
        for (int row = a_loc.row - 1; row < a_loc.row + 2; row++) {

            //If out of bounds
            if (row < 0 || row > 7) continue;

            for (int col = a_loc.col - 1; col < a_loc.col+ 2; col++) {
                //If outside of bounds of board
                if (col < 0 || col > 7) continue;
                //Same location
                if (row == a_loc.row && col == a_loc.col) continue;
                moves.add(new Location(row, col));
            }
        }
        return getAvailableMoves(a_loc, moves);
    }


}
