
public class Body {
	// creates instance variables//
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	//creates the original Body object with parameters
	public Body(double xp, double yp, double xv, double yv, double mass, String fileName) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = fileName;
	}
	//creates the Body object from an existing Body
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
	//calculates the distance between multiple bodies
	public double calcDistance(Body b) {
		double dx = (b.myXPos - myXPos);
		double dy = (b.myYPos - myYPos);
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	//calculates the total force between two bodies
	public double calcForceExertedBy(Body p) {
		double force = ((6.67 * 1e-11) * myMass * p.myMass) / (this.calcDistance(p) * this.calcDistance(p));
		return force;
	}
	
	//calculates the sum of the forces in the X direction
	public double calcForceExertedByX(Body p) {
		double fx;
		fx = this.calcForceExertedBy(p) * (p.myXPos - myXPos) / (this.calcDistance(p));
		return fx;
	}

	//calculates the sum of the forces in the Y direction
	public double calcForceExertedByY(Body p) {
		double fy;
		fy = this.calcForceExertedBy(p) * (p.myYPos - myYPos) / (this.calcDistance(p));
		return fy;
	}

	//calculates the sum of the forces in the X direction, minus the force of the body on itself
	public double calcNetForceExertedByX(Body[] bodies) {
		double totalForceX = 0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				totalForceX += this.calcForceExertedByX(b);
			}
		}
		return totalForceX;
	}

	//calculates the sum of the forces in the Y direction, minus the force of the body on itself
	public double calcNetForceExertedByY(Body[] bodies) {
		double totalForceY = 0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				totalForceY += this.calcForceExertedByY(b);
			}
		}
		return totalForceY;
	}

	//mutator function, updates the position and velocity of the bodies
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
	//draws the bodies
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
