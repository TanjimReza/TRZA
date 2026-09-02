/*
 * Breadth-first traversal of an undirected, unweighted graph.
 *
 * BFS uses:
 * 1. A queue to store the vertices that will be processed next.
 * 2. A visited array to avoid visiting the same vertex more than once.
 * 3. A parent array to store the edge that first discovered each vertex.
 *
 * Time: O(V + E)
 * Extra space: O(V)
 */
public class BFS {
    public void bfsTraversal(TraversalGraph graph, int startingVertex) {
        boolean[] visited = new boolean[graph.vertexCount];
        int[] parent = new int[graph.vertexCount];

        for (int vertex = 0; vertex < parent.length; vertex++) {
            parent[vertex] = -1;
        }

        System.out.print("BFS traversal: ");

        // First traverse every vertex reachable from the requested starting point.
        bfsFrom(graph, startingVertex, visited, parent);

        // Repeat from any unvisited vertex so disconnected parts are not skipped.
        for (int vertex = 0; vertex < graph.vertexCount; vertex++) {
            if (!visited[vertex]) {
                bfsFrom(graph, vertex, visited, parent);
            }
        }

        System.out.println();
        printParentArray(parent);
    }

    public void bfsFrom(TraversalGraph graph, int start, boolean[] visited, int[] parent) {
        BFSQueue queue = new BFSQueue();

        queue.enqueue(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.dequeue();
            System.out.print(currentVertex + " ");
            TraversalEdgeNode currentNode = graph.adjList[currentVertex];

            while (currentNode != null) {
                int neighbor = currentNode.dest;

                if (!visited[neighbor]) {
                    queue.enqueue(neighbor);
                    visited[neighbor] = true;
                    parent[neighbor] = currentVertex;
                }

                currentNode = currentNode.next;
            }
        }
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

    public static void main(String[] args) {
        TraversalGraph graph = new TraversalGraph(8);

        // The undirected, unweighted graph from the book's BFS simulation.
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 6);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 6);
        graph.addEdge(4, 7);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);

        BFS bfs = new BFS();
        bfs.bfsTraversal(graph, 3);
    }
}
