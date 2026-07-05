class Solution {
    static final int MOD = 1_000_000_007;

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][] dpScore = new int[n][n];
        int[][] dpWays = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dpScore[i][j] = -1;
            }
        }

        dpScore[n - 1][n - 1] = 0;
        dpWays[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char cell = board.get(i).charAt(j);

                if (cell == 'X' || (i == n - 1 && j == n - 1))
                    continue;

                int maxScore = -1;
                int ways = 0;

                int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];
                    if (ni < n && nj < n && dpScore[ni][nj] != -1) {
                        if (dpScore[ni][nj] > maxScore) {
                            maxScore = dpScore[ni][nj];
                            ways = dpWays[ni][nj];
                        } else if (dpScore[ni][nj] == maxScore) {
                            ways = (ways + dpWays[ni][nj]) % MOD;
                        }
                    }
                }

                if (maxScore == -1) continue;

                int value = (cell >= '0' && cell <= '9') ? cell - '0' : 0;
                dpScore[i][j] = maxScore + value;
                dpWays[i][j] = ways;
            }
        }

        if (dpWays[0][0] == 0) return new int[]{0, 0};
        return new int[]{dpScore[0][0], dpWays[0][0]};
    }
}