
public class Cirlce {
	
	private int radius;
	
	public int getDiameter(){
		return radius*2;
	}
	
	public double getCircum(){
		return this.getDiameter()*Math.PI;
	}
}
