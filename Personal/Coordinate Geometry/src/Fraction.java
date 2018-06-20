
public class Fraction {
	private int numerator;
	private int denominator;
	/////////////////////////////////////////////////////////////////////////////
	Fraction(double x){
		//times top and bottom by 10 to the power of decimal places
		int decimalPlaces = getDecPlaces(x);
		long tempNum = (long) (x * (Math.pow(10, decimalPlaces)));
		long tempDenum = (long) (1.0*(Math.pow(10, decimalPlaces)));
		//Reduce the fraction now
		//divide both the top and bottom by the hcf
		int hcf = hcf(tempNum,tempDenum);
		this.numerator = (int) tempNum/hcf;
		this.denominator = (int) tempDenum/hcf;
	}
	
	//Highest Common Factor of two integers
	private int hcf(long n,long d){
		//Euclidean Algorithm
		if(d==0){
			return (int) n;
		}
		return hcf(d,n%d);
	}
	
	private int getDecPlaces(double x){
		String text = "" + x;
		String[] split = text.split("\\.");
		return split[1].length();
	}	
/////////////////////////////////////////////////////////////////////////////
	
	public int getNumerator(){
		return this.numerator;
	}
	
	public int getDenominator(){
		return this.denominator;
	}
	
	public String toString(){
		int copyNum = this.numerator;
		int copyDenum = this.denominator;
		//if both top and bottom both have negative signs-remove both signs || if denominator is the only one with a negative sign-move sign onto the numerator
		if((this.numerator<0 && this.denominator<0) || (this.denominator<0 && this.numerator>0)){
			copyNum *= -1;
			copyDenum *= -1;
		}
		
		if(this.denominator==1){
			return "" + this.numerator;
		}
		else if(this.denominator==this.numerator){
			return "1";
		}
		else{
			return copyNum + "/" + copyDenum;
		}
	}
}
