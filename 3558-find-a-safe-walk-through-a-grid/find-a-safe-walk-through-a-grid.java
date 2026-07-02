class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] best = new int[m][n];
        for (int[] row : best) Arrays.fill(row, -1);

        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0) return false;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, startHealth});
        best[0][0] = startHealth;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], h = cur[2];

            if (r == m - 1 && c == n - 1) return true;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= m || nc >= n) continue;

                int newHealth = h - grid.get(nr).get(nc);
                if (newHealth <= 0) continue;

                if (newHealth > best[nr][nc]) {
                    best[nr][nc] = newHealth;
                    q.offer(new int[]{nr, nc, newHealth});
                }
            }
        }

        return false;
    }
}