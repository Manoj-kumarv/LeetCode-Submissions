class Solution {
    public char processStr(String s, long k) {
        long len = 0;

        // Step 1: Calculate final length
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                len++;
            } else if (c == '*') {
                if (len > 0) len--;
            } else if (c == '#') {
                len *= 2;
            } else if (c == '%') {
                // length unchanged
            }
        }

        if (k >= len) return '.';

        // Step 2: Reverse simulate
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z') {
                if (k == len - 1) return c;
                len--;
            }
            else if (c == '*') {
                len++;
            }
            else if (c == '#') {
                len /= 2;
                if (k >= len) k -= len;
            }
            else if (c == '%') {
                k = len - 1 - k;
            }
        }

        return '.';
    }
}