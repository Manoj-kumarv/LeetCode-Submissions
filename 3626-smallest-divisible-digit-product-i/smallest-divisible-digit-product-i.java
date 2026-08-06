class Solution {
    public int smallestNumber(int n, int t) {
        for(int i = n; i<= 100; i++){
            if(i <= 9){
                if( i % t == 0){
                    return i;
                }
            }
            else{
                int digit1 = i/10;
                int digit2 = i%10;
                int digit3 = digit1*digit2;
                if(digit3 % t == 0){
                    return i;
                }

            }
        }
        return 0;
    }
}