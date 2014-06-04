import java.net.URLDecoder;
import java.net.URLEncoder;


public class testCoder {
    public static void main(String[] args) throws Exception{
        String str = URLEncoder.encode("ÖÐÎÄ", "utf-8");
        byte[] bt = str.getBytes();
        String str1 = new String(bt);
        URLDecoder ud = new URLDecoder();  
        System.out.println(ud.decode(str1, "utf-8"));
    }
}
