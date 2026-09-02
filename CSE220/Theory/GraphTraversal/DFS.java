/*
 * Iterative depth-first traversal of an undirected, unweighted graph.
 *
 * DFS uses a linked-list stack, a visited array, and a parent array.
 *
 * Time: O(V + E)
 * Extra space: O(V)
 */
public class DFS {
    public void dfsTraversal(TraversalGraph graph, int startingVertex) {
        boolean[] visited = new boolean[graph.vertexCount];
        int[] parent = new int[graph.vertexCount];

        for (int vertex = 0; vertex < parent.length; vertex++) {
            parent[vertex] = -1;
        }

        System.out.print("DFS traversal: ");

        // First traverse every vertex reachable from the requested starting point.
        dfsFrom(graph, startingVertex, visited, parent);

        // Repeat from any unvisited vertex so disconnected parts are not skipped.
        for (int vertex = 0; vertex < graph.vertexCount; vertex++) {
            if (!visited[vertex]) {
                dfsFrom(graph, vertex, visited, parent);
            }
        }

        System.out.println();
        printParentArray(parent);
    }

    public void dfsFrom(TraversalGraph graph, int start, boolean[] visited, int[] parent) {
        DFSStack stack = new DFSStack();

        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            System.out.print(currentVertex + " ");

            TraversalEdgeNode currentNode = graph.adjList[currentVertex];

            while (currentNode != null) {
                int neighbor = currentNode.dest;

                if (!visited[neighbor]) {
                    stack.push(neighbor);
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

        // The undirected, unweighted graph from the book's DFS simulation.
        // Reverse insertion order keeps the book's order when the stack pops.
        graph.addEdge(6, 7);
        graph.addEdge(5, 7);
        graph.addEdge(4, 7);
        graph.addEdge(3, 7);
        graph.addEdge(2, 5);
        graph.addEdge(1, 2);
        graph.addEdge(0, 4);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);

        DFS dfs = new DFS();
        dfs.dfsTraversal(graph, 7);
    }
}
