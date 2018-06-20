
public class Point {
	
	private double x;
	private double y;
	
	Point(){
		this.x=0;
		this.y=0;
	}
	
	Point(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}
	
	public double getY(){
		return y;
	}
	
	public double getX(){
		return x;
	}
	
	public String toString(){
		Fraction x = new Fraction(this.x);
		Fraction y = new Fraction(this.y);
		return "(" + x.toString() + "," + y.toString() + ")";
	}
}
