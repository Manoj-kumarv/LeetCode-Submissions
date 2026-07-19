class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        boolean[] visited = new boolean[26];
        StringBuilder stack = new StringBuilder();

        for (char c : s.toCharArray()) {
            freq[c - 'a']--;

            if (visited[c - 'a']) {
                continue;
            }

            while (stack.length() > 0) {
                char top = stack.charAt(stack.length() - 1);

                if (top > c && freq[top - 'a'] > 0) {
                    visited[top - 'a'] = false;
                    stack.deleteCharAt(stack.length() - 1);
                } else {
                    break;
                }
            }

            stack.append(c);
            visited[c - 'a'] = true;
        }

        return stack.toString();
    }
}