import java.util.Arrays;
import java.util.Scanner;

public class ChessGame {
/***********************t79******************************/
	
public static final int[][] vectors ={
		{-1, -1},
		{-1, 0},
		{-1, 1},
		{0, -1},
		{0, 1},
		{1, -1},
		{1, 0},
		{1, 1}
}; //vector to get position of eight directions 

final static int chessNum = 3;
/* 
3 chess game, AKA tick tack toe game.You can change to any number 
that is less than verticalNum or horizontalNum.
*/
 		
final static int verticalNum = 3;   // vertical length of chess table
final static int horizontalNum = 3; // horizontal length of chess table

	public static void t79(){
		int[][] chessTable = new int[verticalNum][horizontalNum]; // define a chess table
		int stepNum = 0;
		assert (chessNum <= verticalNum) || (chessNum <= horizontalNum);
		do{
			playGame(chessTable, stepNum%2);
			printGame(chessTable);
			stepNum++;
			if(stepNum == verticalNum * horizontalNum){
				System.out.println("You are equal.");
				break;
			}
		}while(!winOrNot(chessTable, chessNum));
	}
	
	public static void playGame(int[][] input, int player){
		while(true){
			Scanner scn = new Scanner(System.in);
			System.out.printf("Player %d, please input the row number(1-%d)\n", 
							   player+1, verticalNum);
			int rowNum = scn.nextInt();
			System.out.printf("Player %d, please input the colum number(1-%d)\n", 
							   player+1, horizontalNum);
			int columnNum = scn.nextInt();
			if(wrongStep(input, rowNum, columnNum)){
				continue;
			}
			if(player==0)
				input[rowNum-1][columnNum-1] = 1; //when player1 play, set the chess number 1
			else
				input[rowNum-1][columnNum-1] = 9; //when player2 play, set the chess number 9
			break;
		}
	}
	
	public static void printGame(int[][] input){
		byte[] b = new byte[horizontalNum * 2 + 1];
		Arrays.fill(b, (byte)'-');
		String line = new String(b);
		for(int n=0;n<input.length;n++){
			System.out.println(line);
			for(int s:input[n]){
				if(s==0)
					System.out.print("| ");
				if(s==9)
					System.out.print("|X");
				if(s==1)
					System.out.print("|O");
			}
			System.out.print("|\n");
		}
		System.out.println(line);
	}
	
	public static boolean wrongStep(int input[][], int rowNum, int columnNum){
		if(rowNum>verticalNum || rowNum<=0){
			System.out.println("Out of chess table range!");
			return true;
		}
		if(columnNum>horizontalNum || columnNum<=0){
			System.out.println("Out of chess table range!");
			return true;
		}
		if(input[rowNum-1][columnNum-1]!=0){
			System.out.println("Wrong step, the position had been set!");
			return true;
		}
		return false;
	}
	

	public static boolean winOrNot(int[][] input, int markNum){
		for(int n=0;n<input.length;n++){
			for(int s=0;s<input[n].length;s++){
				if(getSum(input, n, s, markNum))
					return true;
			}
		}
		return false;
	}
	
	public static boolean getSum(int[][] input, int x, int y, int markNum){
		//check if one of 8 directions of positions in row x,column y win
		for(int[] n:vectors){
			int x_pos = 0;
			int y_pos = 0;
			int sum = input[x][y];
			for(int s=1;s<markNum;s++){ 
				x_pos = x + s * n[0];   //get the row index with n[0] vector
				y_pos = y + s * n[1];   //get the column index with n[1] vector
				if(x_pos>=input.length || x_pos <0){ //if out of array index range
					sum = 0;
					break;
				}
				if(y_pos>=input[x_pos].length || y_pos <0){ //if out of array index range
					sum = 0;
					break;
				}
				else
					sum += input[x_pos][y_pos];
			}
			if(sum==markNum){
				System.out.println("Player 1 Win!");
				return true;
			}
			if(sum==(markNum * 9)){
				System.out.println("Player 2 Win!");
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		long startTime=System.nanoTime();
		t79();
		long endTime=System.nanoTime();
		System.out.println("Process running time: " 
						   + (endTime-startTime) + "ns");
	}
}
