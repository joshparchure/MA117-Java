/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
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

public class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
        // You need to fill in this function.
        // Note the 0 based indexing of arrays matches up with the powers of the polynomial (starting with contant term in z^0)
        
        // Initialise the degree of the polynomial as the length of the array coeff.
        int deg = coeff.length;
        
        // Reduce the degree by 1 if the last term of the array coeff is 0.
        // Break from the loop if the last term of the array coeff is not 0 (keep degree unchanged).
        for (int i = coeff.length-1; i>=0; i--){
            if (coeff[i].getReal()==0 && coeff[i].getImag()==0){
                deg -=1;
            }
            else{
                break;
            }
        }

        // Initialise a copy of the array with length equal to degree.
        // We are creating a copy so that if the user changes the array coeff, these values won't change.
        this.coeff = new Complex[deg];
        // Set each element of the array to the same as the element of the original array.
        for (int i =0; i<deg; i++){
            this.coeff[i] = coeff[i];
        }
    }
    
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        // You need to fill in this function.
        this.coeff = new Complex[]{new Complex()};
    }

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Return the coefficients array.
     *
     * @return  The coefficients array.
     */ 
    public Complex[] getCoeff() {
        // You need to fill in this method with the correct code.
        // Complex[] coeffs = new Complex[this.coeff.length];
        // for(int i =0; i< this.coeff.length;i++){
        //     coeffs[i] = this.coeff[i];
        // }
        // return coeffs;
        return this.coeff;
        // // return new Complex[]{};
    }

    /**
     * Create a string representation of the polynomial.
     * Use z to represent the variable.  Include terms
     * with zero co-efficients up to the degree of the
     * polynomial.
     *
     * For example: (-5.000+5.000i) + (2.000-2.000i)z + (-1.000+0.000i)z^2
     */
    public String toString() {
        // You need to fill in this method with the correct code.
        String polyString = "";
        String temp = "";
        for (int i =0; i< this.coeff.length;i++){
            // Formating each term to be concatenated.
            // Choosing the correct sign depending on the sign of the imaginary part.
            if (this.coeff[i].getImag()>=0){
                temp = "(" + this.coeff[i].getReal() + "+" + this.coeff[i].getImag() + "i)";
            }
            else{
                temp = "(" + this.coeff[i].getReal() + this.coeff[i].getImag() + "i)";
            }
            
            // Concatenating and formatting each term correctly.
            if (i==0){
                // Here we do NOT print z^0.
                polyString = polyString  + temp;
            }
            else if (i==1){
                // Here we do NOT print z^1, just z.
                polyString = polyString + " + " + temp +"z";
            }
            else{
                // Here we do print z^i.
                polyString = polyString + " + " + temp + "z^" +i;
            }
        }
        return polyString;
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        // You need to fill in this method with the correct code.
        if (coeff.length == 0){
            return 0;
        }
        else{
            return (coeff.length - 1);
        }
        // return (coeff.length - 1);
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
        // You need to fill in this method with the correct code.

        // We will use the algorithm given in the PDF which avoids using powers of z.
        // This means P(z) = a0 + z(a1+z(a2+z(a3+...))).

        // Initialise the value of the function as the value of the last term of the polynomial at z.
        Complex fofz = new Complex(this.coeff[(this.coeff.length)-1].getReal(),this.coeff[(this.coeff.length)-1].getImag());
        // Start from the penultimate coefficient and run backwards.
        for (int i = coeff.length-2; i>=0; i--){
            // Updates the value of fofz at each stage of the algorithm.
            fofz = (fofz.multiply(z)).add(this.coeff[i]);
        }
        return fofz;
    }

    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // You can fill in this function with your own testing code.
        Complex[] coeff1 = new Complex[] {new Complex(1.0,1.0), new Complex(0.0,1.0), new Complex(), new Complex(1,1), new Complex(0,0), new Complex(0,0), new Complex(2,2), new Complex(0,0)};
        Complex[] coeff2 = new Complex[] {new Complex(), new Complex(2.0,1.0), new Complex(), new Complex(3,-4), new Complex(0,0)};
        Complex[] coeff3 = new Complex[] {new Complex()};
        Polynomial p = new Polynomial(coeff1);
        Polynomial q = new Polynomial(coeff2);
        Polynomial r = new Polynomial(coeff3);
        System.out.println("");
        System.out.println("Polynomial p(z)="+p.toString());
        System.out.println("Polynomial p has degree "+p.degree());
        System.out.println(p.getCoeff()[0]+"\n");
        System.out.println("Polynomial q(z)="+q.toString());
        System.out.println("Polynomial q has degree "+q.degree());
        System.out.println(q.getCoeff()[1]+"\n");
        System.out.println(p.evaluate(new Complex(-1,2)));
        System.out.println("");
        System.out.println("Polynomial r has degree "+r.degree());
    }
}