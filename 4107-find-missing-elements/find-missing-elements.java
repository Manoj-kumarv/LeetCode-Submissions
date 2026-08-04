class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> missing = new ArrayList<>();
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[nums.length - 1];

        for(int i = min+1; i<max; i++){
           boolean containsElement = Arrays.binarySearch(nums, i) >= 0;
           if(containsElement != true){
            missing.add(i);
           }
        }
        return missing;
    }
}