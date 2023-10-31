// Problem: https://www.spoj.com/problems/CSUMQ/

import java.util.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] pre = new int[n];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();

            if (i == 0) {
                pre[i] = arr[i];
            } else {
                pre[i] = pre[i - 1] + arr[i];
            }
        }

        int q = scn.nextInt();
        for (int i = 1; i <= q; i++) {
            int l = scn.nextInt();
            int r = scn.nextInt();

            int res;
            if (l == 0) {
                res = pre[r];
            } else {
                res = pre[r] - pre[l - 1];
            }
            System.out.println(res);
        }

        return;
    }
}