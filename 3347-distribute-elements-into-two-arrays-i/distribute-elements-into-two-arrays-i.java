class Solution {
    public int[] resultArray(int[] nums) {
        int[] nums1 = new int[nums.length];
        int[] nums2 = new int[nums.length - 1];
        int count1 = 0;
        int count2 = 0;
        nums1[0] = nums[0];
        nums2[0] = nums[1];
        for(int i = 2; i<nums.length; i++){
            if(nums1[count1] > nums2[count2]){
                count1++;
                nums1[count1] = nums[i];
            }
            else{
                count2 ++;
                nums2[count2] = nums[i];
            }
        }
        int[] result = new int[nums.length];
        System.arraycopy(nums1, 0, result , 0, count1+1);
        System.arraycopy(nums2, 0, result, count1+1 , count2+1);
        return result;
    }
}