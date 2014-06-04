import java.util.Arrays;
import java.net.Socket;
import java.util.Scanner;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ChessGameNetworkVersion_Client {
    public static void client(){
        clientHandler ch = new clientHandler();
        ch.init();
        new Thread(ch).start();
    }
    
    public static void main(String[] args){
        client();
    }
}

class clientHandler implements Runnable{
    private final int EQUAL = 011;
    private final int FAIL = 012;
    private final int WIN = 013;
    private final int RETRY = 110;
    private final int STARTED_1 = 111;
    private final int STARTED_2 = 112;
    private final int EMPTY = 999;
    private final int INIT = 000;

    private final static int chessNum = 5;
    /* 
    3 chess game, AKA tick tack toe game.You can change to any number 
    that is less than verticalNum or horizontalNum.
     */

    private final static int verticalNum = 5;   
    // vertical length of chess table
    private final static int horizontalNum = 5; 
    // horizontal length of chess table
    private int[][] chessTable;
    // define a chess table
    
    private final static int PORT = 15140;
    private static String HOST = "localhost";
    private DataInputStream playerInput;
    private DataOutputStream playerOutput;
    

    
    public void init(){
        chessTable = new int[verticalNum][horizontalNum];
    }
    
    public void run(){
        int[] data = new int[3];
        data[0] = INIT;
        data[1] = EMPTY;
        data[2] = EMPTY;
        
        int player;
        Socket socket;
        
        try{
            socket = new Socket("192.168.1.161", PORT);
            socket.setTcpNoDelay(true);
            socket.setSendBufferSize(16384);
            socket.setReceiveBufferSize(16384);
            playerInput = new DataInputStream(socket.getInputStream());
            playerOutput= new DataOutputStream(socket.getOutputStream());
            System.arraycopy(this.inputData(playerInput), 0, data, 0, 3);
            if(data[0]==STARTED_1||data[0]==STARTED_2){
                player = data[0];
                System.out.println("You're player " + player +
                            (player == STARTED_1 ? " 1 use O" : " 2 use X"));
            }
            else{
                System.out.println("Something is wrong!");
                return;
            }
            
            
            do{
                System.arraycopy(this.inputData(playerInput), 0, data, 0, 3);
                if(this.isGameOver(data, player))
                    break;
                if(player == data[0]){
                    do{
                        this.sendMove(data, playerInput, playerOutput);
                    }while(data[0]==RETRY);
                }
                this.recvInfo(data, playerInput);
                this.printGame(chessTable);
            }while(data[0]==STARTED_1||data[0]==STARTED_2);
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendMove(int[] data, DataInputStream dinput, DataOutputStream dout){
        try{
            Scanner scn = new Scanner(System.in);
            scn.reset();
            System.out.println("Enter the row num between (1 to " + verticalNum + ")");
            dout.writeInt(scn.nextInt());
            System.out.println("Enter the column num between (1 to " + verticalNum + ")");
            dout.writeInt(scn.nextInt());
            scn = null;
            dout.flush();
            System.arraycopy(this.inputData(dinput), 0, data, 0, 3);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void recvInfo(int[] data, DataInputStream dinput){
        System.arraycopy(this.inputData(dinput), 0, data, 0, 3);
        this.setMovement(data[1], data[2], (data[0]==STARTED_1 ? 1 : 9));
    }
    
    public boolean isGameOver(int[] data, int player){
        switch(data[0]){
            case WIN :{
                if(player==STARTED_1)
                    System.out.println("You're win!!!");
                else
                    System.out.println("You're failed!!!");
                return true;
            }
            case FAIL :{
                if(player==STARTED_1)
                    System.out.println("You're failed!!!");
                else
                    System.out.println("You're win!!!");
                return true;
            }
            case EQUAL :{
                System.out.println("You're equal.");
                return true;
            }
            default :{
                return false;
            }
        }
    }
    
    public int[] inputData(DataInputStream dinput){
        int count = 0;
        int[] data = new int[3];
        try{
            while(count<3){
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
        return data;
    }
    
    public boolean isEmpty(int x, int y){
        if(x==EMPTY&&y==EMPTY)
            return true;
        else
            return false;
    }
    
    public void setMovement(int x, int y, int value){
        chessTable[x][y] = value;
    }
    
    public void printGame(int[][] input){
        byte[] b = new byte[horizontalNum * 2 + 1];
        Arrays.fill(b, (byte)'-');
        String line = new String(b) + "\n";
        for(int n=0;n<input.length;n++){
            String grid = line;
            for(int s:input[n]){
                if(s==0)
                    grid += "| ";
                if(s==9)
                    grid += "|X";
                if(s==1)
                    grid += "|O";
            }
            grid += "|\n";
            System.out.print(grid);
        }
        System.out.print(line);
    }
}
