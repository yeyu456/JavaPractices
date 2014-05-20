import javax.swing.JOptionPane;

public class Chapter5 {
/********************************t5.3***************************************/
	public static void t53(){
		int testNum = Integer.parseInt(JOptionPane.showInputDialog("Enter a number."));
		if (isPalindrome(testNum))
			System.out.println("The number is palindrome.");
		else
			System.out.println("The number isn't palindrome.");
	}
	
	public static int reverse(int number){
		String numStr = String.valueOf(number);
		String newStr = "";
		int len = numStr.length();
		for(int n = len-1;n>=0;n--){
			newStr += numStr.charAt(n);
		}
		return Integer.parseInt(newStr);
	}
	
	public static boolean isPalindrome(int number){
		if (reverse(number) == number)
			return true;
		else
			return false;
	}
	
/*****************************t5.33******************************************/	
	public static void t533(){
		System.out.println(getDateAndTime(System.currentTimeMillis() + 8 * 60 * 60 * 1000));
	}
	
	public static String getDateAndTime(long time){
		long timeOfADay = 24 * 60 * 60 * 1000;
		String timeStr = getTime(time % timeOfADay / 1000);
		String dateStr = getDate(time / timeOfADay);
		return ("Current date and time is " + dateStr + " " + timeStr);
	}
	
	public static String getTime(long time){
		long hour = time / (60 * 60);
		long min = time % (60 * 60) / 60;
		long sec = time % (60 * 60) % 60; 
		return (hour + ":" + min + ":" + sec);
	}
	
	public static String getDate(long day){
		long yearsCount = day / 365;
		long daysLeft = day % 365;
		boolean isLeap;
		
		long daysAddBack = (yearsCount + 1970) / 400; //calculate how many 400 years which is not a leap year
		daysAddBack = (daysAddBack>4) ? (daysAddBack - 4) : 0; //add the numbers of 400 year before 1970, which is 4.
		long daysDelBack = (yearsCount - 2) / 4; //calculate the leap year number between now and 1972 that is first leap year after 1970.
		daysLeft += daysAddBack - daysDelBack; 
		yearsCount += daysLeft / 365; //make sure the add back action won't let the daysLeft over 365.
		daysLeft = daysLeft % 365; //as above.
		
		long year = yearsCount + 1970;
		if ((year % 4 == 0) && (year % 400 != 0))
			isLeap = true;
		else
			isLeap = false;
		String monthAndDay = getMonthAndDay(daysLeft, isLeap);
		return (monthAndDay + " " + year);
	}
	
	public static String getMonthAndDay(long day, boolean isLeap){
		int daysCount[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		final String monthsCount[] = {"January", "February", "March", "April",
            "May", "June", "July", "Auguest", "September", 
            "October", "November", "December"};
		if(isLeap)
			daysCount[1] = 29;
		int count = daysCount[0];
		int monthNum = 1;
		while(day>count){
			count += daysCount[monthNum];
			monthNum++;
		}
		String month = monthsCount[monthNum - 1];
		return (month + " " + (day - count + daysCount[monthNum - 2]));
	}
	
/****************************main function*******************************************/	
	public static void main(String[] args){
		t533();
	}
}
