/* Assignment number:  7.1
 * File Name:          body.java 
 * Name (First Last):  Andrey Kastelmacher
 * Student ID :        303258537 
 * Email :             Andrey Kastelmacher@post.idc.ac.il
 */ 
import java.awt.Color;

public class Body {
		
		final static double G = 6.67 * Math.pow(10,-11); 
		Vector location;	 // location vector of the body
		Vector velocity; 	 // velocity of the body
		double mass; 		 // mass of the body
		String pic;			 // filename of the body's image
		
		/**
		 * Standard Constructor for Body
		 * 
		 * @param loc the location vector of the body.
		 * @param v  the velocity vector of the body.
		 * @param mass  the mass of the body.
		 * @param pic  a filename for an image file containing an image of the body.
		 */
		public Body (Vector loc, Vector v, double mass, String pic){
			this.location = loc;
			this.velocity = v;
			this.mass = mass;
			this.pic = pic;
		}
		
		/**
		 * Calculates the distance between this body and another body.
		 * Calculation is done according to the Pythagorean theorem.
		 * 
		 * @param other the body from which the distance is calculated
		 * @return The distance from this planet to other.
		 */
		public double distance ( Body other ){
			double deltaX = this.location.getXcomponent() - other.location.getXcomponent();
			double deltaY = this.location.getYcomponent() - other.location.getYcomponent();
			return Math.hypot( deltaX, deltaY );

		}
		
		/**
		 * Calculates the gravitational force exerted by another body on this body.
		 * It then changes the current velocity vector according to the gravitational force
		 * With respect to a given time step.
		 * 
		 * It is assumed that the location of the other body is different than the location 
		 * of this body.
		 * 
		 * Calculation is done in several steps:
		 * - Calculate the distance between the bodies.
		 * - Calculate the gravitational force according to Newton's law of universal gravitation.
		 * - Calculate the partial accelerations in the x axis and the y axis.
		 * - Update the velocity vector of this body according to Newton's laws of motion.
		 * 
		 * @param other - the body who exerts a gravitational pull on this body.
		 * @param dt(delta t) - the time step.
		 */
		public void GravitaionalPull( Body other, double dt ) {
			double r = this.distance( other );
			double f = ( G * this.mass * other.mass ) / Math.pow( r, 2 );
			double Vx = this.velocity.getXcomponent();
			double Vy = this.velocity.getYcomponent();
			double deltaX = other.location.getXcomponent() - this.location.getXcomponent();
			double deltaY = other.location.getYcomponent() - this.location.getYcomponent();
            double fX = f * ( deltaX / r );
            double fY = f * ( deltaY / r);
            double aX = fX / this.mass;
            double aY = fY / this.mass;
            this.velocity = new Vector( Vx + ( dt * aX ), Vy + ( dt * aY ));
		}
		

		/**
		 * Updates the current location of the body according to the current velocity vector.
		 * Calculation is done according to Newton's law of motion with a given time step.
		 * 
		 * @param dt(delta t) - the time step
		 */
		public void move(double dt) {
			double locX = this.location.getXcomponent();
			double locY = this.location.getYcomponent();
			double Vx = this.velocity.getXcomponent();
			double Vy = this.velocity.getYcomponent();
			this.location = new Vector ( locX + ( dt * Vx ), locY + ( dt * Vy ));			
		}
		
		/**
		 * Draws the picture (Using StdDraw.picture) associated to the 
		 * body at the body's location.
		 * 
		 * In case the image file containing the image does not exist, 
		 * this function will instead draw a filled yellow circle of radius 25000000000.0
		 * at the body's location.
		 * 
		 * Use StdDraw.setPenColor(StdDraw.Yellow) to change the pen's color to yellow.
		 * And StdDraw.filledCircle to draw a circle.
		 * 
		 * Hint: StdDraw.picture throws an IllegalArgumentException if the image file does not exist.
		 * @param scale - the scale of the canvas
		 */
		public void draw(double scale) {
			try {
				StdDraw.setXscale( -scale, +scale );
				StdDraw.setYscale( -scale, +scale );

				StdDraw.picture( this.location.getXcomponent(), this.location.getYcomponent(), pic );
				
			} catch (IllegalArgumentException s) { 
				StdDraw.setPenColor( Color.YELLOW );
				StdDraw.filledCircle( this.location.getXcomponent(), this.location.getYcomponent(), 25000000000.0 );
				
			}
		}
		
		/**
		 * Returns a string representation of the body.
		 * A string representing a body needs to be in the exact same format 
		 * as the line representing the body in the input files.
		 * 
		 */
		public String toString(){
			double locX = this.location.getXcomponent();
			double locY = this.location.getYcomponent();
			double Vx = this.velocity.getXcomponent();
			double Vy = this.velocity.getYcomponent();
			double mass = this.mass;
			String pic = this.pic;
			
			return String.valueOf(locX) +" , "+ String.valueOf(locY) +" , "+
			String.valueOf(Vx) +" , "+ String.valueOf(Vy)
			+" , "+ String.valueOf(mass) +" , "+ pic;
		} 
}
