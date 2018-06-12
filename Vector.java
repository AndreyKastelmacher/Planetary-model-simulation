/**
 * Represents a two dimensional vectors.
 * 
 * @author Intro2CS staff
 *
 */
public class Vector {
	
	private double x;	// the x coordinate of the vector
	private double y;	// the y coordinate of the vector
	
	/**
	 * Standard Constructor
	 * 
	 * @param x  x coordinate
	 * @param y  y coordinate
	 */
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x component of the vector
	 * 
	 * @return the x component
	 */
	public double getXcomponent(){
		return x;
	}
	
	/**
	 * Returns the y component of the vector
	 * 
	 * @return the y component
	 */
	public double getYcomponent(){
		return y;
	}
	
	/**
	 * Returns a new vector which is the sum of this vector and the other.
	 * 
	 * @param other  the vector to be added.
	 * @return a new vector which is the addition.
	 */
	public Vector addition(Vector other){
		double x = this.getXcomponent() + other.getXcomponent();
		double y = this.getYcomponent() + other.getYcomponent();
		return new Vector(x,y);
	}
	
	/**
	 * Multiplies the coordinates of this vector according to a given scalar.
	 * 
	 * @param scalar  a number to multiply the coordinates.
	 */
	public void scalarMultiplication(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}

	/**
	 * Calculates the size of this vector. 
	 * Uses the Pythagorean theorem for calculation.
	 * 
	 * @return the size of the vector
	 */
	public double size(){
		return Math.hypot(x, y);
	}
	
	/**
	 * Calculates the angle of this vector from the x-axis.
	 * 
	 * @return the angle
	 */
	public double angle(){
		return Math.atan2(y, x);
	}
	
	/**
	 * Returns a string representation of vector in the following format: (x, y)
	 * 
	 */
	public String toString(){
		return "(" + this.x + ", " + this.y + ")";
	}
}
