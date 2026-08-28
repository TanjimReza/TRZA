class EdgeNode {
    int dest;
    int weight;
    EdgeNode next;

    public EdgeNode(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
        this.next = null;
    }
}

/*
 * Undirected, weighted graph using an array of linked lists.
 * Vertices are numbered from 0 to vertexCount - 1.
 *
 * adjList[from] is the head of the source vertex's list.
 * Each EdgeNode represents one adjacency entry: a destination and weight.
 * For example, adjList[0] -> (1,2) means 0 connects to 1 with weight 2.
 *
 * Each undirected edge needs two nodes, one in each endpoint's list.
 * Space: O(V + E)
 */
public class GraphLL {
    EdgeNode[] adjList;
    int vertexCount;

    public GraphLL(int vertexCount) {
        /*
         * Each array position stores the head of one adjacency list.
         * Java initially fills the array with null, so every list is empty.
         * Time: O(V), Space: O(V)
         */
        this.vertexCount = vertexCount;
        this.adjList = new EdgeNode[vertexCount];
    }

    public void appendLL(EdgeNode head, EdgeNode eNode) {
        /*
         * addEdge handles empty lists before calling this method.
         * Stop at the last node so we can attach the new node after it.
         * Appending keeps the edges in insertion order. Time: O(list length)
         */
        EdgeNode currentNode = head;

        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = eNode;
    }

    public void addEdge(int from, int to, int weight) {
        /*
         * The array index already tells us the source vertex.
         * The new node only needs the destination and weight.
         * Assume from and to are valid vertex numbers.
         * Time: O(1 + degree(from) + degree(to))
         */
        EdgeNode newNode = new EdgeNode(to, weight);

        if (adjList[from] == null) {
            adjList[from] = newNode;
        } else {
            appendLL(adjList[from], newNode);
        }

        // An undirected edge must also appear in the other vertex's list.
        EdgeNode reverseNode = new EdgeNode(from, weight);

        if (adjList[to] == null) {
            adjList[to] = reverseNode;
        } else {
            appendLL(adjList[to], reverseNode);
        }
    }

    public void printLL(EdgeNode head) {
        /*
         * Walk through one linked list using a separate traversal reference.
         * Time: O(list length)
         */
        EdgeNode currentNode = head;

        while (currentNode != null) {
            System.out.print(" -> (" + currentNode.dest + "," + currentNode.weight + ")");
            currentNode = currentNode.next;
        }

        System.out.println();
    }

    public void showAdjList() {
        // Visit every vertex and every edge node. Time: O(V + E)
        for (int i = 0; i < adjList.length; i++) {
            System.out.print("Vertex " + i + ":");
            printLL(adjList[i]);
        }
    }

    public static void main(String[] args) {
        GraphLL graph = new GraphLL(5);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 6);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 4, 4);

        System.out.println("Adjacency list:");
        graph.showAdjList();
    }
}
