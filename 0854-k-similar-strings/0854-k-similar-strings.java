class Solution {
    int ans = Integer.MAX_VALUE;

    public int kSimilarity(String s1, String s2) {
        dfs(s1.toCharArray(), s2.toCharArray(), 0, 0);
        return ans;
    }

    void dfs(char[] s1, char[] s2, int idx, int swaps) {
        if (swaps >= ans)
            return;

        while (idx < s1.length && s1[idx] == s2[idx])
            idx++;

        if (idx == s1.length) {
            ans = Math.min(ans, swaps);
            return;
        }

        for (int j = idx + 1; j < s1.length; j++) {
            if (s1[j] == s2[idx] && s1[j] != s2[j]) {

                swap(s1, idx, j);

                dfs(s1, s2, idx + 1, swaps + 1);

                swap(s1, idx, j);
            }
        }
    }

    void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}