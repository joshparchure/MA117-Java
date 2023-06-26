/*
 * PROJECT II: Secant.java
 *
 * This file contains a template for the class Secant. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * In this class, you will create a basic Java object responsible for
 * performing the Secant root finding method on a given polynomial
 * f(z) with complex co-efficients. The formulation outlines the method, as
 * well as the basic class structure, details of the instance variables and
 * how the class should operate.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 * 
 * Tasks:
 *
 * 1) Complete this class with the indicated methods and instance variables.
 *
 * 2) Fill in the following fields:
 *
 * NAME: Ameya (Josh) Parchure
 * UNIVERSITY ID: u2100098
 * DEPARTMENT: Maths
 */

public class Secant {
    /**
     * The maximum number of iterations that should be used when applying
     * Secant. Ensure this is *small* (e.g. at most 50) otherwise your
     * program may appear to freeze.
     */
    public static final int MAXITER = 20;

    /**
     * The tolerance that should be used throughout this project. Note that
     * you should reference this variable and _not_ explicity write out
     * 1.0e-10 in your solution code. Other classes can access this tolerance
     * by using Secant.TOL.
     */
    public static final double TOL = 1.0e-10;

    /**
     * The polynomial we wish to apply the Secant method to.
     */
    private Polynomial f;


    /**
     * A root of the polynomial f corresponding to the root found by the
     * iterate() function below.
     */
    private Complex root;
    
    /**
     * The number of iterations required to reach within TOL of the root.
     */
    private int numIterations;

    /**
     * An enumeration that signifies errors that may occur in the root finding
     * process.
     *
     * Possible values are:
     *   OK: Nothing went wrong.
     *   ZERO: Difference went to zero during the algorithm.
     *   DNF: Reached MAXITER iterations (did not finish)
     */
    enum Error { OK, ZERO, DNF };
    private Error err = Error.OK;
    
    
    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Basic constructor.
     *
     * @param p  The polynomial used for Secant.
     */
    public Secant(Polynomial p) {
        this.f = p;
        // You need to fill in this method.
    }

    // ========================================================
    // Accessor methods.
    // ========================================================
    
    /**
     * Returns the current value of the err instance variable.
     */
    public Error getError() {
        // You need to fill in this method with the correct code.
        return this.err;
        // return Error.OK;
    }

    /**
     * Returns the current value of the numIterations instance variable.
     */
    public int getNumIterations() { 
        // You need to fill in this method with the correct code.
        return this.numIterations;
        // return 0;
    }
    
    /**
     * Returns the current value of the root instance variable.
     */
    public Complex getRoot() {
        // You need to fill in this method with the correct code.
        return this.root;
        // return new Complex();
    }

    /**
     * Returns the polynomial associated with this object.
     */
    public Polynomial getF() {
        // You need to fill in this method with the correct code.
        return this.f;
        // return new Polynomial();
    }

    // ========================================================
    // Secant method (check the comment)
    // ========================================================
    
    /**
     * Given two complex numbers z0 and z1, apply Secant to the polynomial f in
     * order to find a root within tolerance TOL.
     *
     * One of three things may occur:
     *
     *   - The root is found, in which case, set root to the end result of the
     *     algorithm, numIterations to the number of iterations required to
     *     reach it and err to OK.
     *   - At some point the absolute difference between f(zn) and f(zn-1) becomes zero. 
     *     In this case, set err to ZERO and return.
     *   - After MAXITER iterations the algorithm has not converged. In this 
     *     case set err to DNF and return.
     *
     * @param z0,z1  The initial starting points for the algorithm.
     */
    public void iterate(Complex z0, Complex z1) {
        // You need to fill in this method.

        err = Error.OK;

        // Initialise an array where the sequence of generated complex numbers will be stored.
        Complex[] zn = new Complex[MAXITER+1];
        // Set the first 2 elements of the sequence as the intial values, i.e. the paramaters given.
        zn[0]=z0;
        zn[1]=z1;

        // This section of code will generate the sequence and check its convergence.
        for(int i=1;i<MAXITER;i++){
            // Defining the numerator and denominator of the fraction in the secant difference equation.
            Complex numerator = zn[i].add(zn[i-1].negate());
            Complex denominator = f.evaluate(zn[i]).add((f.evaluate(zn[i-1])).negate());

            // Defining the next term in the sequence as given by the difference equation.
            zn[i+1] = zn[i].add((f.evaluate(zn[i]).negate()).multiply(numerator.divide(denominator)));

            // Checking if the denominator is ever equal to 0. If so, set the error as ZERO and break out of the loop.
            // I would have liked to have used denominator as defined above but the indices are shifted.
            if ((f.evaluate(zn[i+1].add((f.evaluate(zn[i])).negate()))).abs()==0){
                err = Error.ZERO;
                return;
            }
            // Checking if the sequence converges to the root.
            else if ((zn[i+1].add(zn[i].negate())).abs() < TOL && (f.evaluate(zn[i+1])).abs() < TOL ){
                // Set the root to be the corresponding term in the sequence. 
                root = zn[i+1];
                // Set the number of iterations to be i (if there are i+1 elements in the sequence):
                // An iteration takes you from the ith to the (i+1)th element of the sequence.
                // The first 2 elements are given without having to do any iterations.
                // This removes 1 iteration going from the 0th to the 1st element.
                // Therefore there are (i+1) - 0 - 1 = i iterations from the 0th to the (i+1)th element.
                numIterations = i;
                // The sequence converges so we return OK.
                err = Error.OK;
                return;
            }
            // Checking if i iterates all the way to MAXITER-1.
            else if (i==MAXITER-1){
                // Then the algorithm did not finish and we return the error DNF.
                err = Error.DNF;
                return;
            }
        }
    }
      
    // ========================================================
    // Tester function.
    // ========================================================
    
    public static void main(String[] args) {
        // Basic tester: find a root of f(z) = z^3-1.
        Complex[] coeff = new Complex[] { new Complex(-1.0,0.0), new Complex(), new Complex(), new Complex(1.0,0.0) };
        Polynomial p    = new Polynomial(coeff);
        Secant     s    = new Secant(p);
                
        s.iterate(new Complex(), new Complex(1.0,1.0));

        // Testing if we start at the root, it should return 1
        //s.iterate(new Complex(), new Complex(-0.5,0.8660254038));

        System.out.println(s.getNumIterations());   // 12
        System.out.println(s.getError());           // OK
        System.out.println(s.getRoot());
    }
}