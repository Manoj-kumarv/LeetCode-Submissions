class Solution {
    private boolean[] visited;
    private int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] r : roads) {
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }

        visited = new boolean[n + 1];
        dfs(1, graph);

        return ans;
    }

    private void dfs(int node, List<int[]>[] graph) {
        visited[node] = true;

        for (int[] edge : graph[node]) {
            int next = edge[0];
            int dist = edge[1];

            ans = Math.min(ans, dist);

            if (!visited[next]) {
                dfs(next, graph);
            }
        }
    }
}