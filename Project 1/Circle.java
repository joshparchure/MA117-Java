/* 
 * PROJECT I: Circle.java
 *
 * This file contains a template for the class Circle. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Point class.
 *
 * Remember not to change the types, names, parameters or return types of any
 * functions or variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Circle {

    /*
     * Here, YOU should define private variables that represent the radius and
     * centre of this particular Circle. The radius should be of type double,
     * and the centre should be of type Point.
     */

     // J- completed.
     // r
     private double r;
     // A
     private Point A;
    

    // =========================
    // Constructors
    // =========================
    /**
     * Default constructor - performs no initialization.
     */
    public Circle() {
        // This method is complete.
    }
    
    /**
     * Alternative constructor, which sets the circle up with x and y
     * co-ordinates representing the centre, and a radius. Remember you should
     * not store these x and y co-ordinates explicitly, but instead create a
     * Point to hold them for you.
     *
     * @param xc   x-coordinate of the centre of the circle
     * @param yc   y-coordinate of the centre of the circle
     * @param rad  radius of the circle
     */
    public Circle(double xc, double yc, double rad) {
        // You need to fill in this method.
        // Uses setters to construct a circle from coordinates and a radius
        setCentre(xc,yc);
        setRadius(rad);
    }

    /**
     * Alternative constructor, which sets the circle up with a Point
     * representing the centre, and a radius.
     *
     * @param centre  Point representing the centre
     * @param rad     Radius of the circle
     */
    
    public Circle(Point centre, double rad) {
        // You need to fill in this method.
        // Uses setters to construct a circle from a point (centre) and a radius
        setCentre(centre);
        setRadius(rad);
    }

    // =========================
    // Setters and Getters
    // =========================

    /**
     * Setter - sets the co-ordinates of the centre.
     *
     * @param xc  new x-coordinate of the centre
     * @param yc  new y-coordinate of the centre
     */   
    public void setCentre(double xc, double yc) {
        // You need to fill in this method.
         this.A = new Point(xc,yc);
    }

    /**
     * Setter - sets the centre of the circle to a new Point.
     *
     * @param C  A Point representing the new centre of the circle.
     */   
    public void setCentre(Point C) {
        // You need to fill in this method.
        this.A = C;
    }
    
    /**
     * Setter - change the radius of this circle.
     *
     * @param rad  New radius for the circle.
     */   
    public void setRadius(double rad) {
        // You need to fill in this method.
        this.r = rad;
    }
    
    /**
     * Getter - returns the centre of this circle.
     *
     * @return The centre of this circle (a Point).
     */   
    public Point getCentre(){
        // You need to fill in this method.
        return this.A;
    }

    /**
     * Getter - extract the radius of this circle.
     *
     * @return The radius of this circle.
     */   
    public double getRadius(){
        // You need to fill in this method.
        return this.r;
    }

    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Circle.
     *
     * @return A String of the form: "[Ax,Ay,Radius]" where Ax and Ay are
     *         numerical values of the coordinates, and Radius is a numerical
     *         value of the radius.
     */
    public String toString() {
        // You need to fill in this method.
        // This converts to a string and rounds to 9 decimal places which is useful for printing
        return String.format("[%.9f,%.9f,%.9f]",this.getCentre().getX(),this.getCentre().getY(),this.r);
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    /**
     * Similar to the equals() function in Point. Returns true if two Circles
     * are equal. By this we mean:
     * 
     * - They have the same radius (up to the tolerance defined in Point).
     * - They have the same centre (up to the tolerance defined in Point).
     * 
     * Remember that the second test is already done in the Point class!
     * 
     * @param c The circle to compare this with.
     * @return true if the two circles are equal.
     */
    public boolean equals(Circle c) {
        // You need to fill in this method.
        return this.A.equals(c.getCentre()) && (Math.abs(this.r - c.getRadius()) <= Point.GEOMTOL);
    }
    
    // -----------------------------------------------------------------------
    // Do not change the method below! It is essential for marking the
    // project, and you may lose marks if it is changed.
    // -----------------------------------------------------------------------

    /**
     * Compare this Circle with some Object, using the test above.
     *
     * @param obj  The object to compare this with.
     * @return true if the two objects are equal.
     */
    public boolean equals(Object obj) {
        // This method is complete.
        
        if (obj instanceof Circle) {
            boolean test = false;
            Circle C = (Circle)obj;
            
            test = this.equals(C);

            return test;
        } else {
            return false;
        }
    }

    // ======================================
    // Implementors
    // ======================================
    
    /**
     * Computes and returns the area of the circle.
     *
     * @return  Area of the circle
     */
    public double area() {
        // You need to fill in this method.
        return Math.PI*Math.pow(this.getRadius(),2);
    }

    // =======================================================
    // Tester - test methods defined in this class
    // =======================================================
    
    public static void main(String args[]) {
        // You can use this method for testing.
        // Testing constructors and creating circles for further testing.
        Circle circle_1 = new Circle(3,4,5);
        Circle circle_2 = new Circle(new Point(2,-1),6);
        Circle circle_3 = new Circle(-2,4,3);
        Circle circle_4 = new Circle(3,4.00000001,5.00000001);
        Circle circle_5 = new Circle(new Point(0,0),0);
        Circle circle_6 = new Circle(0,0,0);

        // Test getters.
        System.out.println(circle_1.getCentre());
        System.out.println(circle_2.getCentre());
        System.out.println(circle_3.getRadius());
        System.out.println(circle_4.getRadius());

        // Test setters.
        circle_5.setRadius(6);
        circle_5.setCentre(new Point(5,6));
        System.out.println(circle_5);

        // Test conversion.
        System.out.println("");
        System.out.println(circle_1.toString());
        System.out.println(circle_3.toString());

        // Test if 2 circles are equal.
        System.out.println("");
        System.out.println(circle_1.equals(circle_4));
        System.out.println(circle_2.equals(circle_3));

        // Test area.
        System.out.println("");
        System.out.println(circle_1.area());
        System.out.println(circle_3.area());
    }
}