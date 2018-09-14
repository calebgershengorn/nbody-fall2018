
public class Body {
	// creates instance variables
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	/** Creates a body from parameters
	 * @param xp initial X position
	 * @param yp initial Y position
	 * @param xv initial X velocity
	 * @param yv initial Y velocity
	 * @param mass of object
	 * @param fileName of object for animation
	 */
	public Body(double xp, double yp, double xv, double yv, double mass, String fileName) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = fileName;
	}
	//creates the Body object from an existing Body
	/**Copy constructor: copy instance variables 
	 * from one body to the other
	 * @param b used to initalize the body
	 */
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}
	//these create the get functions to enable the 'this' calls to work
	public double getXVel() {
		return this.myXVel;
	}

	public double getYVel() {
		return this.myYVel;
	}

	public double getX() {
		return this.myXPos;
	}

	public double getY() {
		return this.myYPos;
	}

	public double getMass() {
		return this.myMass;
	}

	public String getName() {
		return this.myFileName;
	}
	/**
	 * 
	 * @param b is the body passed to calcDistance 
	 * @return distance between two bodies
	 */
	public double calcDistance(Body b) {
		double dx = (b.myXPos - myXPos);
		double dy = (b.myYPos - myYPos);
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	/**
	 * 
	 * @param p is the body passed to calcForceExertedBy 
	 * @return calculates the total force due to gravity between two bodies
	 */
	public double calcForceExertedBy(Body p) {
		double force = ((6.67 * 1e-11) * myMass * p.myMass) / (this.calcDistance(p) * this.calcDistance(p));
		return force;
	}
	
	/**
	 * 
	 * @param p is the body passed to calcForceExertedByX
	 * @return the X component of the force between the two bodies
	 */
	public double calcForceExertedByX(Body p) {
		double fx;
		fx = this.calcForceExertedBy(p) * (p.myXPos - myXPos) / (this.calcDistance(p));
		return fx;
	}

	/**
	 * 
	 * @param p is the body passed to calcForceExertedByY
	 * @return the Y component of the force between the two bodies
	 */
	public double calcForceExertedByY(Body p) {
		double fy;
		fy = this.calcForceExertedBy(p) * (p.myYPos - myYPos) / (this.calcDistance(p));
		return fy;
	}

	/**
	 * calculates the sum of all the forces in the X direction
	 * @param array of bodies
	 * @return total X forces without the body acting on itself
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double totalForceX = 0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				totalForceX += this.calcForceExertedByX(b);
			}
		}
		return totalForceX;
	}

	/**
	 * calculates the sum of all the forces in the Y direction
	 * @param array of bodies
	 * @return total Y forces without the body acting on itself
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double totalForceY = 0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				totalForceY += this.calcForceExertedByY(b);
			}
		}
		return totalForceY;
	}

	/**
	 * Mutator function: changes the value of the X and Y position and 
	 * @param deltaT is the time between cycles 
	 * @param xForce is the total force in the X direction
	 * @param yForce is the total force in the X direction
	 */
	public void update(double deltaT, double xForce, double yForce) {
		double ax = xForce / myMass;
		double ay = yForce / myMass;
		double nvx = myXVel + deltaT * ax;
		double nvy = myYVel + deltaT * ay;
		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	/**
	 * redraws the Bodies
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
