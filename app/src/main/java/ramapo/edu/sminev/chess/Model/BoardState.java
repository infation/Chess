package ramapo.edu.sminev.chess.Model;


public class BoardState {

    private Piece board[][];

    public BoardState(){
        board = new Piece[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new Piece();
            }
        }
        initializeBoard();
    }

    public void initializeBoard(){
        initializePawns();
        initializeRooks();
        initializeBishops();
        initializeKings();
        initializeKnights();
        initializeQueens();
    }

    public void initializePawns(){
        for(int i= 0; i < 8; i++){
            board[1][i] = new Pawn(0);
            board[6][i] = new Pawn(1);
        }

    }

    public void initializeQueens(){
        board[0][3] = new Queen(0);
        board[7][3] = new Queen(1);
    }

    public void initializeKings(){
        board[0][4] = new King(0);
        board[7][4] = new King(1);
    }

    public void initializeBishops(){
        board[0][2] = new Bishop(0);
        board[0][5] = new Bishop(0);
        board[7][2] = new Bishop(1);
        board[7][5] = new Bishop(1);
    }

    public void initializeKnights(){
        board[0][1] = new Knight(0);
        board[0][6] = new Knight(0);
        board[7][1] = new Knight(1);
        board[7][6] = new Knight(1);
    }

    public void initializeRooks(){
        board[0][0] = new Rook(0);
        board[0][7] = new Rook(0);
        board[7][0] = new Rook(1);
        board[7][7] = new Rook(1);
    }

    public void updateState(int oldPos, int newPos){
        int oldY = oldPos/8;
        int oldX = oldPos%8;
        int newY = newPos/8;
        int newX = newPos%8;
        System.out.println("Y: "+ oldY+"X: "+oldX+"Y: "+newY+ "X: "+ newX);
        board[newY][newX] = board[oldY][oldX];
        board[oldY][oldX] = new Piece();
    }


    public Piece getSquare(int x, int y){
        return board[x][y];
    }

}
