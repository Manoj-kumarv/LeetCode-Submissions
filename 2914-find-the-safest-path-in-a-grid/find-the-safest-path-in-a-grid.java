class Solution {
    private static final int[] DR = {1, -1, 0, 0};
    private static final int[] DC = {0, 0, 1, -1};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> q = new ArrayDeque<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    dist[r][c] = 0;
                    q.offer(new int[]{r, c});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n &&
                    dist[nr][nc] > dist[r][c] + 1) {

                    dist[nr][nc] = dist[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[2] - a[2]
        );

        int[][] best = new int[n][n];
        for (int[] row : best) Arrays.fill(row, -1);

        pq.offer(new int[]{0, 0, dist[0][0]});
        best[0][0] = dist[0][0];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1], safe = cur[2];

            if (r == n - 1 && c == n - 1) {
                return safe;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    int newSafe = Math.min(safe, dist[nr][nc]);
                    if (newSafe > best[nr][nc]) {
                        best[nr][nc] = newSafe;
                        pq.offer(new int[]{nr, nc, newSafe});
                    }
                }
            }
        }

        return 0;
    }
}