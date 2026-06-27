class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;
        if (freq.containsKey(1L)) {
            int c = freq.get(1L);
            ans = Math.max(ans, c % 2 == 1 ? c : c - 1);
        }

        for (long x : freq.keySet()) {
            if (x == 1) continue;

            if (freq.get(x) < 2) continue;

            long cur = x;
            int pairs = 0;

            while (freq.containsKey(cur) && freq.get(cur) >= 2) {
                long next = cur * cur;
                if (!freq.containsKey(next)) break;
                pairs++;
                cur = next;
            }

            if (pairs > 0) {
                ans = Math.max(ans, 2 * pairs + 1);
            }
        }

        return ans;
    }
}