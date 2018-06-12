/* Assignment number:  7.2
 * File Name:          Nbody.java 
 * Name (First Last):  Andrey Kastelmacher
 * Student ID :        303258537 
 * Email :             Andrey Kastelmacher@post.idc.ac.il
 */  
public class Nbody {
	
    /**
     * populates an array of Bodies called galaxy, with initial location, velocity mass and planet image.
     * 
     * @param nPlanets - number of planets to read.
     * @return a populated array of bodies with initial position
     */
	private static Body[] readBody( int nPlanets ){
		Body[] galaxy = new Body[ nPlanets ];
        for( int y = 0; y < nPlanets; y++ ){
        	double locX = StdIn.readDouble();
        	double locY = StdIn.readDouble();
        	double Vx = StdIn.readDouble();
        	double Vy = StdIn.readDouble();
        	double mass = StdIn.readDouble();
    		String planetName = StdIn.readString();
        	Vector location = new Vector( locX, locY );
    		Vector Velocity = new Vector( Vx, Vy );
            galaxy[ y ] = new Body( location, Velocity, mass, planetName );
        }
		return galaxy;
	}
	
	/**
	 * simulates the movement of the planets in a galaxy,
	 * calculates the gravitaional pull, velocity, accelelration and location at each time step.
	 * it uses StdDraw to draw each step of the animation and draws a starfield at the end of each step.
	 * @param galaxy - an array of Body, with initial planet data for each body
	 * @param nPlanets - the amount of planets in the galaxy
	 * @param dt - time interval for simulation (86400 for 1 day in seconds)
	 * @param N - amount of time steps to calculate. (365 if given 86400 seconds in a day)
	 * @param scale - the scale of the canvas of the simulation.
	 */
	private static void drawPlanets(Body[] galaxy, int nPlanets, double dt, int N, double scale){ 
		// create a starfield background
		Vector uniLoc = new Vector( 0.5 ,0.5 );
		Vector uniVel = new Vector( 0 ,0 );
		double uniMass = 0.0;
		String uniName = "starfield.jpg";
		Body Starfield = new Body( uniLoc, uniVel, uniMass , uniName );
		
        for ( int step = 0 ; step < N; step ++ ){         	
        	//Gravitational pull
        	for ( int thisBody = 0; thisBody < nPlanets; thisBody++ ){
        		for( int otherBody = 0; otherBody < nPlanets; otherBody++ ){
        			if ( thisBody != otherBody ){
        			galaxy[ thisBody ].GravitaionalPull( galaxy[ otherBody ], dt );	
        			}
        		}	
        	}
        	for ( int thisBody = 0; thisBody < nPlanets; thisBody++ ){
        		galaxy[ thisBody ].move( dt );
        		galaxy[ thisBody ].draw( scale );	
        		if (step == N-1){StdOut.println(galaxy[thisBody].toString());} // print the last coordinate of simulation

        	}
    		StdDraw.show( 30 );
    		Starfield.draw( scale );
    		
        }
	}
	/**
	 * Nbody main, simulates the movement of a number of "nPlanets" in a galaxy on x/y axis.
	 * input user command line args: recommended - N steps (365 - for the days), dt - the time step,
	 * 86400 seconds for one day, and input file planets.txt
	 * with this input you can watch the simulation over a year for awhole earth around sun cycle.
	 * 
	 * @param args - N - amount of time steps.
	 * dt - time interval in seconds,
	 * textFile - data input file.
	 * 
	 * uses readBody to populate an array of Bodies with the data
	 * drawPlanets - to draw the simulation
	 */
	public static void main( String[] args ){
		int N = Integer.parseInt( args[0] ); // amount of steps
		double dt = Double.parseDouble( args[ 1 ] ); // steps duration
		String textFile = args[ 2 ]; // file to process
		StdIn.setInput( textFile );
	    int nPlanets = StdIn.readInt(); // Number of runs
	    double scale = StdIn.readDouble();
	    
	    Body[] galaxy = readBody( nPlanets );
	    drawPlanets ( galaxy, nPlanets, dt, N, scale );    
	}
}
