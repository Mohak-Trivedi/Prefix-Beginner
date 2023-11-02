// Problem: https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1?utm_source=geeksforgeeks&utm_medium=article_practice_tab&utm_campaign=article_practice_tab

//{ Driver Code Starts
import java.util.*;

class MaxLenZeroSumSub {

    // Returns length of the maximum length subarray with 0 sum

    // Drive method
    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T > 0) {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();

            GfG g = new GfG();
            System.out.println(g.maxLen(arr, n));
            T--;
        }
    }
}
// } Driver Code Ends

// Brute Force Approach: TC: O(N^2), SC: O(1)
// For each sub-array, check if sum 0. If yes, check and update maxLen. Finally,
// return maxLen after checking all possible sub-arrays.

// Optimized Approach: Using Prefix Sum and HashMap (PrefixSum ->
// FirstOccurrenceIndex): O(2N), SC: O(2N)
// For every element in the Prefix Sum array:
// if(element == 0) -> sum[0, i] = 0 -> len = i+1, also store in sumFirstOcc if
// not present already.
// else if(element exists in sumFirstOcc) -> len = element's index - first
// occurence
// else -> first occurence -> store in sumFirstOcc

// Logic behind else if (and HashMap (PrefixSum -> FirstOccurrenceIndex)):
// We know, prefixSum[r] = prefixSum[l - 1] + sum[l, r]
// sum[l, r] = prefixSum[r] - prefixSum[l - 1]
// in order for sum[l, r] sub-array to be 0 -> prefixSum[r] = prefixSum[l - 1]
// Hence, if prefixSum[r] = prefixSum[l - 1] -> sum[l, r] = 0. So we find
// sub-array from index l to r whose elements' sum is 0.
// But, we want to find longest of such sub-array. We are traversing from left
// to right, so it is better to maintain the l to be the left-most index i.e.
// first occurence of that sum.
class GfG {
    int maxLen(int arr[], int n) {
        // Your code here
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        Map<Integer, Integer> sumFirstOcc = new HashMap<>();

        int len = 0;
        int maxLen = 0;
        for (int i = 0; i < prefixSum.length; i++) {
            if (prefixSum[i] == 0) {
                len = i + 1;
                maxLen = Math.max(maxLen, len);

                if (!sumFirstOcc.containsKey(prefixSum[i])) {
                    sumFirstOcc.put(prefixSum[i], i);
                }
            } else if (sumFirstOcc.containsKey(prefixSum[i])) {
                int LMinusOne = sumFirstOcc.get(prefixSum[i]);
                len = i - LMinusOne;
                maxLen = Math.max(maxLen, len);
            } else {
                sumFirstOcc.put(prefixSum[i], i);
            }
        }

        return maxLen;
    }
}