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
	

	
	
	public static void main(String[] args){
		long startTime=System.nanoTime();
		t63();
		long endTime=System.nanoTime();
		System.out.println("Process running time: " + (endTime-startTime) + "ns");
	}
}
