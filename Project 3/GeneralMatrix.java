/*
 * PROJECT III: GeneralMatrix.java
 *
 * This file contains a template for the class GeneralMatrix. Not all methods
 * implemented and they do not have placeholder return statements. Make sure 
 * you have carefully read the project formulation before starting to work 
 * on this file. You will also need to have completed the Matrix class.
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

import java.util.Arrays;

public class GeneralMatrix extends Matrix {
    /**
     * This instance variable stores the elements of the matrix.
     */
    private double[][] values;

    /**
     * Constructor function: should initialise iDim and jDim through the Matrix
     * constructor and set up the data array.
     *
     * @param firstDim   The first dimension of the array.
     * @param secondDim  The second dimension of the array.
     */
    public GeneralMatrix(int firstDim, int secondDim) throws MatrixException {
        // You need to fill in this method.
        // Initialise iDim and jDim with the given parameters.
        super(firstDim,secondDim);
        // Throw exception if either of the dimesions are non-positive.
        if (firstDim < 1 || secondDim < 1){
            throw new MatrixException("Invalid matrix dimensions");
        }
        // Set the values 2D array with the correct length.
        this.values = new double[firstDim][secondDim];
    }   

    /**
     * Constructor function. This is a copy constructor; it should create a
     * copy of the second matrix.
     *
     * @param second  The matrix to create a copy of.
     */
    public GeneralMatrix(GeneralMatrix second) {
        // You need to fill in this method.
        // Initialise iDim and jDim from the matrix parameter, second.
        super(second.iDim,second.jDim);
        // Set the values 2D array with the correct length.
        this.values = new double[second.iDim][second.jDim];
        // Iterate through the rows and columns of the matrix.
        for(int i = 0; i< second.iDim; i++){
            for (int j = 0; j< second.jDim; j++){
                // Set each entry equal to the corresponding entry of second using the setter function.
                this.setIJ(i,j,second.getIJ(i,j));
            }
        }
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
        // You need to fill in this method.
        // Checking if the indices ij refer to an element in the matrix.
        if (i>=0 && j>=0 && i<this.iDim && j<this.jDim){
            // Return the (i,j)th entry of the matrix.
            return this.values[i][j];
        }
        else{
            // Otherwise throw an exception.
            throw new MatrixException("Invalid matrix index"); 
        }
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the values array.
     *
     * @param i      The location in the first co-ordinate.
     * @param j      The location in the second co-ordinate.
     * @param value  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double value) {
        // You need to fill in this method.
        // Checking if the indices ij refer to an element in the matrix.
        if (i>=0 && j>=0 && i<this.iDim && j<this.jDim){
            // Set the (i,j)th entry of the matrix to the parameter value.
            this.values[i][j] = value;
        }
        else{
            // Otherwise throw an exception.
            throw new MatrixException("Invalid matrix index"); 
        }
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // You need to fill in this method.
    
        // Say the matrix we are taking the determinant of is M.
        // We can use the LU decomposition algorithm to get M = L*U.
        // L and U are lower/upper triangular matrices.
        // Note that M, L, and U are all square matrices, say with dimensions nxn.
        // Note also that det(M)=det(L*U)=det(L)*det(U).

        // The definition of determinant is the sum of the signed products of n entries of the matrix.
        // These n entries are of the form a_(i,phi(i)) for all permuations phi of {1,2,...,n}.
        // Most of these summands are 0 since the matrix is in triangular form.
        // A summand can be non-zero only if i<=phi(i) for all i for lower triangular matrices (and i>=phi(i) for upper triangular matrices.)
        // Therefore the only non-zero summand is when phi is the identity, which has sign +1.
        // So the determinant is equal to +1 * a_11 * a_22 * ... * a_nn.
        // Hence the determinant of a triangular matrix is the product of the entries on the main diagonal.

        // L has entries on the main diagonal all equal to 1. So det(L) = 1
        // Therefore det(M) = det(U), which is the product of entires on the main diagonal of U.
        // The matrix returned by LUdecomp has the same main diagonal as U.
        // So finally, the deteminant of M is the product of all entries on the main diagonal of the matrix returned by LUdecomp.


        // Run the LU decomposition algorithm on the matrix given.
        // Note we don't have to check for square matrices and throw exceptions because that is coded in the LUdecomp function.
        double[] idSign = {1};
        GeneralMatrix LUmerged = this.LUdecomp(idSign);
        // Initialise the determinant as 1 so we can multiply the entries on the main diagonal.
        double det = 1;
        // Iterate through the main diagonal.
        for(int i=0;i<this.iDim;i++){
            // Multiply the determinant by each entry on the main diagonal.
            det = det*LUmerged.getIJ(i,i);
        }
        return det;
    }

    /**
     * Add the matrix to another second matrix.
     *
     * @param second  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the second matrix.
     */
    public Matrix add(Matrix second) {
        // You need to fill in this method.
        // Check if the dimensions of the matrices are equal.
        if (this.iDim == second.iDim && this.jDim == second.jDim){
            // Create a new GeneralMatrix which will be the sum of the matrices.
            GeneralMatrix sumMatrix = new GeneralMatrix(this.iDim,this.jDim);
            // Iterate over the rows and columns of the matrix.
            for(int i=0; i<this.iDim;i++){
                for(int j =0; j<this.jDim;j++){
                    // Set each entry of the matrix as the sum of the correspoding entries of the 2 matrices being added.
                    sumMatrix.setIJ(i,j,this.getIJ(i,j)+second.getIJ(i,j));
                }
            }
            return sumMatrix;
        }
        else{
            // Otherwise throw an exception.
            throw new MatrixException("Matrices must have equal dimension to add");
        }
    }
    
    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {
        // You need to fill in this method.

        // Here we will implement an algorithm which comes directly from the definition of matrix multiplication.
        // We will iterate through the rows of the first matrix and the columns of the second matrix, as usual.
        // The first matrix is this matrix and the second matrix is A.

        // Check if the inner dimensions of the 2 matrices are equal.
        if (this.jDim == A.iDim){
            // entry will represent the (i,j)th element of the matrix product.
            double entry = 0.0;
            // Create a new GeneralMatrix which will be the product of the matrices.
            GeneralMatrix productMatrix = new GeneralMatrix(this.iDim,A.jDim);
            // Iterate through the rows of this matrix.
            for(int i=0; i<this.iDim;i++){
                // Iterate through the columns of the matrix A.
                for(int j =0; j<A.jDim;j++){
                    entry =0;
                    // Iterate through the elements of row i of this matrix and column j of matrix A.
                    for(int k=0;k<this.jDim;k++){
                        // Multiply the two corresponding elements and add all the products together.
                        entry = entry + (this.getIJ(i,k)*A.getIJ(k,j));
                    }
                    productMatrix.setIJ(i,j,entry);
                }
            }
            return productMatrix;
        }
        else{
            // Otherwise throw an exception.
            throw new MatrixException("Matrices are of incorrect dimensions for multiplication.");
        }
    }

    /**
     * Multiply the matrix by a scalar.
     *
     * @param scalar  The scalar to multiply the matrix by.
     * @return        The product of this matrix with the scalar.
     */
    public Matrix multiply(double scalar) {
        // You need to fill in this method.
        GeneralMatrix scalarProductMatrix = new GeneralMatrix(this.iDim,this.jDim);
        for(int i=0; i<this.iDim; i++){
            for(int j=0; j<this.jDim; j++){
                scalarProductMatrix.setIJ(i,j,scalar*this.getIJ(i,j));
            }
        }
        return scalarProductMatrix;
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        // You need to fill in this method.
        for(int i=0; i<this.iDim; i++){
            for(int j=0; j<this.jDim; j++){
                this.setIJ(i,j,Math.random());
            }
        }
    }

    /**
     * Returns the LU decomposition of this matrix; i.e. two matrices L and U
     * so that A = LU, where L is lower-diagonal and U is upper-diagonal.
     * 
     * On exit, decomp returns the two matrices in a single matrix by packing
     * both matrices as follows:
     *
     * [ u_11 u_12 u_13 u_14 ]
     * [ l_21 u_22 u_23 u_24 ]
     * [ l_31 l_32 u_33 u_34 ]
     * [ l_41 l_42 l_43 u_44 ]
     *
     * where u_ij are the elements of U and l_ij are the elements of l. When
     * calculating the determinant you will need to multiply by the value of
     * sign[0] calculated by the function.
     * 
     * If the matrix is singular, then the routine throws a MatrixException.
     * In this case the string from the exception's getMessage() will contain
     * "singular"
     *
     * This method is an adaptation of the one found in the book "Numerical
     * Recipies in C" (see online for more details).
     * 
     * @param sign  An array of length 1. On exit, the value contained in here
     *              will either be 1 or -1, which you can use to calculate the
     *              correct sign on the determinant.
     * @return      The LU decomposition of the matrix.
     */
    public GeneralMatrix LUdecomp(double[] sign) {
        // This method is complete. You should not even attempt to change it!!
        if (jDim != iDim)
            throw new MatrixException("Matrix is not square");
        if (sign.length != 1)
            throw new MatrixException("d should be of length 1");
        
        int           i, imax = -10, j, k; 
        double        big, dum, sum, temp;
        double[]      vv   = new double[jDim];
        GeneralMatrix a    = new GeneralMatrix(this);
        
        sign[0] = 1.0;
        
        for (i = 1; i <= jDim; i++) {
            big = 0.0;
            for (j = 1; j <= jDim; j++)
                if ((temp = Math.abs(a.values[i-1][j-1])) > big)
                    big = temp;
            if (big == 0.0)
                throw new MatrixException("Matrix is singular");
            vv[i-1] = 1.0/big;
        }
        
        for (j = 1; j <= jDim; j++) {
            for (i = 1; i < j; i++) {
                sum = a.values[i-1][j-1];
                for (k = 1; k < i; k++)
                    sum -= a.values[i-1][k-1]*a.values[k-1][j-1];
                a.values[i-1][j-1] = sum;
            }
            big = 0.0;
            for (i = j; i <= jDim; i++) {
                sum = a.values[i-1][j-1];
                for (k = 1; k < j; k++)
                    sum -= a.values[i-1][k-1]*a.values[k-1][j-1];
                a.values[i-1][j-1] = sum;
                if ((dum = vv[i-1]*Math.abs(sum)) >= big) {
                    big  = dum;
                    imax = i;
                }
            }
            if (j != imax) {
                for (k = 1; k <= jDim; k++) {
                    dum = a.values[imax-1][k-1];
                    a.values[imax-1][k-1] = a.values[j-1][k-1];
                    a.values[j-1][k-1] = dum;
                }
                sign[0] = -sign[0];
                vv[imax-1] = vv[j-1];
            }
            if (a.values[j-1][j-1] == 0.0)
                a.values[j-1][j-1] = 1.0e-20;
            if (j != jDim) {
                dum = 1.0/a.values[j-1][j-1];
                for (i = j+1; i <= jDim; i++)
                    a.values[i-1][j-1] *= dum;
            }
        }
        
        return a;
    }

    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // Test your class implementation using this method.

        // Create two 3x3 matrices A and B.
        Matrix A = new GeneralMatrix(3,3);
        A.setIJ(0,0,2);
        A.setIJ(0,1,3);
        A.setIJ(0,2,5);
        A.setIJ(1,0,-7);
        A.setIJ(1,1,1);
        A.setIJ(1,2,4);
        A.setIJ(2,0,6);
        A.setIJ(2,1,-2);
        A.setIJ(2,2,1);
        System.out.println("A is:"+"\n"+A.toString()+"\n");

        Matrix B = new GeneralMatrix(3,3);
        B.setIJ(0,0,-1);
        B.setIJ(0,1,4);
        B.setIJ(0,2,5);
        B.setIJ(1,0,2);
        B.setIJ(1,1,-2);
        B.setIJ(1,2,3);
        B.setIJ(2,0,7);
        B.setIJ(2,1,-1);
        B.setIJ(2,2,-4);
        System.out.println("B is:"+"\n"+B.toString()+"\n");

        // Creates a 2x2 matrix C.
        Matrix C = new GeneralMatrix(2,2);
        C.setIJ(0,0,1);
        C.setIJ(0,1,2);
        C.setIJ(1,0,-3);
        C.setIJ(1,1,5);
        System.out.println("C is:"+"\n"+C.toString()+"\n");

        // Creates a 3x2 matrix D.
        Matrix D = new GeneralMatrix(3,2);
        D.setIJ(0,0,3);
        D.setIJ(0,1,2);
        D.setIJ(1,0,-4);
        D.setIJ(1,1,1);
        D.setIJ(2,0,7);
        D.setIJ(2,1,3.5);
        System.out.println("D is:"+"\n"+D.toString()+"\n");

        // Creates a copy of A.
        Matrix copyA = new GeneralMatrix((GeneralMatrix)A);
        System.out.println("A is:"+"\n"+copyA.toString()+"\n");

        // Test the getter function.
        System.out.println("The (0,0)th entry of A is: "+A.getIJ(0,0));
        System.out.println("The (1,2)th entry of B is: "+B.getIJ(1,2)+"\n");

        // Test the setter function.
        C.setIJ(0,0,1.5);
        System.out.println("C is:"+"\n"+C.toString()+"\n");

        // Test the determinant method.
        System.out.println("The determinant of A is:" + A.determinant());
        System.out.println("The determinant of B is:" + B.determinant());
        System.out.println("The determinant of C is:" + C.determinant()+"\n");

        // Test adding matrices.
        System.out.println("A+B="+"\n"+A.add(B)+"\n");

        // Test multiplying matrices.
        System.out.println("AB is:"+"\n"+(A.multiply(B)).toString()+"\n");
        System.out.println("BA is:"+"\n"+(B.multiply(A)).toString()+"\n");
        System.out.println("AD is:"+"\n"+(A.multiply(D)).toString()+"\n");
        System.out.println("DC is:"+"\n"+(D.multiply(C)).toString()+"\n");

        // Test multiply matrices by scalars.
        System.out.println("5*A is:");
        System.out.println("2*B is:");
        System.out.println("-4*C is:");
        System.out.println("0.5*D is:"+"\n");

        // Test random method.
        Matrix E = new GeneralMatrix(3,3);
        E.random();
        System.out.println("E is:"+"\n"+E.toString()+"\n");

        // Testing functions when exceptions should be raised.

        // Getter.
        // If i > iDim
        try{
            A.getIJ(5,1);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        // If j > jDim
        try{
            A.getIJ(1,6);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        // If i < 0
        try{
            A.getIJ(-1,2);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        // If j < 0
        try{
            A.getIJ(2,-3);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        // Combination of the above
        try{
            A.getIJ(-5,6);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        System.out.println("");

        // Setter.
        // If i > iDim
        try{
            A.setIJ(5,1,2);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        // If j > jDim
        try{
            A.setIJ(1,6,2);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        // If i < 0
        try{
            A.setIJ(-1,2,2);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        // If j < 0
        try{
            A.setIJ(2,-3,2);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        // Combination of the above
        try{
            A.setIJ(-5,6,2);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        System.out.println("");

        // Determinant.
        // If matrix is not square
        try{
            D.determinant();
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        System.out.println("");

        // Matrix multiplication.
        // If inner dimensions are different (A.jDim and C.iDim)
        try{
            A.multiply(C);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        System.out.println("");

        // Addition.
        // If iDim and jDim are not equal
        try{
            A.add(C);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        System.out.println("");
    }
}