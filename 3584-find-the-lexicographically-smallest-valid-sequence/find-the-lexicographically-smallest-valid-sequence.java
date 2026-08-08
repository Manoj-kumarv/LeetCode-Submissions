class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] exact = new int[n + 1];
        int[] oneChange = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            exact[i] = exact[i + 1];

            if (exact[i] < m &&
                word1.charAt(i) == word2.charAt(m - 1 - exact[i])) {
                exact[i]++;
            }

            oneChange[i] = oneChange[i + 1];

            if (oneChange[i] < m &&
                word1.charAt(i) == word2.charAt(m - 1 - oneChange[i])) {
                oneChange[i]++;
            }

            if (exact[i + 1] < m &&
                word1.charAt(i) != word2.charAt(m - 1 - exact[i + 1])) {
                oneChange[i] = Math.max(
                    oneChange[i],
                    exact[i + 1] + 1
                );
            }
        }

        int[] ans = new int[m];
        int pos = 0;
        boolean usedChange = false;

        for (int j = 0; j < m; j++) {
            boolean found = false;

            while (pos < n) {
                boolean mismatch =
                    word1.charAt(pos) != word2.charAt(j);

                if (mismatch && usedChange) {
                    pos++;
                    continue;
                }

                int remaining = m - j - 1;
                boolean possible;

                if (usedChange || mismatch) {
                    possible = exact[pos + 1] >= remaining;
                } else {
                    possible = oneChange[pos + 1] >= remaining;
                }

                if (possible) {
                    ans[j] = pos;

                    if (mismatch) {
                        usedChange = true;
                    }

                    pos++;
                    found = true;
                    break;
                }

                pos++;
            }

            if (!found) {
                return new int[0];
            }
        }

        return ans;
    }
}