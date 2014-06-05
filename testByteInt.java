
public class testByteInt {
    
    public static void main(String[] args) {
        
        byte[] temp = new byte[4];
        int data = 0;
        for(int n=0;n<4;n++)
            temp[n] = (byte)(511 >> ((3-n) * 8) & 0xFF);
        for(byte n:temp)
            System.out.println(n);
        for(int n=0;n<4;n++){
            data<<=8;
            data |= (temp[n] & 0xFF);
        }
        System.out.println(data);
    }
    
}
