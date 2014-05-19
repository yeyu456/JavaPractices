import java.util.Scanner;
import javax.swing.JOptionPane;

public class Chapter3 {
	public static void switchtest(){
		Scanner scannum = new Scanner(System.in);
		int num = scannum.nextInt();
		switch(num){
			case 1: 
				System.out.println('1');
				break;
			case 2: 
				System.out.println('2');
				System.out.println('3');
				break;
			default: 
				System.out.println("Wrong!");
				break;
		}
	}
	
	public static void t34(){
		int num1 = (int)Math.round(Math.random() * 100);
		int num2 = (int)Math.round(Math.random() * 100);
		int sum = Integer.parseInt(JOptionPane.showInputDialog("The sum of " + num1 + " " + num2));
		if (num1 + num2 == sum){
			JOptionPane.showMessageDialog(null, "true");
		}
		else{
			JOptionPane.showMessageDialog(null, "false");
		}
	}
	
	public static void t321(){
		Scanner scn = new Scanner(System.in);
		int year = scn.nextInt();
		int m = scn.nextInt();
		int q = scn.nextInt();
		int j = year / 100;
		int k = year % 100;
		int dayOfWeek = (q + (26 * (m + 1)) / 10 + k + k / 4 + j / 4 + 5 * j) % 7;
		System.out.print("day of week is ");
		switch(dayOfWeek){
			case 2 :
				System.out.println("Monday.");
				break;
			case 3 :
				System.out.println("Tuesday.");
				break;
			case 4 :
				System.out.println("Wednesday.");
				break;
			case 5 :
				System.out.println("Thursday.");
				break;
			case 6 :
				System.out.println("Friday.");
				break;
			case 0 :
				System.out.println("Saturday.");
				break;
			case 1 :
				System.out.println("Sunday.");
				break;
			}
	}
	
	public static void t322(){
		Scanner scn = new Scanner(System.in);
		double x = scn.nextDouble();
		double y = scn.nextDouble();
		double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(x, 2));
		if (dist <= 10){
			System.out.printf("point(%f, %f) is in the circle.", x, y);
		}
		else{
			System.out.printf("point(%f, %f) is out of the circle.", x, y);
		}
	}
	
	public static void t323(){
		Scanner scn = new Scanner(System.in);
		double x = scn.nextDouble();
		double y = scn.nextDouble();
		if (Math.abs(x) <= 5 && Math.abs(y) <= 2.5){
			System.out.printf("point(%f, %f) is in the retangle.", x, y);
		}
		else{
			System.out.printf("point(%f, %f) is out of retangle.", x, y);
		}
	}
	
	public static void t324(){
		int cardDigit = (int)(Math.random() * 13) + 1;
		int color = (int)(Math.random() * 4) + 1; 
		System.out.print("Your car is ");
		switch(cardDigit){
		case 1 :
			System.out.print("Ace of ");
			break;
		case 11 :
			System.out.print("Jack of ");
			break;		
		case 12 :
			System.out.print("Queen of ");
			break;
		case 13 :
			System.out.print("King of ");
			break;
		default :
			System.out.print(cardDigit + " of ");
		}
		
		switch(color){
		case 1 :
			System.out.print("Clues.");
			break;
		case 2 :
			System.out.print("Diamond.");
			break;
		case 3 :
			System.out.print("Heart.");
			break;
		case 4 :
			System.out.print("Spades.");
			break;
		}
		
	}
	
	public static void t325(){
		Scanner scn = new Scanner(System.in);
		double x = scn.nextDouble();
		double y = scn.nextDouble();
		double z = scn.nextDouble();
		if ((x + y > z) && (x + z > y) && (y + z > x)){
			System.out.println("It's  a triangle.");
		}
		else{
			System.out.println("It isn't  a triangle.");
		}
	}
	
	public static void main(String[] args){
		t325();
	}
}
