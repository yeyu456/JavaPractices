public class EightQueenPuzzle{
/**********************t620**********************************/
	public static void t620(){
		//Eight Queens Puzzle
		int[] pos = new int[8];
		int[] p = new int[8];
		int[] m = new int[8];
		calPos(0, pos, p, m, 2);
	}
	
	public static void calPos(int index, int[] pos, int[] p, int[] m, int resultNum){
		int result=0; //resultNum, how much result you want to print
		int[] status = new int[8]; //store the column number when next time to search on every row
		int columNum = 0; //indicate the current column number
		while(index<8){ //index, which indicate the current row number
			if(columNum==8){ 
				status[index] = 0;
				index--;
			}
			columNum = status[index];
			if(status[index]==8){
				status[index] = 0;
				index--;
				columNum = 0;
				continue;
			}
			while(columNum<8){
				pos[index] = columNum;
				p[index] = index + columNum;
				m[index] = index - columNum;
				if(!canAttackOrNot(index, pos, p, m)){
					status[index] = columNum + 1;
					index++;
					break;
				}
				else
					columNum++;
			}
			
			if(index==8){
				for(int n:pos){
		        	  System.out.print(n + " ");
		          }
		          System.out.println(" ");
		        displayPos(pos);
				result++;
				index--;
				if(result==resultNum||result==92)
					break;
			}
		}
	}
	
	public static boolean canAttackOrNot(int index, int[] pos, int[] p, int[] m){
		for(int n=0;n<index;n++){
			if ((pos[index]==pos[n]) || (m[index]==m[n]) || (p[index]==p[n]))
				return true;
		}
		return false;
	}
	
	public static void displayPos(int[] p){
		for(int n=0;n<p.length;n++){
			for(int s=0;s<8;s++){
				if(p[n]==s){
					System.out.print("|Q");
				}
				else{
					System.out.print("| ");
				}
			}
			System.out.println("|");
		}
		
	}
	
	
	public static void main(String[] args){
		long startTime=System.nanoTime();
		t620();
		long endTime=System.nanoTime();
		System.out.println("Process running time: " + (endTime-startTime) + "ns");
	}
}