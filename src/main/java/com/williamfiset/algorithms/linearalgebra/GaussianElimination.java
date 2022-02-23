/**
 * Solve a system of linear equations using Gaussian elimination. To work with this code the linear
 * equations must be specified as a matrix augmented with the constants as the right-most column.
 *
 * <p>Time Complexity: O(c*r^2)
 */
package com.williamfiset.algorithms.linearalgebra;

class GaussianElimination {

  // Define a small value of epsilon to compare double values
  static final double EPS = 0.00000001;

  // Solves a system of linear equations as an augmented matrix
  // with the rightmost column containing the constants. The answers
  // will be stored on the rightmost column after the algorithm is done.
  // NOTE: make sure your matrix is consistent and does not have multiple
  // solutions when you solve the system if you want a unique valid answer.
  // Time Complexity: O(r²c)
  // This method does not have 100% branch coverage.
  // If the branch does not have a comment, it is covered.
   static void solve(double[][] augmentedMatrix, int[] array) {
    int nRows = augmentedMatrix.length, nCols = augmentedMatrix[0].length, lead = 0;
    for (int r = 0; r < nRows; r++) {
      array[0]++;
      // BRANCH REQ: this if statement is never set to TRUE by any branch
      if (lead >= nCols) {
        array[1]++;
        break;
      } else {
        array[2]++;
      }
      int i = r;
      //BRANCH REQ: the condition for the while loop is never set to TRUE
      while (Math.abs(augmentedMatrix[i][lead]) < EPS) {
        array[3]++;
        if (++i == nRows) {
          array[4]++;
          i = r;
          if (++lead == nCols) {
            array[6]++;
            return;
            }
          else{array[7]++;}
        }
        else{array[5]++;}
      }
      double[] temp = augmentedMatrix[r];
      augmentedMatrix[r] = augmentedMatrix[i];
      augmentedMatrix[i] = temp;
      double lv = augmentedMatrix[r][lead];
      for (int j = 0; j < nCols; j++) {
        array[8]++;
        augmentedMatrix[r][j] /= lv;
      }
      for (i = 0; i < nRows; i++) {
        array[9]++;
        //BRANCH REQ: this if statement is set to both TRUE and FALSE
        if (i != r) {
          array[10]++;
          lv = augmentedMatrix[i][lead];
          for (int j = 0; j < nCols; j++) {
            array[12]++;
            augmentedMatrix[i][j] -= lv * augmentedMatrix[r][j];
          }
        }
        else{array[11]++;}
      }
      lead++;
    }
  }

  // Checks if the matrix is inconsistent
  static boolean isInconsistent(double[][] arr) {
    int nCols = arr[0].length;
    outer:
    for (int y = 0; y < arr.length; y++) {
      if (Math.abs(arr[y][nCols - 1]) > EPS) {
        for (int x = 0; x < nCols - 1; x++) if (Math.abs(arr[y][x]) > EPS) continue outer;
        return true;
      }
    }
    return false;
  }

  // Make sure your matrix is consistent as well
  static boolean hasMultipleSolutions(double[][] arr) {
    int nCols = arr[0].length, nEmptyRows = 0;
    outer:
    for (int y = 0; y < arr.length; y++) {
      for (int x = 0; x < nCols; x++) if (Math.abs(arr[y][x]) > EPS) continue outer;
      nEmptyRows++;
    }
    return nCols - 1 > arr.length - nEmptyRows;
  }

  public static void main(String[] args) {

    int[] array= new int[13];

    // Suppose we want to solve the following system for
    // the variables x, y, z:
    //
    // 2x - 3y + 5z = 10
    // x  + 2y - z  = 18
    // 6x -  y + 0  = 12
    // Then we would setup the following augment matrix:

    double[][] augmentedMatrix = {
            {2, -3, 5, 10},
            {1, 2, -1, 18},
            {6, -1, 0, 12}
    };

    solve(augmentedMatrix, array);

    if (!hasMultipleSolutions(augmentedMatrix) && !isInconsistent(augmentedMatrix)) {

      double x = augmentedMatrix[0][3];
      double y = augmentedMatrix[1][3];
      double z = augmentedMatrix[2][3];

      // x ~ 3.755, y ~ 10.531, z ~ 6.816
      System.out.printf("x = %.3f, y = %.3f, z = %.3f\n", x, y, z);
    }
    //Calculate and display the branch coverage
    int covered_branches=0;
    for(int i=0; i< array.length; i++) {
      System.out.println(array[i]);
      if(array[i]>0){
        covered_branches++;
      }
    }
    System.out.println("Covered branches: " + covered_branches);
    double percentage_covered= (covered_branches*100/13);
    System.out.println("The branch coverage is: "+ percentage_covered +"%");

  }
}
