class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        int offset = n + 1;
        int size = 2 * n + 3;
        Fenwick fenwick = new Fenwick(size);

        long result = 0;

        fenwick.add(prefix[0] + offset, 1);

        for (int j = 1; j <= n; j++) {
            int idx = prefix[j] + offset;
            result += fenwick.sum(idx - 1); // count prefix[i] < prefix[j]
            fenwick.add(idx, 1);
        }

        return result;
    }

    static class Fenwick {
        long[] tree;

        Fenwick(int n) {
            tree = new long[n + 1];
        }

        void add(int i, long v) {
            while (i < tree.length) {
                tree[i] += v;
                i += i & -i;
            }
        }

        long sum(int i) {
            long s = 0;
            while (i > 0) {
                s += tree[i];
                i -= i & -i;
            }
            return s;
        }
    }
}