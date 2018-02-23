package ramapo.edu.sminev.chess.Model;

/**
 * Created by sminev on 2/22/18.
 */

public class Computer extends Player {

    public Computer(){
        super();
        //initializePieces();
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
