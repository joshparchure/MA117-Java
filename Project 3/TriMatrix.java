/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
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

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diagonal;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upperDiagonal;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lowerDiagonal;
    
    /**
     * Constructor function: should initialise iDim and jDim through the Matrix
     * constructor and set up the values array.
     *
     * @param dimension  The dimension of the array.
     */
    public TriMatrix(int dimension) {
        // You need to fill in this method.
        // Initialise iDim and jDim with the given parameter (here we only look at square matrices).
        super(dimension,dimension);
        // hrow exception ifthe dimesion is non-positive.
        if (dimension<1){
            throw new MatrixException("Invalid matrix dimension");
        }
        // Create the arrays for the 3 diagonals of the matrix with the correct length.
        diagonal = new double[dimension];
        upperDiagonal = new double[dimension-1];
        lowerDiagonal = new double[dimension-1];
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
        if (i>=0 && j>=0 && i<this.iDim && j<this.iDim){
            // Checking if the entry is on the main diagonal
            if (i==j){
                return diagonal[i];
            }
            // Checking if the entry is on the upper diagonal
            else if (i == j-1){
                return upperDiagonal[i];
            }
            // Checking if the entry is on the lower diagonal
            else if (j == i-1){
                return lowerDiagonal[j];
            }
            // Checking if the entry is on none of these 3
            else{
                // All other entries of a tridiagonal matrix are 0.
                return 0.0;
            }
        }
        else{
            // Otherwise throw an exception.
            throw new MatrixException("Invalid trimatrix index");
        }
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i      The location in the first co-ordinate.
     * @param j      The location in the second co-ordinate.
     * @param value  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double value) {
        // You need to fill in this method.
        // Checking if the indices ij refer to an element in the matrix.
        if (i>=0 && j>=0 && i<this.iDim && j<this.iDim){
            // Checking if the entry is on the main diagonal
            if (i==j){
                diagonal[i] = value;
            }
            // Checking if the entry is on the upper diagonal
            else if (i == j-1){
                upperDiagonal[i] = value;
            }
            // Checking if the entry is on the lower diagonal
            else if (j == i-1){
                lowerDiagonal[j] = value;
            }
            // Checking if the entry is on none of these 3
            else{
                // Throw an exception; all other entries must be 0 and cannot be changed.
                throw new MatrixException("Invalid trimatrix index; these must all be 0");
            }
        }
        else{
            // Otherwise throw an exception.
            throw new MatrixException("Invalid trimatrix index");
        }
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // You need to fill in this method.
        // Just like in GeneralMatrix.java, the determinant is the product of 
        // the entries on the main diagonal of the matrix returned by LUdecomp().
        // This time, LUdecomp() has no parameters. This is because the sign is always +1.


        // Run the LU decomposition algorithm on the matrix given.
        // Note we don't have to check for square matrices and throw exceptions because that is coded in the LUdecomp function.
        TriMatrix LUmerged = this.LUdecomp();
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
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     * 
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix LUdecomp() {
        // You need to fill in this method.

        // We can express tridiagonal matrices as the product of a lower triangular matrix and an upper triangular matrix.
        // However, these 2 matrices are now also tridiagonal.
        // Using the notation from the instructions, we can compute matrix multiplication of L and U and equate entries with T.
        // This yields 3 difference equations:
        // d_i = d*_i + (l*_i)(u*_(i-1)) for 2<= i <=n, d_1 = d*_1
        // l_i = (l*_i)(d*_(i-1)) for 2<= i <=n
        // u_i = u*_i for 1<= i <=n-1

        // We can work out each entry row by row; first we know d*1 and u*1. Next we can use d*1 to find l*2, and then l*2 to find d*2, etc. All the us are the same.

        TriMatrix LUmerged = new TriMatrix(this.iDim);

        // Set the first d* and u* values; these are the same.
        LUmerged.setIJ(0,0,this.getIJ(0,0));
        LUmerged.setIJ(0,1,this.getIJ(0,1));
        
        // Iterate through the rows and set the 3 values.
        for(int row=1;row<this.iDim-1;row++){
            // Set the u* value.
            LUmerged.setIJ(row,row+1,this.getIJ(row,row+1));
            // Set the l*_i value using the previously calculated d*_i-1 value.
            LUmerged.setIJ(row,row-1,this.getIJ(row,row-1)/LUmerged.getIJ(row-1,row-1));
            // Set the d*_i value using the previously calculated u*_i-1 value, and the l*_i value that was just calculated.
            LUmerged.setIJ(row,row,this.getIJ(row,row)-LUmerged.getIJ(row,row-1)*LUmerged.getIJ(row-1,row));
        }

        // Setting the last l* value.
        double lFinal = this.getIJ(this.iDim-1,this.iDim-2)/LUmerged.getIJ(this.iDim-2,this.iDim-2);
        LUmerged.setIJ(this.iDim-1,this.iDim-2,lFinal);

        // Setting the last d* value.
        double dFinal = this.getIJ(this.iDim-1,this.iDim-1)-LUmerged.getIJ(this.iDim-1,this.iDim-2)*LUmerged.getIJ(this.iDim-2,this.iDim-1);
        LUmerged.setIJ(this.iDim-1,this.iDim-1,dFinal);
        
        return LUmerged;
    }

    /**
     * Add the matrix to another second matrix.
     *
     * @param second  The Matrix to add to this matrix.
     * @return        The sum of this matrix with the second matrix.
     */
    public Matrix add(Matrix second){
        // You need to fill in this method.

        // Check if the dimensions of the matrices are equal.
        if (this.iDim == second.iDim && this.jDim == second.jDim){
            // If A is a TriMatrix, then we only need to add the elements of the 3 arrays.
            if (second instanceof TriMatrix){

                // Create a new TriMatrix which will be the sum of the matrices.
                TriMatrix sumTriMatrix = new TriMatrix(this.iDim);

                // We can do the sum in O(n) time rather than O(n^2) like in GeneralMatrix.
                // We iterate over each row and set the l, d, and u terms in each row.
                // This is O(n) since there are only 3 terms in each row to compute, not n.
                // The first and last row only have 2 terms however, so we do these separately.
                for (int row=0;row<this.iDim;row++){
                    if (row==0){
                        // If we are in the first row, only define d1 and u1.
                        sumTriMatrix.setIJ(row,row,this.getIJ(row,row)+second.getIJ(row,row));
                        sumTriMatrix.setIJ(row,row+1,this.getIJ(row,row+1)+second.getIJ(row,row+1));
                    }
                    else if (row==this.iDim-1){
                        // If we are in the first row, only define d1 and l1.
                        sumTriMatrix.setIJ(row,row-1,this.getIJ(row,row-1)+second.getIJ(row,row-1));
                        sumTriMatrix.setIJ(row,row,this.getIJ(row,row)+second.getIJ(row,row));
                    }
                    else{
                        // In all other rows, define d, u, and l.
                        sumTriMatrix.setIJ(row,row-1,this.getIJ(row,row-1)+second.getIJ(row,row-1));
                        sumTriMatrix.setIJ(row,row,this.getIJ(row,row)+second.getIJ(row,row));
                        sumTriMatrix.setIJ(row,row+1,this.getIJ(row,row+1)+second.getIJ(row,row+1));
                    }
                }
                return sumTriMatrix;
            }
            else{
                // Otherwise, second is a GeneralMatrix.
                // If second is a GeneralMatrix, then add them as usual.
                // Note input validation is already implemented in the add function in GeneralMatrix.java
                return this.add(second);
            }
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

        // This code will be near identical to the multiply code in GenerealMatrix.
        // However, there are max 3 non-zero entries in each row and column of the TriMatrix.
        // This way we won't need to iterate over them entirely.
        // This should make our algorithm O(n^2) rather than O(n^3).

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

                    // Note: here is where the code differs (line 274 only).
                    // Each column and row has a maximum of 3 non-zero entries of the TriMatrix.
                    // Therefore the product matrix has a maximum of 5 terms in each row/column.
                    // Hence we only need to iterate over 5 entries, not n.
                    // The max and min functions deals with the first and last row/column, since they only have 2 terms, not 3.

                    // Iterate through the elements of row i of this matrix and column j of matrix A.
                    for(int k=Math.max(0,i-1);k<Math.min(i+4,this.jDim);k++){
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
        TriMatrix scalarProductTriMatrix = new TriMatrix(this.iDim);

        // Using the same method as in add, we iterate through the rows and set the 2/3 terms in each row.
        for (int row=0;row<this.iDim;row++){
            if (row==0){
                // If we are in the first row, only define d1 and u1.
                scalarProductTriMatrix.setIJ(row,row,this.getIJ(row,row)*scalar);
                scalarProductTriMatrix.setIJ(row,row+1,this.getIJ(row,row+1)*scalar);
            }
            else if (row==this.iDim-1){
                // If we are in the first row, only define d1 and l1.
                scalarProductTriMatrix.setIJ(row,row-1,this.getIJ(row,row-1)*scalar);
                scalarProductTriMatrix.setIJ(row,row,this.getIJ(row,row)*scalar);
            }
            else{
                // In all other rows, define d, u, and l.
                scalarProductTriMatrix.setIJ(row,row-1,this.getIJ(row,row-1)*scalar);
                scalarProductTriMatrix.setIJ(row,row,this.getIJ(row,row)*scalar);
                scalarProductTriMatrix.setIJ(row,row+1,this.getIJ(row,row+1)*scalar);
            }
        }
        return scalarProductTriMatrix;
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        // You need to fill in this method.

        // Using the same method as in add, we iterate through the rows and set the 2/3 terms in each row.
        for (int row=0;row<this.iDim;row++){
            if (row==0){
                // If we are in the first row, only define d1 and u1.
                this.setIJ(row,row,Math.random());
                this.setIJ(row,row+1,Math.random());
            }
            else if (row==this.iDim-1){
                // If we are in the first row, only define d1 and l1.
                this.setIJ(row,row-1,Math.random());
                this.setIJ(row,row,Math.random());
            }
            else{
                // In all other rows, define d, u, and l.
                this.setIJ(row,row-1,Math.random());
                this.setIJ(row,row,Math.random());
                this.setIJ(row,row+1,Math.random());
            }
        }
    }
    
    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // Test your class implementation using this method.

        // Create two 4x4 trimatrices A and B.
        Matrix A = new TriMatrix(4);
        A.setIJ(0,0,1);
        A.setIJ(0,1,-3);
        A.setIJ(1,0,4);
        A.setIJ(1,1,2);
        A.setIJ(1,2,1);
        A.setIJ(2,1,1);
        A.setIJ(2,2,7);
        A.setIJ(2,3,2);
        A.setIJ(3,2,-2);
        A.setIJ(3,3,3);
        System.out.println("A is:"+"\n"+A.toString()+"\n");

        Matrix B = new TriMatrix(4);
        B.setIJ(0,0,3);
        B.setIJ(0,1,-1);
        B.setIJ(1,0,7);
        B.setIJ(1,1,-2);
        B.setIJ(1,2,2);
        B.setIJ(2,1,4);
        B.setIJ(2,2,4);
        B.setIJ(2,3,1);
        B.setIJ(3,2,3);
        B.setIJ(3,3,6);
        System.out.println("B is:"+"\n"+B.toString()+"\n");

        // Test the getter function.
        System.out.println("The (0,0)th entry of A is: "+A.getIJ(0,0));
        System.out.println("The (1,2)th entry of B is: "+B.getIJ(1,2)+"\n");

        // Compute addition.
        System.out.println("A+B is:"+"\n"+(A.add(B)).toString()+"\n");
        // Compute scalar multiplication.
        System.out.println("5*A is:"+"\n"+(A.multiply(5)).toString()+"\n");

        // Generate a 5x5 trimatrix with random entries.
        Matrix C = new TriMatrix(5);
        C.random();
        System.out.println("C is:"+"\n"+C.toString()+"\n");

        Matrix D = new GeneralMatrix(4,5);
        D.setIJ(0,0,2);
        D.setIJ(0,1,3);
        D.setIJ(0,2,-4);
        D.setIJ(0,3,6);
        D.setIJ(0,4,7);
        D.setIJ(1,0,1);
        D.setIJ(1,1,2);
        D.setIJ(1,2,-3);
        D.setIJ(1,3,1);
        D.setIJ(1,4,-9);
        D.setIJ(2,0,8);
        D.setIJ(2,1,8);
        D.setIJ(2,2,7);
        D.setIJ(2,3,1);
        D.setIJ(2,4,-2);
        D.setIJ(3,0,-3);
        D.setIJ(3,1,4);
        D.setIJ(3,2,-7);
        D.setIJ(3,3,1);
        D.setIJ(3,4,1);
        System.out.println("D is:"+"\n"+D.toString()+"\n");

        // Compute matrix multiplication.
        System.out.println("AD is:"+"\n"+(A.multiply(D)).toString()+"\n");

        // Test the determinant (and LUdecomp) methods.
        System.out.println("The determinant of A is:" + A.determinant());
        System.out.println("The determinant of B is:" + B.determinant()+"\n");

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
        // If inner dimensions are different (C.jDim and B.iDim)
        try{
            C.multiply(B);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        System.out.println("");

        // Addition.
        // If iDim and jDim are not equal
        try{
            C.add(B);
        }
        catch (MatrixException except){
            System.out.println(except.getMessage());
        }
        System.out.println("");
    }
}