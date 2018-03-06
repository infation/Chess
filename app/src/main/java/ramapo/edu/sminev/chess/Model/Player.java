package ramapo.edu.sminev.chess.Model;

import java.util.Vector;

/**
 * Created by sminev on 2/22/18.
 */

public class Player {


    private Vector<Piece> graveyard;

    public Player(){
        graveyard = new Vector<>();
    }

    public void addPiece(Piece a_piece){
        graveyard.add(a_piece);
    }

    public Vector<Piece> getGraveyard(){
        return graveyard;
    }

    public void play(){}

    /*
    private Vector<Piece> pieces;


    public Player(){
        pieces = new Vector<>();
    }

    public Vector<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Vector<Piece> pieces) {
        this.pieces = pieces;
    }

    public void addPiece(Piece p){
        pieces.add(p);
    }

    public int findPieceById(int id){
        for(int i=0;i < pieces.size();i++){
            if(pieces.get(i).getId()==id){
                return i;
            }
        }
        System.out.println("Couldn't find ID....");
        return -1;
    }

    public void initializePieces(){}
    public void initializePawns(){}
    public void initializeRooks(){}
    public void initializeBishops(){}
    public void initializeKnights(){}
    public void initializeKing(){}
    public void initializeQueen(){}
    */
}
