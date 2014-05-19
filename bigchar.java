/*print java
 * 
 */

public class bigchar {
	public static void word(){
		System.out.println("   J    A    V    V    A   ");
		System.out.println("   J   A A    V  V    A A  ");
		System.out.println("J  J  AAAAA    VV    AAAAA ");
		System.out.println(" JJ  A     A    V   A     A");
	}
	
	public static void word2(){
		String j[] = {"   J ", "   J ", "J  J ", " JJ  "};  
		String a[] = {"   A   ", "  A A  ", " AAAAA", "A     A"};
		String v[] = {"V    V", " V  V ", "   VV  ", "   V  "};
		for(int n=0;n<4;n++){
			System.out.print(j[n]);
			System.out.print(a[n]);
			System.out.print(v[n]);
			System.out.println(a[n]);
		}
	}
	
	public static void main(String[] args){
		word2();
	}
}
