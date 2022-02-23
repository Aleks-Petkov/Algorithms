package com.williamfiset.algorithms.linearalgebra;
import static com.google.common.truth.Truth.assertThat;
import org.junit.*;

public class GaussianEliminationTest {

    @Test
    public static void test_Gauss_Elimination_solve_1(int[] array){
        double[][] augmentedMatrix = {
                {2, -3, 5, 10},
                {1, 2, -1, 18},
                {6, -1, 0, 12},
                {0, 0, 0, 0}
        };

        GaussianElimination.solve(augmentedMatrix, array);

        double x = augmentedMatrix[0][3];
        double y = augmentedMatrix[1][3];
        double z = augmentedMatrix[2][3];

        assertThat(x).isEqualTo(3.7551020408163263);
        assertThat(y).isEqualTo(10.53061224489796);
        assertThat(z).isEqualTo(6.816326530612245);
    }

    @Test
    public static void test_Gauss_Elimination_solve_2(int[] array){
        double[][] augmentedMatrix = {
                {0, 0, 1, 10},
                {0, 1, 0, 18},
                {1, 0, 0, 12}
        };

        GaussianElimination.solve(augmentedMatrix,array);

        double x = augmentedMatrix[0][3];
        double y = augmentedMatrix[1][3];
        double z = augmentedMatrix[2][3];

        assertThat(x).isEqualTo(12);
        assertThat(y).isEqualTo(18);
        assertThat(z).isEqualTo(10);
    }

    public static void main(String[] args){
        int[] array= new int[13];
        test_Gauss_Elimination_solve_1(array);
        test_Gauss_Elimination_solve_2(array);

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
