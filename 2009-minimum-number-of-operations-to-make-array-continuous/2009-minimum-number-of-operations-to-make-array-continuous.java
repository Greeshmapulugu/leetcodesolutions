import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (list.isEmpty() || list.get(list.size() - 1) != num) {
                list.add(num);
            }
        }

        int ans = n;
        int right = 0;

        for (int left = 0; left < list.size(); left++) {

            while (right < list.size() && list.get(right) < list.get(left) + n) {
                right++;
            }

            int windowSize = right - left;
            ans = Math.min(ans, n - windowSize);
        }

        return ans;
    }
}