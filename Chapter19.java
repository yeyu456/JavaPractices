import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Chapter19 {
	public static void main(String[] args){
		try{
			BitOutputStream test = new BitOutputStream(new File("src/test.txt"));
			test.WriteBit('1');
			test.WriteBit("01011011111111111111111111111");
			test.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class BitOutputStream extends FileOutputStream {
	private byte tempByte = 0;
	private int count = 0;
	
	public BitOutputStream(File file) throws FileNotFoundException{
		super(file, true);
	}
	
	public void WriteBit(char bit) throws IOException{
		tempByte = (byte)(tempByte << 1 | (bit & 0x1));
		System.out.println(tempByte);
		count++;
		if(count==8){
			super.write(tempByte);
			super.flush();
			count = 0;
			tempByte = 0;
		}
	}
	
	public void WriteBit(String bit) throws IOException{
		char[] c = bit.toCharArray();
		for(char n:c){
			this.WriteBit(n);
		}
	}
	
	public void close() throws IOException{
		while(count!=0&&count!=7){
			this.WriteBit('0');
		}
		super.close();
	}
}