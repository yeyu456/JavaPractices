import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Chapter9_2 {
	public static void modifyFile(String ff) throws IOException{
		System.out.println(new File("").getAbsolutePath());
		File f = new File(ff);
		if(!f.exists())
			return;
		File fbak = new File(ff + ".bak");
		if(!fbak.exists())
			fbak.createNewFile();
		FileWriter fw = new FileWriter(ff + ".bak");
		
		String[] swap = new String[2];
		int swap_pos = 0;
		Scanner scn = new Scanner(f);
		while(scn.hasNextLine()){
			swap[swap_pos]=scn.nextLine();
			if(swap_pos==1){
				if(swap[1].matches("^\\s*\\{")){
					swap[0] = swap[0] + " {";
					swap[1] = swap[1].replaceFirst("\\{", "");
				}
				if(!swap[0].matches("^\\s*$"))
					fw.write(swap[0] + "\r\n");
				fw.flush();
				swap[0] = swap[1];
				swap[1] = "";
			}
			swap_pos = 1;
		}
		fw.write(swap[0]);
		fw.flush();
		fw.close();
		scn.close();
	}
	
	public static void main(String[] args) throws IOException{
		for(String n:args)
			modifyFile(n);
	}
}
