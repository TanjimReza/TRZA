/*
 * Recursive depth-first traversal of an undirected, unweighted graph.
 * Java's call stack stores each unfinished DFS call.
 */
public class DFSRecursive {
    TraversalGraph graph;
    boolean[] visited;
    int[] parent;
    int[] startingTime;
    int[] endingTime;
    int time;

    public void dfsTraversal(TraversalGraph graph, int startingVertex) {
        this.graph = graph;
        this.visited = new boolean[graph.vertexCount];
        this.parent = new int[graph.vertexCount];
        this.startingTime = new int[graph.vertexCount];
        this.endingTime = new int[graph.vertexCount];

        for (int vertex = 0; vertex < parent.length; vertex++) {
            parent[vertex] = -1;
        }

        time = 0;
        System.out.print("Recursive DFS traversal: ");

        // First traverse every vertex reachable from the requested starting point.
        dfs(startingVertex);

        // Repeat from any unvisited vertex so disconnected parts are not skipped.
        for (int vertex = 0; vertex < graph.vertexCount; vertex++) {
            if (!visited[vertex]) {
                dfs(vertex);
            }
        }

        System.out.println();
        printParentArray(parent);
        printTimes(startingTime, endingTime);
    }

    public void dfs(int currentVertex) {
        visited[currentVertex] = true;
        startingTime[currentVertex] = ++time;
        System.out.print(currentVertex + " ");

        TraversalEdgeNode currentNode = graph.adjList[currentVertex];

        while (currentNode != null) {
            int neighbor = currentNode.dest;

            if (!visited[neighbor]) {
                parent[neighbor] = currentVertex;
                dfs(neighbor);
            }

            currentNode = currentNode.next;
        }

        endingTime[currentVertex] = ++time;
    }

    public void printParentArray(int[] parent) {
        System.out.print("Parent array:  ");

        for (int vertex = 0; vertex < parent.length; vertex++) {
            if (parent[vertex] == -1) {
                System.out.print("N ");
            } else {
                System.out.print(parent[vertex] + " ");
            }
        }

        System.out.println();
    }

    public void printTimes(int[] startingTime, int[] endingTime) {
        System.out.println("Start/end time:");

        for (int vertex = 0; vertex < startingTime.length; vertex++) {
            System.out.println("Vertex " + vertex + ": "
                    + startingTime[vertex] + "/" + endingTime[vertex]);
        }
    }

    public static void main(String[] args) {
        TraversalGraph graph = new TraversalGraph(8);

        // The undirected, unweighted graph from the book's recursive DFS simulation.
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(3, 7);
        graph.addEdge(4, 7);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        DFSRecursive dfs = new DFSRecursive();
        dfs.dfsTraversal(graph, 7);
    }
}
