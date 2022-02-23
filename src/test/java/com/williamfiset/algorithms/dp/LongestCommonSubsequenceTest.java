package com.williamfiset.algorithms.dp;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.google.common.primitives.Ints;
import com.williamfiset.algorithms.utils.TestUtils;
import org.junit.*;


public class LongestCommonSubsequenceTest {

    @Test
    public void testSuccessfulLongestCommonSubsequence() {
        char[] A = {'A', 'X', 'B', 'C', 'Y', 'S'};
        char[] B = {'Z', 'A', 'Y', 'W', 'B', 'C'};

        String lcs = LongestCommonSubsequence.lcs(A, B);

        assertEquals("ABC", lcs);
    }

    @Test
    public void testSuccessfulUnequalParametersLongestCommonSubsequence() {
        char[] A = {'A'};
        char[] B = {'Z', 'A', 'Y', 'W', 'B', 'C', 'X', 'B', 'C', 'Y', 'S'};

        String lcs = LongestCommonSubsequence.lcs(A, B);

        assertEquals("A", lcs);
    }

    @Test
    public void testNullParametersLongestCommonSubsequence() {
        String test = LongestCommonSubsequence.lcs(null, null);
        assertNull(test);
    }

    @Test
    public void testEmptyParametersLongestCommonSubsequence() {
        char[] A = {'A', 'X', 'B', 'C', 'Y', 'S'};
        char[] B = {};
        String test = LongestCommonSubsequence.lcs(A, B);
        assertNull(test);
    }
}