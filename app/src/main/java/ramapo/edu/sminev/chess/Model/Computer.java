package ramapo.edu.sminev.chess.Model;

import java.util.Random;
import java.util.Vector;

/**
 * Created by sminev on 2/22/18.
 */

public class Computer extends Player {

    public Computer(){
        super();
        //initializePieces();
    }

    public void play(){
        Vector<Location> pieces = new Vector<>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(GameState.getBoard()[i][j]!=null && GameState.getTurn() == GameState.getBoard()[i][j].getColor()) {
                    Vector<Location> moves = GameState.getPieceMovesUnderCheck(new Location(i,j));
                    if(moves.size()!= 0){
                        pieces.add(new Location(i, j));
                    }
                }
            }
        }

        if(pieces.size()==0){
            GameState.isCheckMate();
            return;
        }
        Random rand= new Random();
        int randLoc = rand.nextInt(pieces.size());
        Location oldLoc = pieces.get(randLoc);
        Vector<Location> moves = GameState.getPieceMovesUnderCheck(oldLoc);
        randLoc = rand.nextInt(moves.size());
        Location newLoc = moves.get(randLoc);
        GameState.updateState(oldLoc, newLoc);
    }

    /*
    public void initializePieces(){
        initializePawns();
        initializeRooks();
        initializeBishops();
        initializeKing();
        initializeKnights();
        initializeQueen();
    }

    public void initializePawns(){
        for(int i= 0; i < 8; i++){
            Pawn p = new Pawn(0);
            p.setCoords(1,i);
            addPiece(p);
        }
    }

    public void initializeQueen(){
        Queen q = new Queen(0);
        q.setCoords(0,3);
        addPiece(q);
    }

    public void initializeKing(){
        King k = new King(0);
        k.setCoords(0,4);
        addPiece(k);
    }

    public void initializeBishops(){
        Bishop b = new Bishop(0);
        b.setCoords(0,2);
        addPiece(b);
        b = new Bishop(0);
        b.setCoords(0,5);
        addPiece(b);
    }

    public void initializeKnights(){
        Knight k = new Knight(0);
        k.setCoords(0,1);
        addPiece(k);
        k = new Knight(0);
        k.setCoords(0,6);
        addPiece(k);
    }

    public void initializeRooks(){
        Rook r = new Rook(0);
        r.setCoords(0,0);
        addPiece(r);
        r = new Rook(0);
        r.setCoords(0,7);
        addPiece(r);
    }*/
}
