/*
 * 
 * PROJECT I: Project1.java
 *
 * This file contains a template for the class Point. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * Remember not to change the types, names, parameters or return types of any
 * functions or variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Point {
    /**
     * x and y co-ordinates of the point on the plane. 
     */
    private double X, Y;
    
    /**
     * GEOMTOL is described in the formulation. It describes the tolerance
     * that we are going to use throughout this project. Remember that you do
     * NOT need to redefine this in other classes.
     */
    public static final double GEOMTOL = 1.0e-6;

    // =========================
    // Constructors
    // =========================
    
    /**
     * Default constructor - this initializes X and Y to the point (0,0).
     */
    public Point() {
        // This method is complete.
        setPoint(0.0, 0.0);
    }

   /**
    * Two-parameter version of the constructor. Initialiases (X,Y) to the
    * point (a,b) which is supplied to the function.
    *
    * @param a - x-coordinate of the point
    * @param b - y-coordinate of the point
    */
    public Point (double a, double b) {
        // This method is complete.
        setPoint(a, b);
    }

    // =========================
    // Setters and Getters
    // =========================
    
    /**
     * Setter for instance variables - sets the coordinates of the point.
     *
     * @param X - new x-coordinate for this point.
     * @param Y - new y-coordinate for this point
     */
    public void setPoint(double X, double Y) {
        // This method is complete.
        this.X = X;
        this.Y = Y;
    }

    /**
     * Getter for x co-ordinate. 
     *
     * @param  none
     * @return The x co-ordinate of this point.
     */   
    public double getX() {
    	// You need to fill in this method.
        return this.X;
    }

    /**
     * Getter for y co-ordinate. 
     *
     * @param  none
     * @return The y co-ordinate of this point.
     */   
    public double getY() {
	    // You need to fill in this method.
        return this.Y;
    }
    
    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Point.
     *
     * @return A String of the form [X,Y]
     */
    public String toString() {
    	// This method is complete.
        return String.format("[%.9f,%.9f]",X,Y);
    }

    // ==========================
    // Implementors
    // ==========================
    
    /**
     * Compute the distance of this Point to the supplied Point x.
     *
     * @param x  Point from which the distance should be measured.
     * @return   The distance between x and this instance of a Point
     */
    public double distance(Point x) {
        // You need to fill in this method.
        double X = x.getX();
        double Y = x.getY();
        return Math.sqrt(Math.pow((this.X-X),2)+Math.pow((this.Y-Y),2));
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    // -----------------------------------------------------------------------
    // Do not change the two methods below! They are essential for marking the
    // project, and you may lose marks if they are changed.
    //
    // We shall talk about these functions later (week 17).
    // -----------------------------------------------------------------------

    /**
     * Compare this with some Object. Two points are equal if their are in a
     * box given by the constant GEOMTOL.
     *
     * @param obj The object to compare with.
     * @return If obj is a Quaternion with the same coefficients.
     */
    public boolean equals(Object obj) {
        // This method is complete.
        if (obj instanceof Point) {
            Point q = (Point)obj;
            return (Math.abs(X - q.X) <= GEOMTOL && 
                    Math.abs(Y - q.Y) <= GEOMTOL);
        } else {
            return false;
        }
    }

    /**
     * Compare two points. Two points are equal if their are in a box given by
     * the constant GEOMTOL.
     *
     * @param q  A Point to be compared to this instance
     * @return   true if Point q is equal to this instance.
     */
    public boolean equals(Point q) {
        return (Math.abs(X - q.X) <= GEOMTOL && 
            Math.abs(Y - q.Y) <= GEOMTOL);
    }

    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main (String args[]) {
        // You can use this method for testing.
        // Testing constructors and creating points for further testing.
        Point point_1 = new Point(1,1);
        Point point_2 = new Point(2,-5);
        Point point_3 = new Point(-3,7);
        Point point_4 = new Point(-6,-7);
        Point point_5 = new Point(0,0);
        Point point_6 = new Point(-3.00000001,6.9999999995);

        // Testing getters.
        System.out.println(point_1.getX());
        System.out.println(point_2.getY());

        // Testing setters.
        point_5.setPoint(7,8);
        System.out.println(point_5);

        // Testing converters.
        System.out.println(point_5.toString());

        // Testing distances.
        System.out.println("The distance between points 1 and 2 is = "+point_1.distance(point_2)+"\n");
        System.out.println("The distance between points 2 and 3 is = "+point_2.distance(point_3)+"\n");

        // Testing point comparisons.
        System.out.println(point_2.equals(point_1));
        System.out.println(point_3.equals(point_6));
    } 
}