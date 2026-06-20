class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<long[]> list = new ArrayList<>();

        // Building 1 must be 0
        list.add(new long[]{1, 0});

        // Add given restrictions
        for (int[] r : restrictions) {
            list.add(new long[]{r[0], r[1]});
        }

        // Add building n with infinite height
        list.add(new long[]{n, (long)1e18});

        // Sort by building index
        list.sort(Comparator.comparingLong(a -> a[0]));

        int m = list.size();

        // Forward pass
        for (int i = 1; i < m; i++) {
            long dist = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(
                list.get(i)[1],
                list.get(i - 1)[1] + dist
            );
        }

        // Backward pass
        for (int i = m - 2; i >= 0; i--) {
            long dist = list.get(i + 1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(
                list.get(i)[1],
                list.get(i + 1)[1] + dist
            );
        }

        // Calculate max possible height
        long ans = 0;
        for (int i = 1; i < m; i++) {
            long x1 = list.get(i - 1)[0];
            long h1 = list.get(i - 1)[1];
            long x2 = list.get(i)[0];
            long h2 = list.get(i)[1];

            long dist = x2 - x1;
            long peak = (h1 + h2 + dist) / 2;
            ans = Math.max(ans, peak);
        }

        return (int) ans;
    }
}