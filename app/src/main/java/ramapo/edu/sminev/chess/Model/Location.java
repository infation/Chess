package ramapo.edu.sminev.chess.Model;


import android.provider.Settings;

public class Location {
    public final int row;
    public final int col;
    public Location(int a_row, int a_col){
        row = a_row;
        col = a_col;
    }

    public static int convertToId(Location loc){
        return loc.row*8+loc.col;
    }

    public static Location parseId(int id){
        return new Location(id/8, id%8);
    }

    public static void toString(Location a_loc){
        System.out.println("Row: " + a_loc.row);
        System.out.println("Column: " + a_loc.col);
    }

}
