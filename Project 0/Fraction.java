/*
 * FRACTION
 *
 * This file is a SKELETON file. That means that a number of functions have
 * not been implemented. Your task is to complete the class by filling in the
 * missing methods.
 * 
 * Tasks:
 * 
 * 1) First an important note. This file contains names of public methods
 *    which you should NOT change. Each method is already "preprogrammed" so
 *    the interface (the functions, their parameters and what should be
 *    returned) is fixed and you should NOT change it. Every function has a
 *    description, which outlines:
 *
 *    - The purpose of the function, and intended use;
 *    - The parameters it accepts (if any) indicated by @param;
 *    - What it returns (if anything) indicated by @return.
 *
 * 2) CAREFULLY read through the class definition.
 *
 * 3) Define the methods that are not implemented. They are indicated by a
 *    comment reading "Fill in this method", but for reference, they are:
 * 
 *    - The Fraction(m,n) constructor.
 *    - Convertors: toDouble(), toString()
 *    - Operations with fractions: add(), divide() and multiply()
 * 
 * 4) Add to the class two methods, which are private and accept no
 * parameters:
 *      
 *    - gcd():    computes and returns the greatest common divisor of the
 *                numerator and denominator for this fraction.
 *    - reduce(): uses gcd() to reduce the fraction to irreducible form. e.g.
 *                a fraction 10/20 is simplified to 1/2.
 *
 * 5) The BOSS system will perform specific tests with your class. It will
 *    test: constructors, operators and simplification to irreducible form. So
 *    even if you do not complete all of the methods you will obtain some
 *    credit if others are completed satisfactorily.
 *
 * 6) Finally, fill in the following fields:
 *
 * NAME: Ameya Parchure (JOSH)
 * UNIVERSITY ID: u2100098
 * DEPARTMENT: Maths
 */

/**
 * Classname: Fraction
 * Description: This class implements a new type for fractions
 *              and corresponding arithmetic.
 * 
 * @author : Original: K.N. King, modified by D. Moxey and P. Plechac
 *           for use in the course MA117
 * @version: history: v1.1
 */

import java.lang.Math;

public class Fraction {
    // ============================================================
    // Instance variables
    // ============================================================

    /**
     * The numerator of the fraction.
     */
    private int numerator;
    /**
     * The denominator of the fraction.
     */
    private int denominator;
    
    // ============================================================
    // Constructors
    // ============================================================

    /**
     * Constructor which takes coefficients explicity.
     * 
     * Behaviour: Constructs a fraction with the specified numerator and
     *            denominator. Remember that your fraction should *always* be
     *            stored in irreducible form.
     *
     * @param num   The numerator
     * @param denom The denominator
     */
    public Fraction(int num, int denom) {
        // Fill in this method
        this.numerator = num;
        this.denominator = denom;
        reduce();
        }
    //}

    /**
     * Constructor which takes coefficients explicity.
     *
     * Behaviour: Constructs a fraction which represents an integer: set the
     *            specified numerator and set denominator to 1.
     *
     * @param num The numerator
     */
    public Fraction(int num) {
        // This method is complete.
        this(num, 1);
    }

    /**
     * Default constructor.
     *
     * Behaviour: Constructs a fraction and set the default value to 0;
     *            i.e. numerator = 0 and denominator = 1
     */
    public Fraction() {
        this(0, 1);
    }
    
    
    // ==============================================================
    // Convertors
    //
    // These functions will convert the Fraction to other data types.
    // ==============================================================

    /**
     * Convert the fraction to the floating point representation using double
     * precision.
     * 
     * Behaviour: Converts this fraction into a double.
     *
     * @return    A double value obtained by dividing the fraction's numerator
     *            by its denominator.
     */
    public double toDouble() {
        // Fill in this method to calculate the correct value to return.
        // J- completed. copied from toFloat, changed type
        return (double) numerator / denominator;
    }


    /**
     * Convert the fraction to the floating point representation using the
     * single precision.
     *
     * Behaviour: Converts this fraction into a float value.
     *
     * @return    A float value obtained by dividing the fraction's numerator by
     *            its denominator
     */
    public float toFloat() {
        // This method is complete.
        return (float) numerator / denominator;
    }

