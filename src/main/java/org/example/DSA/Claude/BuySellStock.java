package org.example.DSA.Claude;

public class BuySellStock {
/*
    Find maximum profit from stock prices.
    Pattern: Track minimum price while iterating
*/
    /*
    Time Complexity: O(n²) - nested loops
    Space Complexity: O(1)
    Approach: Try every possible buy-sell combination
    Problem: Too slow for large arrays
    */
    public int maxProfitNaive(int[] prices) {
        // Check every buy-sell combination
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }

    /*
    Time Complexity: O(n) - single pass
    Space Complexity: O(1) - constant extra space
    Pattern: Track minimum while iterating
    Key Insight: For each price, check profit if we bought at minimum price seen so far
    */
    public int maxProfitOptimized(int[] prices) {
        if(prices==null || prices.length==0){
            return 0;
        }

        int minPrice=Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int price: prices){

            minPrice = Math.min(minPrice, price);
            if(minPrice < price){
                maxProfit = Math.max(maxProfit, (price - minPrice));
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        BuySellStock sol = new BuySellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(sol.maxProfitOptimized(prices)); // 5
    }
}
