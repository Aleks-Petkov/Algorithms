/**
 * This file contains an implementation of finding the Longest Common Subsequence (LCS) between two
 * strings using dynamic programming.
 *
 * <p>Time Complexity: O(nm)
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.algorithms.dp;

import static com.williamfiset.algorithms.geometry.LineSegmentLineSegmentIntersection.printCoverage;

public class LongestCommonSubsequence {

  // Returns a non unique Longest Common Subsequence
  // between the strings str1 and str2 in O(nm)
  public static String lcs(char[] A, char[] B, int[] bc) {

    if (A == null || B == null) {
      bc[0]++;
      return null;
    } else {
      bc[1]++;
    }

    final int n = A.length;
    final int m = B.length;

    if (n == 0 || m == 0) {
      bc[2]++;
      return null;
    } else {
      bc[3]++;
    }

    int[][] dp = new int[n + 1][m + 1];

    // Suppose A = a1a2..an-1an and B = b1b2..bn-1bn
    for (int i = 1; i <= n; i++) {
      bc[4]++;
      for (int j = 1; j <= m; j++) {
        bc[5]++;
        // If ends match the LCS(a1a2..an-1an, b1b2..bn-1bn) = LCS(a1a2..an-1, b1b2..bn-1) + 1
        if (A[i - 1] == B[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
          bc[6]++;
        }

        // If the ends do not match the LCS of a1a2..an-1an and b1b2..bn-1bn is
        // max( LCS(a1a2..an-1, b1b2..bn-1bn), LCS(a1a2..an-1an, b1b2..bn-1) )
        else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
          bc[7]++;
        }
      }
    }

    int lcsLen = dp[n][m];
    char[] lcs = new char[lcsLen];
    int index = 0;

    // Backtrack to find a LCS. We search for the cells
    // where we included an element which are those with
    // dp[i][j] != dp[i-1][j] and dp[i][j] != dp[i][j-1])
    int i = n, j = m;
    while (i >= 1 && j >= 1) {
      bc[8]++;
      int v = dp[i][j];

      // The order of these may output different LCSs
      while (i > 1 && dp[i - 1][j] == v) {
        i--;
        bc[9]++;
      }

      while (j > 1 && dp[i][j - 1] == v) {
        j--;
        bc[10]++;
      }

      // Make sure there is a match before adding
      if (v > 0) {
        lcs[lcsLen - index++ - 1] = A[i - 1]; // or B[j-1];
        bc[11]++;
      } else {
        bc[12]++;
      }

      i--;
      j--;
    }

    return new String(lcs, 0, lcsLen);
  }

  public static void main(String[] args) {
    int[] bc = new int[15]; //Branch coverage array containing number of branch hits for each branch

    char[] A = {'A', 'X', 'B', 'C', 'Y'};
    char[] B = {'Z', 'A', 'Y', 'W', 'B', 'C'};
    System.out.println(lcs(A, B, bc)); // ABC

    A = new char[] {'3', '9', '8', '3', '9', '7', '9', '7', '0'};
    B = new char[] {'3', '3', '9', '9', '9', '1', '7', '2', '0', '6'};
    System.out.println(lcs(A, B, bc)); // 339970

    printCoverage(bc);
  }
}
