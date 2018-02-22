package ramapo.edu.sminev.chess.Model;



public class Piece {
    private enum pieces {PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING};
    private int m_xposition;
    private int m_yposition;
    //Enumerate
    //Pawn = 0
    //Rook = 1
    //Knight = 2
    //Bishop = 3
    //Queen = 4
    //King = 5
    private int m_type;

    public Piece(){
        m_xposition = 0;
        m_yposition = 0;
        m_type = 0;
    }

    public Piece(int a_xposition, int a_yposition, int a_type){
        m_xposition = a_xposition;
        m_yposition = a_yposition;

    }

    public int getXpos() {
        return m_xposition;
    }

    public void setXpos(int a_xposition) {
        m_xposition = a_xposition;
    }

    public int getYpos() {
        return m_yposition;
    }

    public void setYpos(int a_yposition) {
        m_yposition = a_yposition;
    }

    public int getType() {
        return m_type;
    }

    public void setType(int a_type) {
        this.m_type = a_type;
    }
}
