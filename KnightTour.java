import java.util.Arrays;

public class KnightTour{
    public static void main(String[] args){
        Kst kt1 = new Kst();
        long startTime=System.currentTimeMillis();
        if(kt1.move(1, 0, 0))
            kt1.drawTable();
        long endTime=System.currentTimeMillis();
        System.out.println("Process running time: " 
                   + (endTime-startTime) + "ms");
    }
}

class Kst {
    private final int[][] vectors = {
        {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
        {1, -2}, {2, -1}, {2, 1},{1, 2}
    }; //vector to get position of eight directions
    private int Count = 0;
    private int tableWidth1 = 5;
    private int tableWidth2 = 4;
    private int len = tableWidth1 * tableWidth2 + 1;
    private int[][] chessTable = new int[tableWidth1][tableWidth2];
    
    public boolean canMove(int x, int y){
        if((x>=0) 
            && (x<tableWidth1) 
            && (y>=0) 
            && (y<tableWidth2) 
            && (chessTable[x][y]==0))
                return true;
        else
            return false;
    }
    
    public boolean move(int Count, int x, int y){
        if(Count==len)
            return true;
        else{
            if(canMove(x, y)){
                chessTable[x][y] = Count;
                for(int n=0;n<8;n++){
                    if(move(Count+1, x+vectors[n][0], y+vectors[n][1]))
                        return true;
                }
                chessTable[x][y] = 0;
                return false;
            }
            return false;
        }
    }

    public void drawTable(){
        byte[] b = new byte[tableWidth2 * 3 + 1];
        Arrays.fill(b, (byte)'-');
        String line = new String(b);
        for(int n=0;n<chessTable.length;n++){
            System.out.println(line);
            for(int s:chessTable[n]){
                if(s<10)
                    if(s==0)
                        System.out.print("|--");
                    else
                        System.out.printf("|0%d", s);
                else
                    System.out.printf("|%d", s);
            }
            System.out.print("|\n");
        }
        System.out.println(line);
    }
}
