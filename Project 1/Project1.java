/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The results() method will perform the tasks
 *    laid out in the project formulation.
 */
 
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Project1 {
    // -----------------------------------------------------------------------
    // Do not modify the names or types of the instance variables below! This 
    // is where you will store the results generated in the Results() function.
    // -----------------------------------------------------------------------
    public int      circleCounter; // Number of non-singular circles in the file.
    public double[] aabb;          // The bounding rectangle for the first and 
                                   // last circles
    public double   Smax;          // Area of the largest circle (by area).
    public double   Smin;          // Area of the smallest circle (by area).
    public double   areaAverage;   // Average area of the circles.
    public double   areaSD;        // Standard deviation of area of the circles.
    public double   areaMedian;    // Median of the area.
    public int      stamp = 220209;
    // -----------------------------------------------------------------------
    // You should implement - but *not* change the types, names or parameters of
    // the variables and functions below.
    // -----------------------------------------------------------------------

    /**
     * Default constructor for Project1. You should leave it empty.
     */
    public Project1() {
        // This method is complete.
    }

    /**
     * Results function. It should open the file called fileName (using
     * Scanner), and from it generate the statistics outlined in the project
     * formulation. These are then placed in the instance variables above.
     *
     * @param fileName  The name of the file containing the circle data.
     */
    public void results(String fileName){
        // You need to fill in this method.
        double x, y, rad;
        circleCounter = 0;
        ArrayList<Circle> circleArrayList = new ArrayList<Circle>();
        // Setting Smin and Smax to extreme values
        // This way we can compare each circle and choose the biggest/smallest area at each stage
        this.Smin = Double.MAX_VALUE;
        this.Smax = Double.MIN_VALUE;

        try (
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))
            ) {
    
            while(scanner.hasNext()) {
      
                //Read the three values on each line of the file
                x = scanner.nextDouble();
                y = scanner.nextDouble();
                rad = scanner.nextDouble();

                if (rad>Point.GEOMTOL){
                    circleArrayList.add(new Circle(x,y,rad));
                    circleCounter++;
                    // Find max, min areas of non-singular circles.
                    // We only want to consider non-singular circles so this section of code is inside this if statement.
                    // I did not use the .area() method since we dont't have an explicit circle object, just the coordinates of the centre and the radius.
                    // There is no need to create a circle object from this data just to use the area method.
                    if (Math.PI*Math.pow(rad,2) > this.Smax) {
                        this.Smax = Math.PI*Math.pow(rad,2);
                        }
                        if (Math.PI*Math.pow(rad,2) < this.Smin) {
                        this.Smin = Math.PI*Math.pow(rad,2);
                        }
                }
            }
            Circle[] circleArray = circleArrayList.toArray(new Circle[circleArrayList.size()]);
            this.areaAverage = this.averageCircleArea(circleArray);
            this.areaSD = this.areaStandardDeviation(circleArray);
            this.areaMedian = this.median(sortAscending(returnAreaArray(circleArray)));
            this.aabb = this.calculateAABB(new Circle[]{circleArray[9],circleArray[19]});
      
            } catch(Exception e) {
                System.err.println("An error has occured. See below for details");
                e.printStackTrace();
            }
    }

    /**
     * A function to return the array of areas from the array of circles provided.
     *
     * @param circles  An array of Circles
     */

    public double[] returnAreaArray(Circle[] circles) {
        double[] areaArray = new double[circles.length];
        for(int i=0;i<circles.length;i++) {
            Array.set(areaArray,i,circles[i].area());
        }
        return areaArray;
    }

    public double[] sortAscending(double[] arrayDoubles) {
        Arrays.sort(arrayDoubles);
        return arrayDoubles;
    }

    public double median(double[] arrayAscending) {
        if (arrayAscending.length == 0){
            return 0.0;
        }
        else{
            if (arrayAscending.length % 2 ==1){
                return arrayAscending[(arrayAscending.length-1)/2];
            }
            else {
                return (arrayAscending[arrayAscending.length/2 -1])+(arrayAscending[arrayAscending.length/2])/2;
            }
        }
    }

    public static String doubleToString(double x) {
        return String.format("%.9f",x);
    }

    /**
     * A function to calculate the average area of circles in the array provided. 
     * This array may contain 0 or more circles.
     *
     * @param circles  An array of Circles
     */
    public double averageCircleArea(Circle[] circles) {
      // You need to fill in this method.
      double sumArea = 0.0;
      double mean = 0.0;
      // Checking if the array is empty
      if (circles.length ==0){
        mean = 0.0;
      }
      else{
        // Iterating through the array and adding the area at each iteration
        for(int i=0;i<circles.length;i++){
            sumArea += circles[i].area();
            }
        mean = sumArea/circles.length;
        }
      return mean;
    }
    
    /**
     * A function to calculate the standard deviation of areas in the circles in the array provided. 
     * This array may contain 0 or more circles.
     * 
     * @param circles  An array of Circles
     */
    public double areaStandardDeviation(Circle[] circles) {
      //You need to complete this method.
      double areaMean = averageCircleArea(circles);
      double sd = 0.0;
      double squaredWeightedSum = 0.0;
      for(int i=0;i<circles.length;i++){
        squaredWeightedSum += Math.pow(circles[i].area()-areaMean,2);
      }
      // Calculates sd if the array is not empty; otherwise return inital value of 0.0
      if(circles.length !=0){
        sd = Math.pow((squaredWeightedSum)/circles.length,0.5);
      }
    return sd;
    }
    
    /**
     * Returns 4 values in an array [X1,Y1,X2,Y2] that define the rectangle
     * that surrounds the array of circles given.
     * This array may contain 0 or more circles.
     *
     * @param circles  An array of Circles
     * @return An array of doubles [X1,Y1,X2,Y2] that define the bounding rectangle with
     *         the origin (bottom left) at [X1,Y1] and opposite corner (top right)
     *         at [X2,Y2]
     */
    public double[] calculateAABB(Circle[] circles) {
        // You need to fill in this method.
        // Setting extreme values for the corresponding values for the top right and bottom left coordinates
        // This way we can compare each circle at each point and update the values at each stage
        double northMax = Double.MIN_VALUE;
        double eastMax = Double.MIN_VALUE;
        double southMin = Double.MAX_VALUE;
        double westMin = Double.MAX_VALUE;
        double r = 0.0;
        double x = 0.0;
        double y = 0.0;
        if (circles.length ==0){
            return new double[]{0.0,0.0,0.0,0.0};
        }
        else{
            // Comparing and updating values for each circle in the array
            for(int i=0; i<circles.length;i++){
                r = circles[i].getRadius();
                x = (circles[i].getCentre()).getX();
                y = (circles[i].getCentre()).getY();
                if (y+r>northMax){
                    northMax = y+r;
                }
                if (x+r>eastMax){
                    eastMax = x+r;
                }
                if (y-r<southMin){
                    southMin = y-r;
                }
                if (x-r<westMin){
                    westMin = x-r;
                }
            }
            return new double[]{westMin,southMin,eastMax,northMax};
        }
    }

  
    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main(String args[]){
        // You can use this method for testing.
        Project1 project1 = new Project1();
        project1.results("student.data");

        // Printing values of each variable and rounding to 9 decimal places
        System.out.println("\n"+"The number of non-singular circles is "+project1.circleCounter+"\n");

        System.out.println("The maximum area of non-singular circles is "+project1.Smax);
        System.out.println("The maximum area of non-singular circles to 9 dp is "+doubleToString(project1.Smax)+"\n");
        System.out.println("The minimum area of non-singular circles is "+project1.Smin);
        System.out.println("The minimum area of non-singular circles to 9 dp is "+doubleToString(project1.Smin)+"\n");

        System.out.println("The average area of non-singular circles is "+project1.areaAverage);
        System.out.println("The average area of non-singular circles to 9dp is "+doubleToString(project1.areaAverage)+"\n");

        System.out.println("The standard deviation of non-singular circles is "+project1.areaSD);
        System.out.println("The standard deviation of non-singular circles to 9dp is "+doubleToString(project1.areaSD)+"\n");

        System.out.println("The median area of non-singular circles is "+project1.areaMedian);
        System.out.println("The median area of non-singular circles to 9 dp is "+doubleToString(project1.areaMedian)+"\n");

        System.out.println("The defining coordinates of the axis-aligned bounding rectangle are:");
        System.out.println("("+project1.aabb[0]+","+project1.aabb[1]+")");
        System.out.println("("+project1.aabb[2]+","+project1.aabb[3]+")"+"\n");

        System.out.println("The defining coordinates of the axis-aligned bounding rectangle to 9dp are:");
        System.out.println("("+doubleToString(project1.aabb[0])+","+doubleToString(project1.aabb[1])+")");
        System.out.println("("+doubleToString(project1.aabb[2])+","+doubleToString(project1.aabb[3])+")");
    }
}