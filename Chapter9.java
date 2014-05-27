import java.util.Scanner;

public class Chapter9 {
	public static void t92(){
		Scanner scn = new Scanner(System.in);
		String one = scn.next();
		String two = scn.next();
		scn.close();
		
		int count = 0;
		int count2 = two.indexOf(one.charAt(0));
		if(count2 == -1){
			System.out.printf("%s isn't %s's substring\n", one, two);
			return;
		}
		while(count2<two.length()&&count<one.length()){
			if(one.charAt(count)!=two.charAt(count2)){
				count = 0;
			}
			else
				count++;
			count2++;	
			if(count==one.length()){
				System.out.printf("%s is %s's substring\n", one, two);
				return;
			}
		}
		System.out.printf("%s isn't %s's substring\n", one, two);
	}
	
	public static void t93(){
		Scanner scn = new Scanner(System.in);
		String passwd = scn.next();
		scn.close();
		Password pw = new Password(passwd);
		int l = pw.getLetterNum();
		int d = pw.getDigitNum();
		int n = pw.getNoneNum();
		if((d>=2)&&(n==0)&&(d+l>=8))
			System.out.println("Valid Password.");
		else
			System.out.println("Invalid Password.");
	}
	
	public static void main(String[] args){
		long startTime=System.nanoTime();
		t93();
		long endTime=System.nanoTime();
		System.out.println("Process running time: " + (endTime-startTime) + "ns");
	}
}


class Password{
	//for t93
	private String pwd;
	private int letterNum = 0;
	private int digitNum = 0;
	private int noneNum = 0;
	
	public Password(String passwd){
		pwd = passwd;
		int indexOfPass = 0;
		while((indexOfPass<pwd.length())){
			char temp = pwd.charAt(indexOfPass);
			if(Character.isLetter(temp))
				letterNum++;
			else{
				if(Character.isDigit(temp))
					digitNum++;
				else
					noneNum++;
			}
			indexOfPass++;
		}
	}
	
	public int getLetterNum(){
		return letterNum;
	}
	
	public int getDigitNum(){
		return digitNum;
	}
	
	public int getNoneNum(){
		return noneNum;
	}
}
