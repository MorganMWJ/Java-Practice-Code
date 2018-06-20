import java.text.DecimalFormat;


public class Line {
	
	private Point pointA;
	private Point pointB;
	
	Line(Point a, Point b){
		pointA=a;
		pointB=b;
	}
	
	public Point getMidPoint(){
		double x = (this.pointA.getX()+this.pointB.getX())/2;
		double y = (this.pointA.getY()+this.pointB.getY())/2;
		return new Point(x,y);
	}
	
	public double getLength(){
		double sideA = pointA.getX()-pointB.getX();
		double sideB = pointA.getY()-pointB.getY();
		return format(Math.sqrt((sideA*sideA) + (sideB*sideB)));
	}
	
	public double getGradient(){
		double changeInY = pointA.getY()-pointB.getY();
		double changeInX = pointA.getX()-pointB.getX();
		return format(changeInY/changeInX);
	}
	
	private double format(double x){
		DecimalFormat df = new DecimalFormat("#.###"); 
		String result = df.format(x);
		return Double.parseDouble(result);
	}
	
	public boolean hasUndefGrad(){
		//if this line is vertical
		if(pointA.getX()-pointB.getX()==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String toString(){
		//equation of the line [y = mx + c]
		//y-y1=m(x-x1)
		double intercept = (-1*this.getGradient()*this.pointA.getX()) + this.pointA.getY();
		Fraction c = new Fraction(intercept);
		Fraction m = new Fraction(this.getGradient());
		if(intercept<0){
			return "y = " + m.toString() + "x - " + c.toString();
		}
		else if (intercept>0){
			return "y = " + m.toString() + "x + " + c.toString();
		}
		else{
			return "y = " + m.toString() + "x";
		}
	}
}
