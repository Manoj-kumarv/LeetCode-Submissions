class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] arr = s.toCharArray();
        int i = 0, n = arr.length;

        while (i < n && arr[i] == ' ') {
            i++;
        }

        int sign = 1;
        if (i < n && (arr[i] == '+' || arr[i] == '-')) {
            sign = (arr[i] == '-') ? -1 : 1;
            i++;
        }

        int result = 0;
        while (i < n && arr[i] >= '0' && arr[i] <= '9') {
            int digit = arr[i] - '0';

            if (result > Integer.MAX_VALUE / 10 ||
               (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}