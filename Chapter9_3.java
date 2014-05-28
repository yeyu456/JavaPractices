import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.Exception;


public class Chapter9_3 {
	public static void delString(String delStr, String fileName) throws Exception{
		FileWriter fw;
		File f = new File(fileName);
		File tempFile = new File(fileName + ".bak");
		fw = new FileWriter(tempFile);
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			String temp = br.readLine();
			while(temp!=null){
				if(temp.matches(".*" + delStr + ".*")){
					temp = temp.replace(delStr, "");
				}
				temp += System.getProperty("line.separator");
				fw.write(temp);
				fw.flush();
				temp = br.readLine();
			}
			fw.close();
		}
		f.delete();
		if(!tempFile.renameTo(new File(fileName)))
				System.out.println("rename Failed.");
	}
	
	public static void main(String[] args){
		try{
			delString(args[0], args[1]);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
