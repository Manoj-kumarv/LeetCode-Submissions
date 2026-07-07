class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long s = 0;
        for(char c: String.valueOf(n).toCharArray()){
            if(c!='0'){
                x = x*10 + c - '0';
                s = s+c - '0';
            }
        }
        return x*s;
    }
}