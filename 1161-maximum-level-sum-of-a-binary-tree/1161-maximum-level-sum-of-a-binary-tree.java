import java.util.*;

class Solution {

    public int maxLevelSum(TreeNode root) {

        List<Integer> levelSum = new ArrayList<>();
        dfs(root, 0, levelSum);

        int max = levelSum.get(0);
        int ans = 1;

        for (int i = 1; i < levelSum.size(); i++) {
            if (levelSum.get(i) > max) {
                max = levelSum.get(i);
                ans = i + 1;
            }
        }

        return ans;
    }

    private void dfs(TreeNode root, int level, List<Integer> levelSum) {

        if (root == null)
            return;

        if (level == levelSum.size())
            levelSum.add(0);

        levelSum.set(level, levelSum.get(level) + root.val);

        dfs(root.left, level + 1, levelSum);
        dfs(root.right, level + 1, levelSum);
    }
}