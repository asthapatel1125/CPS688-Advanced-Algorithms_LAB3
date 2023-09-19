/*
 * The goal of this problem is to determine the maximum revenue that can be obtained by 
 * cutting a rod of length n into pieces of various lengths and selling those pieces at different prices.
 * */

public class RodCutting {
    public static int maxRevenue(int[] p, int n) { //p:prices for different lengths, n:representing the length of rod
        int[] r = new int[n+1]; //stores max rev for diff length rods 
        r[0] = 0;

        for (int i = 1; i <= n; i++) {
            int maxRevenue = Integer.MIN_VALUE;//smallest integer that can be stored in this field
            for (int j = 1; j <= i; j++) {
                //looping through all possible cuts of the rod and calculating the maximum revenue that can
            	//be obtained from each cut
                maxRevenue = Math.max(maxRevenue, p[j-1] + r[i-j]);
            }
            r[i] = maxRevenue;
        }

        return r[n];
    }

    public static void main(String[] args) {
        int[] p = {1, 5, 9,10,17,17,20};
        int n = 7;
        int maxRevenue = maxRevenue(p, n);
        System.out.println("Maximum revenue for a rod of length " + n + ": " + maxRevenue);
    }
}
