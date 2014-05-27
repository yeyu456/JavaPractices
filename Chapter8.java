
public class Chapter8 {
	public static void main(String[] args){
		RegularPolygon test = new RegularPolygon();
		RegularPolygon test1 = new RegularPolygon(6, 4);
		RegularPolygon test2 = new RegularPolygon(10, 4, 5.6, 7.8);
		
		System.out.println(test.getPerimeter() + " "
		 + test1.getPerimeter() + " "
		 + test2.getPerimeter());
		
		System.out.println(test.getArea() + " "
		 + test1.getArea() + " "
		 + test2.getArea());
	}
}

class RegularPolygon{
	private int n = 3;
	private double side = 1;
	private double x = 0;
	private double y = 0;
	private double R;
	
	public RegularPolygon(){
		R = Math.tan(180 / n / 2) * side;
	}
	
	public RegularPolygon(int n1, double side1){
		n = n1;
		side = side1;
		R = Math.tan(180 / n / 2) * side;
	}
	
	public RegularPolygon(int n2, double side2, double x2, double y2){
		n = n2;
		side = side2;
		x = x2;
		y = y2;
		R = Math.tan(180 / n / 2) * side;
	}
	
	public String getData(String data){
		switch(data){
		case "n" :
			return Integer.toString(n);
		case "side" :
			return Double.toString(side);
		case "x" :
			return Double.toString(x);
		case "y" :
			return Double.toString(y);
		default :
			return "";
		}
	}
	
	public void setN(int nSet){
		n = nSet;
	}
	
	public void setSide(double sideSet){
		side = sideSet;
	}
	
	public void setX(double xSet){
		x = xSet;
	}
	
	public void setY(double ySet){
		y = ySet;
	}
	
	public double getPerimeter(){
		return side * n;
	}
	
	public double getArea(){
		return n * Math.pow(R, 2) * Math.sin(2 * Math.PI / n) / 2;
	}
}