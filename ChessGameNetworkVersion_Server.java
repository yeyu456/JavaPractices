import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ChessGameNetworkVersion_Server {
    private final static int PORT = 15140;

    public static void server(){
        try{
            ServerSocket ss = new ServerSocket(PORT);
        //while(true){
                Socket p1 = ss.accept();
                Socket p2 = ss.accept();
                serverHandler sh = new serverHandler(p1, p2);
                new Thread(sh).start();
                ss.close();
            //}
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        server();
    }
}

class serverHandler implements Runnable{
    private final int EQUAL = 011;
    private final int FAIL = 012;
    private final int WIN = 013;
    private final int RETRY = 110;
    private final int STARTED_1 = 111;
    private final int STARTED_2 = 112;
    private final int EMPTY = 999;
    private final int INIT = 000;

    //initial status
    
    private final static int[][] vectors ={
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
            {0, 1}, {1, -1}, {1, 0}, {1, 1}
    }; //vector to get position of eight directions 

    private final static int chessNum = 5;
    /* 
    3 chess game, AKA tick tack toe game.You can change to any number 
    that is less than verticalNum or horizontalNum.
    */

    private final static int verticalNum = 5;   
    // vertical length of chess table
    private final static int horizontalNum = 5; 
    // horizontal length of chess table
    
    private Socket player1;
    private Socket player2;
    
    private int[][] chessTable;
    // define a chess table
    
    private DataInputStream inputForPlayer1; 
    private DataInputStream inputForPlayer2;
    private DataOutputStream outputForPlayer1;
    private DataOutputStream outputForPlayer2;

    public serverHandler(Socket player1, Socket player2){
            this.player1 = player1;
            this.player2 = player2;
            this.chessTable = new int[verticalNum][horizontalNum];
        }
        
    public void run(){
        try{
            this.inputForPlayer1 = new DataInputStream(this.player1.getInputStream());
            this.inputForPlayer2 = new DataInputStream(this.player2.getInputStream());
            this.outputForPlayer1 = new DataOutputStream(this.player1.getOutputStream());
            this.outputForPlayer2 = new DataOutputStream(this.player2.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
            
        int stepNum = 0;
        this.outputInt(STARTED_1, this.outputForPlayer1); //tell player1 that he is player 1
        this.outputInt(STARTED_2, this.outputForPlayer2); //tell player2 that he is player 2
        do{ 
            if(stepNum%2 == 0){
                this.playGame(this.chessTable, STARTED_1, this.inputForPlayer1, this.outputForPlayer1);
            }
            else{
                this.playGame(this.chessTable, STARTED_2, this.inputForPlayer2, this.outputForPlayer2);
            }
            stepNum++;
        }while(!winOrNot(this.chessTable, chessNum, stepNum));
    }

    public void playGame(int[][] input, int player, DataInputStream dinput, DataOutputStream dout){
        this.outputInt(player, this.outputForPlayer1);
        this.outputInt(player, this.outputForPlayer2);
        //tell player that the who can step the chess
        while(true){
            int[] rowAndColumn = new int[2];
            this.inputInt(rowAndColumn, dinput);
            int rowNum = rowAndColumn[0];
            int columnNum = rowAndColumn[1];

            if(this.wrongStep(input, rowNum, columnNum)){
                this.outputInt(RETRY, dout);
                //should enter again.
                continue;
            }
            else
                this.outputInt(player, dout);
            if(player==STARTED_1)
                input[rowNum-1][columnNum-1] = 1;
                //when player1 play, set the chess number 1
                
            else
                input[rowNum-1][columnNum-1] = 9; 
                //when player2 play, set the chess number 9
                
            this.outputInt(player, rowNum-1, columnNum-1, this.outputForPlayer1);
            this.outputInt(player, rowNum-1, columnNum-1, this.outputForPlayer2);
            break;
        }
    }

    public boolean wrongStep(int input[][], 
        int rowNum, int columnNum){
        if(rowNum>verticalNum 
           || rowNum<=0 
           || columnNum>horizontalNum 
           || columnNum<=0
           || input[rowNum-1][columnNum-1]!=0)
            return true;
        else
            return false;
    }
    
    public void inputInt(int[] data, DataInputStream dinput){
        int count = 0;
        try{
            while(count<2){
                if(dinput.available()==0){
                    try{
                        TimeUnit.SECONDS.sleep(1); 
                        //sleep 1 second while there is no data
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                else{
                    data[count] = dinput.readInt();
                    count++;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void outputInt(int status, DataOutputStream dout){
        try{
            dout.writeInt(status);
            dout.flush();
            dout.writeInt(EMPTY); //while no data,write empty data
            dout.flush();
            dout.writeInt(EMPTY); //same as above
            dout.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void outputInt(int status, int pos_x, int pos_y, DataOutputStream dout){
        try{
            dout.writeInt(status);
            dout.flush();
            dout.writeInt(pos_x);
            dout.flush();
            dout.writeInt(pos_y);
            dout.flush();
        }catch(IOException e){
            e.printStackTrace();
        }    
    }

    public boolean winOrNot(int[][] input, int markNum, int stepNum){
        if(stepNum == verticalNum * horizontalNum){
            this.outputInt(EQUAL, this.outputForPlayer1);
            this.outputInt(EQUAL, this.outputForPlayer2);
            try{
                TimeUnit.MILLISECONDS.sleep(500);  
                //sleep 1 second while there is no data
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return true;
        }
        for(int n=0;n<input.length;n++){
            for(int s=0;s<input[n].length;s++){
                if(getSum(input, n, s, markNum)){
                try{
                    TimeUnit.MILLISECONDS.sleep(500); 
                    //sleep 1 second while there is no data
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                    return true;}
            }
        }
        return false;
    }

    public boolean getSum(int[][] input, int x, int y, int markNum){
    //check if one of 8 directions of positions in row x,column y win
        for(int[] n:vectors){
            int x_pos = 0;
            int y_pos = 0;
            int sum = input[x][y];
            for(int s=1;s<markNum;s++){ 
                x_pos = x + s * n[0];   //get the row index with n[0] vector
                y_pos = y + s * n[1];   //get the column index with n[1] vector
                if(x_pos>=input.length 
                        || x_pos <0
                        || y_pos>=input[x_pos].length 
                        || y_pos <0){ //if out of array index range
                    sum = 0;
                    break;
                }
                else
                    sum += input[x_pos][y_pos];
            }   
            if(sum==markNum){
                this.outputInt(WIN, this.outputForPlayer1);
                this.outputInt(WIN, this.outputForPlayer2);
                return true;
            }
            if(sum==(markNum * 9)){
                this.outputInt(FAIL, this.outputForPlayer1);
                this.outputInt(FAIL, this.outputForPlayer2);
                return true;
            }
        }
        return false;
    }
}
