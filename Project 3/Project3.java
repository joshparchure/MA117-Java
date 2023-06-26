/*
 * PROJECT III: Project3.java
 *
 * This file contains a template for the class Project3. None of methods are
 * implemented and they do not have placeholder return statements. Make sure 
 * you have carefully read the project formulation before starting to work 
 * on this file. You will also need to have completed the Matrix class, as 
 * well as GeneralMatrix and TriMatrix.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
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

public class Project3 {
    /**
     * Calculates the variance of the distribution defined by the determinant
     * of a random matrix. See the formulation for a detailed description.
     *
     * @param matrix      The matrix object that will be filled with random
     *                    samples.
     * @param nSamp       The number of samples to generate when calculating 
     *                    the variance. 
     * @return            The variance of the distribution.
     */
    public static double matVariance(Matrix matrix, int nSamp) {
        // You need to fill in this method.
        // Initialise some variables to be used in the variance calculation.
        double det = 0.0;
        double detSum = 0.0;
        double detSquaredSum = 0.0;
        // Iterate over all the samples.
        for (int i =0;i<nSamp;i++){
            // Populate the matrix with random entries.
            matrix.random();
            // Set the values of the variables.
            det = matrix.determinant();
            detSum = detSum + det;
            detSquaredSum = detSquaredSum + Math.pow(det,2);
        }
        // Return the variance using the formula given in the Project3 formulation instructions.
        return (detSquaredSum/nSamp - Math.pow(detSum/nSamp,2));
    }
    
    /**
     * This function should calculate the variances of matrices for matrices
     * of size 2 <= n <= 50 and print the results to the output. See the 
     * formulation for more detail.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
        GeneralMatrix general;
        TriMatrix tri;
        for (int n=2;n<51;n++){
            general = new GeneralMatrix(n,n);
            tri = new TriMatrix(n);
            System.out.println(n + " " + String.format("%.15e",matVariance(general,20000)) + " " + String.format("%.15e",matVariance(tri,200000)));
        }
    }
}