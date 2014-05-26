import javax.swing.JOptionPane;

public class Chapter7 {
	public static void t717(){
		int bankNum = 5;
		int limit = 201;
		double[][] borrowers = new double [bankNum][bankNum];
		for(int n=0;n<borrowers.length;n++){
			String bankstr = JOptionPane.showInputDialog("Enter bank capitcal");
			String[] temp = bankstr.split(" ");
			borrowers[n][n] = Integer.parseInt(temp[0]);
			bankNum = Integer.parseInt(temp[1]);
			for(int s=2;s<temp.length;s=s+2){
				int id = Integer.parseInt(temp[s]);
				double money = Double.parseDouble(temp[s+1]);
				borrowers[n][id] += money;
				borrowers[id][n] -= money;
			}
		}
		boolean notSafe = true;
		System.out.print("Unsafe bank is ");
		while(notSafe){
			notSafe = isSafeOrNot(borrowers, limit);
		}
	}
	
	public static boolean isSafeOrNot(double[][] b, int limit){
		for(int n=0;n<b.length;n++){
			double sum = 0.0;
			for(int s=0;s<b[n].length;s++){
				if(b[n][s]>0.0)
					sum += b[n][s];
			}
			if(sum<limit){
				for(int i=0;i<b.length;i++){
					b[i][n] = 0.0;
				}
				System.out.print(" " + n + " ");
				b[n][n] = limit + 1;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		long startTime=System.nanoTime();
		t717();
		long endTime=System.nanoTime();
		System.out.println("Process running time: " + (endTime-startTime) + "ns");
	}
}
