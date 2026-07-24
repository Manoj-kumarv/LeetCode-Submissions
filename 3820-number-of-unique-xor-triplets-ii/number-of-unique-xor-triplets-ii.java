class Solution {
    public int uniqueXorTriplets(int[] nums) {

        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        max <<= 1; // maximum XOR value < 2 * maxValue

        boolean[] pair = new boolean[max];

        // All possible XORs of two elements (with replacement)
        for (int a : nums) {
            for (int b : nums) {
                pair[a ^ b] = true;
            }
        }

        boolean[] ans = new boolean[max];

        // XOR every pair result with every element
        for (int x = 0; x < max; x++) {
            if (!pair[x]) continue;

            for (int c : nums) {
                ans[x ^ c] = true;
            }
        }

        int count = 0;
        for (boolean b : ans) {
            if (b) count++;
        }

        return count;
    }
}