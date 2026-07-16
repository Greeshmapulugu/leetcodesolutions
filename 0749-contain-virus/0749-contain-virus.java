import java.util.*;

class Solution {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int containVirus(int[][] isInfected) {
        int m = isInfected.length;
        int n = isInfected[0].length;
        int totalWalls = 0;

        while (true) {
            boolean[][] visited = new boolean[m][n];

            List<List<int[]>> regions = new ArrayList<>();
            List<Set<Integer>> frontiers = new ArrayList<>();
            List<Integer> wallsNeeded = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 1 && !visited[i][j]) {
                        List<int[]> region = new ArrayList<>();
                        Set<Integer> frontier = new HashSet<>();
                        int[] walls = new int[1];

                        dfs(isInfected, i, j, visited, region, frontier, walls);

                        regions.add(region);
                        frontiers.add(frontier);
                        wallsNeeded.add(walls[0]);
                    }
                }
            }

            if (regions.isEmpty())
                break;

            int idx = -1;
            int maxThreat = 0;

            for (int i = 0; i < frontiers.size(); i++) {
                if (frontiers.get(i).size() > maxThreat) {
                    maxThreat = frontiers.get(i).size();
                    idx = i;
                }
            }
            if (maxThreat == 0)
                break;

            totalWalls += wallsNeeded.get(idx);

            for (int[] cell : regions.get(idx)) {
                isInfected[cell[0]][cell[1]] = -1;
            }
            for (int i = 0; i < regions.size(); i++) {
                if (i == idx)
                    continue;

                for (int code : frontiers.get(i)) {
                    int r = code / n;
                    int c = code % n;
                    isInfected[r][c] = 1;
                }
            }
        }

        return totalWalls;
    }

    private void dfs(int[][] grid, int r, int c,
                     boolean[][] visited,
                     List<int[]> region,
                     Set<Integer> frontier,
                     int[] walls) {

        int m = grid.length;
        int n = grid[0].length;

        visited[r][c] = true;
        region.add(new int[]{r, c});

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                continue;

            if (grid[nr][nc] == 1 && !visited[nr][nc]) {
                dfs(grid, nr, nc, visited, region, frontier, walls);
            } else if (grid[nr][nc] == 0) {
                walls[0]++;
                frontier.add(nr * n + nc);
            }
        }
    }
}