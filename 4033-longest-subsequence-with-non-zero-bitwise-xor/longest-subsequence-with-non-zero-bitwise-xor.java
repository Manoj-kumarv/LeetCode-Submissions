class Solution {
    public int longestSubsequence(int[] nums) {
        int length = nums.length;
        int xor = 0;
        for(int num : nums){
            xor ^= num;
        }
        if(xor > 0){
            return length;
        }
        for(int num : nums){
            if(num > 0){
                return length -1;
            }
        }
        return 0;
    }
}