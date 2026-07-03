class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        int maxCost = 0;
        for (int[] e : edges) maxCost = Math.max(maxCost, e[2]);

        int lo = 0, hi = maxCost, ans = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isValid(mid, edges, online, n, k)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean isValid(int minEdge, int[][] edges, boolean[] online,
                            int n, long k) {

        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        int[] indeg = new int[n];

        for (int[] e : edges) {
            if (e[2] >= minEdge) {
                g[e[0]].add(new int[]{e[1], e[2]});
                indeg[e[1]]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) q.offer(i);
        }

        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int[] nx : g[u]) {
                if (--indeg[nx[0]] == 0) {
                    q.offer(nx[0]);
                }
            }
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == Long.MAX_VALUE) continue;
            if (!online[u] && u != 0 && u != n - 1) continue;
            if (dist[u] > k) continue;

            for (int[] nx : g[u]) {
                int v = nx[0], w = nx[1];
                if (!online[v] && v != n - 1) continue;
                dist[v] = Math.min(dist[v], dist[u] + w);
            }
        }

        return dist[n - 1] <= k;
    }
}