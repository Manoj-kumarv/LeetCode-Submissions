class Solution {

    static final long LIMIT = 1_000_001;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        String mid = "";

        int len = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];

            if ((freq[i] & 1) == 1)
                mid = "" + (char) ('a' + i);
        }

        if (countWays(half) < k)
            return "";

        StringBuilder left = new StringBuilder();

        while (left.length() < len) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + mid + right;
    }

    private long countWays(int[] cnt) {

        int total = 0;
        for (int x : cnt)
            total += x;

        long ans = 1;

        int rem = total;

        for (int f : cnt) {

            if (f == 0)
                continue;

            ans *= nCr(rem, f);

            if (ans > LIMIT)
                return LIMIT;

            rem -= f;
        }

        return ans;
    }

    private long nCr(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {

            ans = ans * (n - r + i) / i;

            if (ans > LIMIT)
                return LIMIT;
        }

        return ans;
    }
}