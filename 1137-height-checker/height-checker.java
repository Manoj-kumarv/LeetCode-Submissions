class Solution {
    public int heightChecker(int[] heights) {
        int[] oldheights = heights.clone();
        int count = 0;
        for(int i = heights.length - 1; i>=1; i--){
            for(int j = 0; j<=i-1; j++){
                if(heights[j] > heights[j+1]){
                int temp = heights[j+1];
                heights[j+1] = heights[j];
                heights[j] = temp;
            }
        }
        }
        for(int i = 0; i< heights.length; i++){
            if(heights[i] != oldheights[i]){
                count ++;
            }
        }
        return count;
    }
}