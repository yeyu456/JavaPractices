import java.util.Scanner;
import javax.swing.JOptionPane;

public class Chapter4 {
	public static void t47(){
		int initPayment = 10000;
		double fourYearsPayment = initPayment;
		double tenYearsLaterPayment = initPayment * Math.pow((1 + 0.05), 10);
		for(int n=0;n<4;n++){
			fourYearsPayment += fourYearsPayment * Math.pow((1 + 0.05), n);
		}
		tenYearsLaterPayment = Math.round(tenYearsLaterPayment * 100) / 100.0;
		fourYearsPayment = Math.round(fourYearsPayment * 100) / 100.0;
		
		System.out.println("After ten years the payment will be : " 
				+ tenYearsLaterPayment 
				+ " And the payment of four years is : "
				+ fourYearsPayment);
	}
	
	public static void t416(){
		Scanner scn = new Scanner(System.in);
		long num = scn.nextLong();
		long initNum = 2;
		long midNum;
		System.out.print(num + "=");
		if (num == 1){
			System.out.printf("1");
		}
		else{
			while(num!=1){
				midNum = num % initNum;
				if (midNum == 0){
					System.out.printf(" %d *", initNum);
					num = num / initNum;
					initNum = 2;
				}
				else{
					initNum += 1;
				}
				double breakPoint = Math.sqrt(num);
				if (initNum >= breakPoint){
					System.out.printf(" %d ", initNum);
					break;
				}
			}
		}
	}
	
	public static void t417(){
		int rowNum = Integer.parseInt(JOptionPane.showInputDialog(
				"Enter the number of lines between 1 ~ 15:"));
		int columNum = rowNum * 2 - 1;
		for(int n=0;n<rowNum;n++){
			int printNum = n+1;
			for(int s=0;s < columNum;s++){
				if(s < (rowNum - n - 1) || s > (columNum / 2 + n )){
					System.out.print(" ");
				}
				else{
					System.out.print(printNum);
					printNum = (s < columNum / 2)?(printNum-1):(printNum+1);
				}
			}
			System.out.print("\n");
		}
	}
	
	public static void t419(){
		int rowNum = 8;
		int columNum = rowNum * 2 - 1;
		for(int n=0;n<rowNum;n++){
			int printNum = 1;
			for(int s=0;s < columNum;s++){
				if(s < (rowNum - n - 1) || s > (columNum / 2 + n )){
					System.out.print("    ");
				}
				else{
					System.out.print(printNum + " ");
					printNum = (s < columNum / 2)?(printNum * 2):(printNum / 2);
					if (printNum < 10)
						System.out.print("  ");
					else{
						if ((printNum >= 10) && (printNum <= 100))
							System.out.print(" ");
					}
				}
			}
			System.out.print("\n");
		}
	}
	
	public static void t434(){
		//rock-paper-scissors
		int winNum = 0;
		while(Math.abs(winNum) <2){
			int computerCall = (int)(Math.random() * 3);
			System.out.print(computerCall);
			int userCall = Integer.parseInt(JOptionPane.
							showInputDialog("rock?(0) paper?(1) scissors?(2)"));
			System.out.println(" " + userCall);
			int result = computerCall - userCall;
			
			if(result == -1 || result ==2){
				winNum = (winNum>=0) ? (winNum + 1) : 1;
				System.out.println("win" + winNum);
			}
			else{
				if (result == 1 || result == -2){
					winNum = (winNum<=0) ? (winNum - 1) : -1;
					System.out.println("lose" + winNum);
					}
				else{
					winNum = 0;
					System.out.println("equal" + winNum);
				}
			}
		}
	}
	
	public static void main(String[] args){
		long startTime=System.nanoTime();
		t434();
		long endTime=System.nanoTime();
		System.out.println("程序运行时间： " + (endTime-startTime) + "ns");
	}
}
