class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        
        char[] res = new char[n];
        int left = 0;
        int right = n - 1;
        
        int i = 0;
        while (i < n) {
            if (i < n - 1 && chars[i] == chars[i + 1]) {
                res[left] = chars[i];
                res[right] = chars[i + 1];
                left++;
                right--;
                i += 2;
            } else {
                res[n / 2] = chars[i];
                i++;
            }
        }
        
        return new String(res);
    }
}
