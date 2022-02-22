package com.williamfiset.algorithms.graphtheory;
import org.junit.Test;

import java.util.Arrays;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TspDynamicProgrammingIterativeTest {

    private TspDynamicProgrammingIterative createTestSolver() {
        int n = 6;
        double[][] distanceMatrix = new double[n][n];
        for (double[] row : distanceMatrix) java.util.Arrays.fill(row, 10000);
        distanceMatrix[5][0] = 10;
        distanceMatrix[1][5] = 12;
        distanceMatrix[4][1] = 2;
        distanceMatrix[2][4] = 4;
        distanceMatrix[3][2] = 6;
        distanceMatrix[0][3] = 8;
        int startNode = 0;
        return new TspDynamicProgrammingIterative(startNode, distanceMatrix);
    }

    @Test
    public void testSmallDistanceMatrix() {
        assertThrows(IllegalStateException.class, () -> new TspDynamicProgrammingIterative(0, new double[2][2]));
    }

    @Test
    public void testNonSquareMatrix() {
        assertThrows(IllegalStateException.class, () -> new TspDynamicProgrammingIterative(0, new double[4][5]));
    }

    @Test
    public void testNegativeIndexStartNode() {
        assertThrows(IllegalArgumentException.class, () -> new TspDynamicProgrammingIterative(-3, new double[5][5]));
    }

    @Test
    public void testStartNodeIndexTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> new TspDynamicProgrammingIterative(10, new double[5][5]));
    }

    @Test
    public void testDistanceMatrixTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> new TspDynamicProgrammingIterative(new double[33][33]));
    }

    @Test
    public void testGetTourUncached() {
        TspDynamicProgrammingIterative solver = createTestSolver();
        assertThat(solver.getTour()).isEqualTo(Arrays.asList(0,3,2,4,1,5,0));
    }

    @Test
    public void testGetTourCached() {
        TspDynamicProgrammingIterative solver = createTestSolver();
        solver.solve();
        assertThat(solver.getTour()).isEqualTo(Arrays.asList(0,3,2,4,1,5,0));
    }

    @Test
    public void testGetTourCostUncached() {
        TspDynamicProgrammingIterative solver = createTestSolver();
        assertThat(solver.getTourCost()).isEqualTo(42);
    }

    @Test
    public void testGetTourCostCached() {
        TspDynamicProgrammingIterative solver = createTestSolver();
        solver.solve();
        assertThat(solver.getTourCost()).isEqualTo(42);
    }
}
