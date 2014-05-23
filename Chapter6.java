import java.util.Scanner;

public class Chapter6 {
/*******************************t63*************************/
	public static void t63(){
		System.out.println("Enter a list of numbers between "
				+ "1 to 100.End the list with number 0");
		 Scanner scn = new Scanner(System.in);
		int[] numList = new int[100];
		int[] timeList = new int[100];
		int temp;
		int numAccount = 0;
		
		for(int n=0;n<100;n++){
			temp = scn.nextInt();
			if (temp == 0){
				break;
			}
			else{
				numAccount = countNum(temp, 
									numList, 
									timeList, 
									numAccount);
			}
		}
		scn.close();
		numDisplay(numList, timeList);
	}
	
	public static int countNum(int inputNum, 
							   int[] numArray, 
							   int[] timeArray,
							   int arrayCount){
		boolean sign = false;
		for(int n=0;n<arrayCount;n++){
			if (inputNum == numArray[n]){
				timeArray[n]++;
				sign = true;
				break;
			}
		}
		if (arrayCount<numArray.length && !sign){
			numArray[arrayCount] = inputNum;
			timeArray[arrayCount]++;
			arrayCount++;
		}
		return arrayCount;
	}
	
	public static void numDisplay(int[] numArray, int[] timeArray){
		String timeStr;
		for(int n=0;n<numArray.length && numArray[n]!=0;n++){
			if (timeArray[n] > 1){
				timeStr = "times";
			}
			else{
				timeStr = "time";
			}
			System.out.println(numArray[n] + " occur " 
							   + timeArray[n] + " " + timeStr);
		}
	}
	
/*******************************t621*************************/
	//bean machine
	public static void t621(){
		System.out.print("Enter the slot numbers.");
		Scanner scn = new Scanner(System.in);
		int slotNum = scn.nextInt();
		System.out.print("\nEnter the ball numbers.");
		int ballNum = scn.nextInt();
		scn.close();
		beanMachine(slotNum, ballNum);
	}
	
	public static void beanMachine(int slotNum, int ballNum){
		int[] slots = new int[slotNum];
		int maxBall = 0;
		for(int n=0;n<ballNum;n++){
			int dropNum = 0;
			for(int s=0;s<slotNum-1;s++){
				dropNum = getNextSlotNum(dropNum);
			}
			try{
			slots[dropNum]++;
			} catch(ArrayIndexOutOfBoundsException e){
				System.out.println(dropNum);
			}
			if(maxBall<slots[dropNum]){
				maxBall = slots[dropNum];
			}
			System.out.print("\n");
		}
		displaySlots(slots, maxBall);
	}
	
	public static int getNextSlotNum(int lastDropNum){
		int dropSlotNum = (int)(Math.random() * 2);
		if(dropSlotNum==0)
			System.out.print("L");
		else
			System.out.print("R");
		return (dropSlotNum + lastDropNum);
	}
	
	public static void displaySlots(int[] slots, int maxBallInOneSlot){
		while(maxBallInOneSlot>0){
			for(int n=0;n<slots.length;n++){
				if(slots[n]==maxBallInOneSlot){
					System.out.print("O");
					slots[n]--;
					assert (slots[n]>=0);
				}
				else{
					System.out.print(" ");
				}
			}
			maxBallInOneSlot--;
			System.out.println("");
		}
	}
	
	public static void main(String[] args){
		long startTime=System.nanoTime();
		t621();
		long endTime=System.nanoTime();
		System.out.println("Process running time: " + (endTime-startTime) + "ns");
	}
}
