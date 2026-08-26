/*
 * Undirected, unweighted graph without self-loops:
 *
 *       0 ----- 1
 *        \     / \
 *         \   /   \
 *           2      3
 *           |
 *           4
 *
 * matrix[row][col] = 1 means the two vertices share a direct edge.
 * An undirected adjacency matrix is symmetric:
 * matrix[row][col] = matrix[col][row].
 *
 * Space: O(V^2)
 */
public class GraphMatrix {
    int[][] matrix;
    int vertexCount;

    public GraphMatrix(int vertexCount) {
        /*
         * A graph with V vertices needs a V x V matrix.
         * Java initially fills every cell with 0, so there are no edges.
         * Time: O(V^2), Space: O(V^2)
         */
        this.vertexCount = vertexCount;
        this.matrix = new int[vertexCount][vertexCount];
    }

    public void addEdge(int vertex1, int vertex2) {
        /*
         * An undirected edge needs two entries because each vertex
         * is adjacent to the other vertex. Time: O(1)
         */
        matrix[vertex1][vertex2] = 1;
        matrix[vertex2][vertex1] = 1;
    }

    public void removeEdge(int vertex1, int vertex2) {
        // Change both symmetric entries back to 0. Time: O(1)
        matrix[vertex1][vertex2] = 0;
        matrix[vertex2][vertex1] = 0;
    }

    public boolean hasEdge(int vertex1, int vertex2) {
        /*
         * This checks a direct edge, not whether a longer path exists.
         * Direct matrix access takes O(1).
         */
        return matrix[vertex1][vertex2] == 1;
    }

    public void printNeighbors(int vertex) {
        // Every 1 in this vertex's row represents a neighbor. Time: O(V)
        for (int col = 0; col < vertexCount; col++) {
            if (matrix[vertex][col] == 1) {
                System.out.print(col + " ");
            }
        }

        System.out.println();
    }

    public int degree(int vertex) {
        // In an undirected graph, degree is the number of 1s in the row. O(V)
        int count = 0;

        for (int col = 0; col < vertexCount; col++) {
            if (matrix[vertex][col] == 1) {
                count++;
            }
        }

        return count;
    }

    public void printMatrix() {
        // Visit all V x V cells. Time: O(V^2)
        for (int row = 0; row < vertexCount; row++) {
            for (int col = 0; col < vertexCount; col++) {
                System.out.print(matrix[row][col] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphMatrix graph = new GraphMatrix(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        System.out.println("Adjacency matrix:");
        graph.printMatrix();

        System.out.print("Neighbors of 2: ");
        graph.printNeighbors(2);
        System.out.println("Degree of 2: " + graph.degree(2));

        System.out.println("0 and 1 adjacent? " + graph.hasEdge(0, 1));
        System.out.println("0 and 4 adjacent? " + graph.hasEdge(0, 4));

        System.out.println("1 and 3 adjacent before removal? "
                + graph.hasEdge(1, 3));
        graph.removeEdge(1, 3);
        System.out.println("1 and 3 adjacent after removal? "
                + graph.hasEdge(1, 3));
    }
}
