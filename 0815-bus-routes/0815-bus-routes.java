import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target)
            return 0;

        Map<Integer, List<Integer>> stopToBus = new HashMap<>();

        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                stopToBus.computeIfAbsent(stop, k -> new ArrayList<>()).add(bus);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedBuses = new HashSet<>();

        visitedStops.add(source);

        int busesTaken = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            busesTaken++;

            for (int i = 0; i < size; i++) {

                int stop = queue.poll();

                if (!stopToBus.containsKey(stop))
                    continue;

                for (int bus : stopToBus.get(stop)) {

                    if (visitedBuses.contains(bus))
                        continue;

                    visitedBuses.add(bus);

                    for (int nextStop : routes[bus]) {

                        if (nextStop == target)
                            return busesTaken;

                        if (!visitedStops.contains(nextStop)) {
                            visitedStops.add(nextStop);
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }

        return -1;
    }
}