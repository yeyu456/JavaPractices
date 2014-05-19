import javax.swing.JOptionPane;
public class Chapter2 {
	public static void t25(){
		double fee = Double.parseDouble(JOptionPane.showInputDialog(null, "Input the fee:"));
		double gratuity = Math.round(fee * 100 * 0.15) / 100.0;
		double total = gratuity + fee;
		System.out.println("fee " + fee + " gratuity " + gratuity + " total " + total);
	}
	
	public static void t26(){
		int num = Integer.parseInt(JOptionPane.showInputDialog("Enter a number between 1 to 1000"));
		int hungreds = num / 100;
		int tens = (num - hungreds * 100) / 10;
		int digits = num - hungreds * 100 - tens * 10;
		int total = hungreds + tens + digits;
		System.out.println(total);
	}
	
	public static void t27(){
		int minute = Integer.parseInt(JOptionPane.showInputDialog("Enter a number of minutes"));
		int years = minute / (365 * 24 * 60);
		int days = (minute - years * 365 * 24 * 60) / (60 * 24);
		System.out.println("years: " + years + " days: " + days);
	}
	
	public static void t28(){
		int word = Integer.parseInt(JOptionPane.showInputDialog("Enter an ASCII code: "));
		char code = (char)word;
		System.out.println("your ASCII charactor is:" + code);
	}
	
	public static void t215(){
		int monthlyPayment = 100;
		double rate = 0.05;
		double monthlyRate = rate / 12;
		double money = 0.0;
		for(int n=0;n<6;n++){
			 money = Math.round((monthlyPayment + money) * (1+ monthlyRate) * 1000) / 1000.0;  
		}
		System.out.println("Your money after 6 months is :" + money);
	}
	
	public static void main(String[] args){
		t215();
	}
}
