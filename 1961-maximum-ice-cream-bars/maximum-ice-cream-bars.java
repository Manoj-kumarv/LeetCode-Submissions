class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // Step 1: Find maximum cost
        int maxCost = 0;
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        // Step 2: Counting sort array
        int[] count = new int[maxCost + 1];
        for (int cost : costs) {
            count[cost]++;
        }

        // Step 3: Buy ice creams starting from cheapest
        int iceCreams = 0;
        for (int cost = 1; cost <= maxCost; cost++) {
            if (count[cost] == 0) continue;

            int canBuy = Math.min(count[cost], coins / cost);
            iceCreams += canBuy;
            coins -= canBuy * cost;

            if (coins < cost) break;
        }

        return iceCreams;
    }
}