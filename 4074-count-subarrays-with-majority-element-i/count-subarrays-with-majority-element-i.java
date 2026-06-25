class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // Step 1: Transform the array
        int[] prefix = new int[n + 1];
        prefix[0] = 0;
        
        boolean found = false;
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            if (nums[i] == target) found = true;
            prefix[i + 1] = prefix[i] + val;
        }
        
        // If target never appears, no valid subarray
        if (!found) return 0;
        
        // Step2: Count subarrays with positive sum
        int count = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (prefix[j] > prefix[i]) {
                    count++;
                }
            }
        }
        
        return count;
    }
}