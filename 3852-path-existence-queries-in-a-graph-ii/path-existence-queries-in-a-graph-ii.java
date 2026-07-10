class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;
        }

        int[] comp = new int[n];
        int cid = 0;
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i][0] - arr[i - 1][0] > maxDiff)
                cid++;
            comp[i] = cid;
        }

        int[] far = new int[n];
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r + 1 < n && arr[r + 1][0] - arr[l][0] <= maxDiff)
                r++;
            far[l] = r;
        }

        int LOG = 17;
        while ((1 << LOG) <= n)
            LOG++;

        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++)
            up[0][i] = far[i];

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {

            int a = pos[queries[qi][0]];
            int b = pos[queries[qi][1]];

            if (a == b) {
                ans[qi] = 0;
                continue;
            }

            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            if (comp[a] != comp[b]) {
                ans[qi] = -1;
                continue;
            }

            int cur = a;
            int jumps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < b) {
                    jumps += 1 << k;
                    cur = up[k][cur];
                }
            }

            ans[qi] = jumps + 1;
        }

        return ans;
    }
}