/**
 * An implementation of the traveling salesman problem in Java using dynamic programming to improve
 * the time complexity from O(n!) to O(n^2 * 2^n).
 *
 * <p>Time Complexity: O(n^2 * 2^n) Space Complexity: O(n * 2^n)
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.algorithms.graphtheory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// This class does not have 100% branch coverage.
// The branches that aren't covered are commented, and the rest are covered.
public class TspDynamicProgrammingIterative {

  private final int N, start;
  private final double[][] distance;
  private List<Integer> tour = new ArrayList<>();
  private double minTourCost = Double.POSITIVE_INFINITY;
  private boolean ranSolver = false;
  private int[] branchCounter;

  public TspDynamicProgrammingIterative(double[][] distance, int[] bc) {
    this(0, distance, bc);
  }

  public TspDynamicProgrammingIterative(int start, double[][] distance, int[] bc) {
    N = distance.length;
    branchCounter = bc;
    if (N <= 2) throw new IllegalStateException("N <= 2 not yet supported.");
    // BRANCH REQ: No branch currently evaluates this condition to TRUE.
    if (N != distance[0].length) throw new IllegalStateException("Matrix must be square (n x n)");
    // BRANCH REQ: No branch currently evaluates the clause (start < 0) to TRUE.
    if (start < 0 || start >= N) throw new IllegalArgumentException("Invalid start node.");
    // BRANCH REQ: No branch currently evaluates this condition to TRUE.
    if (N > 32)
      throw new IllegalArgumentException(
          "Matrix too large! A matrix that size for the DP TSP problem with a time complexity of"
              + "O(n^2*2^n) requires way too much computation for any modern home computer to handle");

    this.start = start;
    this.distance = distance;
  }

  // Returns the optimal tour for the traveling salesman problem.
  public List<Integer> getTour() {
    // BRANCH REQ: No branch currently evaluates the condition to FALSE.
    if (!ranSolver) solve();
    return tour;
  }

  // Returns the minimal tour cost.
  public double getTourCost() {
    // BRANCH REQ: No branch currently evaluates the condition to TRUE.
    if (!ranSolver) solve();
    return minTourCost;
  }

  // Solves the traveling salesman problem and caches solution.
  // Total of 22 decisions and 2 exit points for a cyclomatic complexity of 22 - 2 + 2 = 22.
  public void solve() {
    // BRANCH REQ: No branch currently evaluates the condition to TRUE.
    // All other branches after this one are covered.
    if (ranSolver) { // #1
      branchCounter[0]++;
      return;
    } else { // #2
      branchCounter[1]++;
      final int END_STATE = (1 << N) - 1;
      Double[][] memo = new Double[N][1 << N];

      // Add all outgoing edges from the starting node to memo table.
      for (int end = 0; end < N; end++) { // #3
        branchCounter[2]++;
        if (end == start) { // #4
          branchCounter[3]++;
          continue;
        } else { // #5
          branchCounter[4]++;
          memo[end][(1 << start) | (1 << end)] = distance[start][end];
        }
      }

      for (int r = 3; r <= N; r++) { // #6
        branchCounter[5]++;
        for (int subset : combinations(r, N)) { // #7
          branchCounter[6]++;
          if (notIn(start, subset)) {  // #8
            branchCounter[7]++;
            continue;
          } else { // #9
            branchCounter[8]++;
            for (int next = 0; next < N; next++) { // #10
              branchCounter[9]++;
              if (next == start || notIn(next, subset)) { // #11
                branchCounter[10]++;
                continue;
              } else { // #12
                branchCounter[11]++;
                int subsetWithoutNext = subset ^ (1 << next);
                double minDist = Double.POSITIVE_INFINITY;
                for (int end = 0; end < N; end++) { // #13
                  branchCounter[12]++;
                  if (end == start || end == next || notIn(end, subset)) { // #14
                    branchCounter[13]++;
                    continue;
                  } else { // #15
                    branchCounter[14]++;
                    double newDistance = memo[end][subsetWithoutNext] + distance[end][next];
                    if (newDistance < minDist) { // #16
                      branchCounter[15]++;
                      minDist = newDistance;
                    } else { // #17
                      branchCounter[16]++;
                    }
                  }
                }
                memo[next][subset] = minDist;
              }
            }
          }
        }
      }

      // Connect tour back to starting node and minimize cost.
      for (int i = 0; i < N; i++) { // #18
        branchCounter[17]++;
        if (i == start) { // #19
          branchCounter[18]++;
          continue;
        } else { // #20
          branchCounter[19]++;
          double tourCost = memo[i][END_STATE] + distance[i][start];
          if (tourCost < minTourCost) { // #21
            branchCounter[20]++;
            minTourCost = tourCost;
          }
        }
      }

      int lastIndex = start;
      int state = END_STATE;
      tour.add(start);

      // Reconstruct TSP path from memo table.
      for (int i = 1; i < N; i++) { // #22
        branchCounter[21]++;
        int bestIndex = -1;
        double bestDist = Double.POSITIVE_INFINITY;
        for (int j = 0; j < N; j++) { // #23
          branchCounter[22]++;
          if (j == start || notIn(j, state)) { // #24
            branchCounter[23]++;
            continue;
          } else { // #25
            branchCounter[24]++;
            double newDist = memo[j][state] + distance[j][lastIndex];
            if (newDist < bestDist) { // #26
              branchCounter[25]++;
              bestIndex = j;
              bestDist = newDist;
            } else { // #27
              branchCounter[26]++;
            }
          }
        }

        tour.add(bestIndex);
        state = state ^ (1 << bestIndex);
        lastIndex = bestIndex;
      }

      tour.add(start);
      Collections.reverse(tour);

      ranSolver = true;
    }
  }

  private static boolean notIn(int elem, int subset) {
    return ((1 << elem) & subset) == 0;
  }

  // This method generates all bit sets of size n where r bits
  // are set to one. The result is returned as a list of integer masks.
  public static List<Integer> combinations(int r, int n) {
    List<Integer> subsets = new ArrayList<>();
    combinations(0, 0, r, n, subsets);
    return subsets;
  }

  // To find all the combinations of size r we need to recurse until we have
  // selected r elements (aka r = 0), otherwise if r != 0 then we still need to select
  // an element which is found after the position of our last selected element
  private static void combinations(int set, int at, int r, int n, List<Integer> subsets) {

    // Return early if there are more elements left to select than what is available.
    int elementsLeftToPick = n - at;
    if (elementsLeftToPick < r) return;

    // We selected 'r' elements so we found a valid subset!
    if (r == 0) {
      subsets.add(set);
    } else {
      for (int i = at; i < n; i++) {
        // Try including this element
        set ^= (1 << i);

        combinations(set, i + 1, r - 1, n, subsets);

        // Backtrack and try the instance where we did not include this element
        set ^= (1 << i);
      }
    }
  }
}
