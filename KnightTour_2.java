import java.util.Arrays;

public class KnightTour_2{
    public static void main(String[] args){
        int base1 = 8; //set length of chess table row to 8
        int base2 = 8; //set length of chess table column to 8
        Kst kt1 = new Kst(base1+4, base2+4); 
        long startTime=System.currentTimeMillis();
        int row = 2 + (int) (Math.random() * (base1 - 4));
        int col = 2 + (int) (Math.random() * (base2 - 4));
        if(kt1.move(1, row, col))
            kt1.drawTable();
        else
            System.out.println("no result");
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
    private int tableWidth1;
    private int tableWidth2;
    private int len;
    private int[][] chessTable;

    public Kst(int row, int column){
        tableWidth1 = row;
        tableWidth2 = column;
        len = (tableWidth1 - 4) * (tableWidth2 - 4);
        chessTable = new int[tableWidth1][tableWidth2];
        for (int r = 0; r < tableWidth1; r++) //set the value of position that out of chess table's range to -1 
            for (int c = 0; c < tableWidth2; c++)
                if (r < 2 || r > tableWidth1 - 3 || c < 2 || c > tableWidth2 - 3)
                    chessTable[r][c] = -1;
    }
    
    public boolean canMove(int x, int y){
        if(chessTable[x][y]==0)
            return true;
        else
            return false;
    }
    
    public boolean move(int Count, int x, int y){
        if(Count>len-1&&canMove(x, y)){
            chessTable[x][y] = Count;
            return true;
        }
        
        int[][] neib = this.getNeighbour(x, y);
        if(neib.length==0&&Count!=len)
            return false;
        
        for(int[] n:neib){
            chessTable[x][y] = Count;
            if(canMove(n[0], n[1]) && move(Count+1, n[0], n[1]))
                return true;
            else
                chessTable[x][y] = 0;
        }
        return false;
    }
    
    public int[][] getNeighbour(int x, int y){
        int[][] neighbor = new int[8][2];
        int[] cNum = new int[8];
        int count = 0;
        for(int n=0;n<8;n++){
            if(canMove(x+vectors[n][0], y+vectors[n][1])){
                neighbor[count][0] = x+vectors[n][0];
                neighbor[count][1] = y+vectors[n][1];
                cNum[count] = countNeighbour(neighbor[count][0], neighbor[count][1]);
                count++;
            }
        }
        int[][] nei = new int[count][2];
        System.arraycopy(neighbor, 0, nei, 0, count);
        neighbor = null;
        if(nei.length>1)
            nei = sortNeighbour(nei, cNum);
        return nei;
    }
    
    public int countNeighbour(int x, int y){
        int c = 0;
        for(int n=0;n<vectors.length;n++){
            if(canMove(x+vectors[n][0], y+vectors[n][1]))
                c++;
        }
        return c;
    }
    
    public int[][] sortNeighbour(int[][] num, int[] c){
        int[][]tmp = new int[num.length][2];
        int count = 0;
        int min = c[0];
        int index = 0;
        while(count<num.length){
            for(int s=0;s<num.length;s++){
                if(c[s]<min){
                    min = c[s];
                    index = s;
                }
            }
            c[index] = 10;
            min = 10;
            tmp[count] = num[index];
            count++;
        }
        return tmp;
    }

    public void drawTable(){
        byte[] b = new byte[(tableWidth2 - 4) * 3 + 1];
        Arrays.fill(b, (byte)'-');
        String line = new String(b);
        for(int n=2;n<chessTable.length-2;n++){
            System.out.println(line);
            for(int s:chessTable[n]){
                if(s<10){
                    if(s==0)
                        System.out.print("|--");
                    if(s>0)
                        System.out.printf("|0%d", s);
                }
                else
                    System.out.printf("|%d", s);
            }
            System.out.print("|\n");
        }
        System.out.println(line);
    }
}
