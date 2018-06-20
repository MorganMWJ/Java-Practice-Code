
public class Application {

	public static void main(String[] args) {
		Point p1 = new Point(0.3,3);
		Point p2 = new Point(1,1);
		Line l = new Line(p1,p2);
		System.out.println(p1.toString() + " " + p2.toString());
		System.out.println(l.getMidPoint().toString());
		System.out.println("length = " + l.getLength());
		Fraction f = new Fraction(l.getLength());
		System.out.println("numer = " + f.getNumerator() + "\ndenumer = " + f.getDenominator() + "\nlength in fraction= " + f.toString());
		
		
		
		
		
		
		
		
		//System.out.println(l.getGradient());
		//System.out.println(l.toString());
	}

}
