package ramapo.edu.sminev.chess.Model;

import java.util.Vector;

import ramapo.edu.sminev.chess.R;

public class King extends Piece {

    public King(int a_color) {
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
        return getAvailableMoves(a_loc,moves);
    }

    public Location castling(Location a_loc){
        Piece piece = GameState.getBoard()[a_loc.row][a_loc.col];
        if(!piece.getIsMoved()&&!GameState.isCheck()){
            int row = getColor()*7;
            if(GameState.getBoard()[row][7]!=null&&!GameState.getBoard()[row][7].getIsMoved()&&GameState.getBoard()[row][6]==null && GameState.getBoard()[row][5] == null)
                return new Location(row,6);
            if(GameState.getBoard()[row][0]!=null&&!GameState.getBoard()[row][0].getIsMoved()&&GameState.getBoard()[row][1]==null && GameState.getBoard()[row][2] == null&&GameState.getBoard()[row][3]==null)
                return new Location(row,2);
        }
        return null;
    }

    public Vector<Location> getMovesWithoutCheck(Location a_loc){
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

        for (int i = 0; i < moves.size(); i++) {
            Location loc = moves.get(i);
            //int color = GameState.getBoard()[a_loc.row][a_loc.col].getColor();
            if (GameState.getBoard()[loc.row][loc.col] != null && GameState.getBoard()[loc.row][loc.col].getColor() == getColor())
                moves.remove(i);
        }
        return moves;
    }


    private Vector<Location> getAvailableMoves(Location a_loc, Vector<Location> a_moves) {
        Vector<Location> moves = new Vector<>();
        for (int i = 0; i < a_moves.size(); i++) {
            Location loc = a_moves.get(i);
            //int color = GameState.getBoard()[a_loc.row][a_loc.col].getColor();
            if (GameState.getBoard()[loc.row][loc.col] != null && GameState.getBoard()[loc.row][loc.col].getColor() == getColor())
                continue;
            Piece piece = GameState.getBoard()[loc.row][loc.col];
            GameState.startSimulation(a_loc, loc);
            if(!isInCheck(loc)){
                moves.add(loc);
            }
            GameState.endSimulation(a_loc, loc, piece);
        }
        Location castling = castling(a_loc);
        if(castling!=null){
            moves.add(castling);
        }
        return moves;
    }

    public boolean isInCheck(Location a_loc){
        Vector<Location> moves = new Vector<>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getColor() == getOppositeColor())
                    if(GameState.getBoard()[i][j].getType()!=PieceType.KING)
                        moves.addAll(GameState.getBoard()[i][j].getPredefinedMoves(new Location(i,j)));
                    else{
                        moves.addAll(((King)GameState.getBoard()[i][j]).getMovesWithoutCheck(new Location(i,j)));
                    }
            }
        }

        for(int i = 0; i < moves.size(); i++){
            Location loc = moves.get(i);
            if(loc.row == a_loc.row && loc.col == a_loc.col){
                return true;
            }
        }
        return false;
    }

}
