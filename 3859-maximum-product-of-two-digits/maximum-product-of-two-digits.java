class Solution {
    public int maxProduct(int n) {
        int[] arr = new int[10];
        int i = 0;
        while(n>0){
            int rem = n%10;
            n = n/10;
            arr[i] = rem;
            i++;
        }
        Arrays.sort(arr);
        int product = arr[arr.length -1] * arr[arr.length -2];
        return product;

    }
}