    /**
     * Convert the fraction to a string.
     * 
     * Behaviour: Converts this fraction into a string
     *
     * @return    A string of the form "num/denom". If the denominator is 1,
     *            returns a string containing *only* the numerator.
     */
    public String toString() {
        // Fill in this method to format the correct string to return.
        // J- completed. used simple if statement.
        if (denominator == 1){
            return Integer.toString(numerator);
        }
        else{
            return(Integer.toString(numerator) + '/' + Integer.toString(denominator));
        }
    }
    
    
    // ============================================================
    // Accessors and mutator methods (getters and setters)
    // ============================================================

    /**
     * Get denominator.
     *
     * Behaviour: Returns the denominator of this fraction.
     *
     * @return    The denominator of this fraction.
     */
    public int getDenominator() {
        // This method is complete.
        return denominator;
    }

    /**
     * Get numerator.
     *
     * Behaviour: Returns the numerator of this fraction.
     *
     * @return    The numerator of this fraction.
     */
    public int getNumerator() {
        // This method is complete.
        return numerator;
    }

    //============================================================
    // Operations with fractions - Core methods
    //============================================================

    /**
     * Addition operation.
     *
     * Behaviour: Adds this fraction to a supplied fraction.
     *
     * @param f  The fraction to be added.
     * @return   The sum of this fraction and f.
     */
    public Fraction add(Fraction f) {
        // Fill in this method to calculate the correct Fraction to return.
        // J- completed. copied from subtract, changed sign. added params to return.
        int num   = numerator * f.denominator + f.numerator * denominator;
        int denom = denominator * f.denominator;

        return new Fraction(num,denom);
    }

    /**
     * Subtraction operation.
     * 
     * Behaviour: Subtracts a fraction from this fraction.
     *
     * @param f   The fraction to be subtracted.
     * @return    The difference between this fraction and f.
     *
     */
    public Fraction subtract(Fraction f) {
        // This method is complete.
        int num   = numerator * f.denominator - f.numerator * denominator;
        int denom = denominator * f.denominator;
        
        return new Fraction(num, denom);
    }

    /**
     * Division.
     *
     * Behaviour: Divides this fraction by another fraction.
     * 
     * @param f   The fraction to be used as a divisor.
     * @return    The quotient of this fraction and f.
     */
    public Fraction divide(Fraction f) {
        // Fill in this method to calculate the correct Fraction to return.
        // J- completed. copied from multiply, did reciprocal. added params to return.
        int num   = numerator * f.denominator;
        int denom = denominator * f.numerator;

        return new Fraction(num,denom);
    }

    /**
     * Multiplication.
     * 
     * Behaviour: Multiplies this fraction and another fraction.
     *
     * @param f   The fraction to be multiplied.
     * @return    The product of this fraction and f.
     */
    public Fraction multiply(Fraction f) {
        // Fill in this method to calculate the correct Fraction to return.
        // J- completed. copied from subtract, made numerator the product as well. added params to return.
        int num   = numerator * f.numerator;
        int denom = denominator * f.denominator;

        return new Fraction(num, denom);
    }

    private void reduce(){
        int gcd = gcd();
        numerator = numerator/gcd;
        denominator = denominator/gcd;
    }

    private int gcd(){
        int num = Math.abs(this.numerator);
        int denom = Math.abs(this.denominator);

        if (num==0 && denom ==0){
            return 0;
        }
        else if (num ==0 && denom !=0){
            return (int) denom;
        }
        else if (num!=0 && denom==0){
            return 1;
        }
        else if (num>denom){
            int num1 = num;
            int num2 = denom;
            while (num2 != 0) {
                int modulus = num1 % num2;
                num1 = num2;
                num2 = modulus;
            }
            return (int) num1;
        }
        else if (denom>num){
            int num1 = denom;
            int num2 = num;
            while (num2 != 0) {
                int modulus = num1 % num2;
                num1 = num2;
                num2 = modulus;
            }
            return (int) num1;
        }
        else{
            // numerator != denominator != 0
            return num;
        }
    }
    
    // ======================================================================
    // END OF USER MODIFIABLE CODE
    //
    // DO NOT CHANGE ANY FUNCTIONS IN THE SECTION BELOW; THEY ARE NEEDED FOR 
    // THE AUTOMATED MARKING SYSTEM. YOUR CODE CANNOT BE MARKED WITHOUT IT!
    // ======================================================================

    /**
     * Compare two fractions.
     *
     * @param  q  The fraction to compare with.
     * @return    true if q is Fraction equal to this fraction .
     */
    public boolean sameAs(Fraction q) {
        return (numerator   == q.getNumerator() &&
                denominator == q.getDenominator());
    }
}