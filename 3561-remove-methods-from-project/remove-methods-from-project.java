class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : invocations) {
            int a = edge[0];
            int b = edge[1];

            graph.get(a).add(b);
        }

        boolean[] suspicious = new boolean[n];

        dfs(k, graph, suspicious);

        for (int[] edge : invocations) {
            int a = edge[0];
            int b = edge[1];

            if (!suspicious[a] && suspicious[b]) {
                List<Integer> ans = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }

                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] suspicious) {

        if (suspicious[node]) {
            return;
        }

        suspicious[node] = true;

        for (int next : graph.get(node)) {
            dfs(next, graph, suspicious);
        }
    }
}
