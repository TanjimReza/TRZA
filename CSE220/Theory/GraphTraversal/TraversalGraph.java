/*
 * Undirected, unweighted graph stored as an array of linked lists.
 * Adjacency-list insertion order determines neighbor traversal order.
 */
public class TraversalGraph {
    TraversalEdgeNode[] adjList;
    int vertexCount;

    public TraversalGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjList = new TraversalEdgeNode[vertexCount];
    }

    public void appendLL(TraversalEdgeNode head, TraversalEdgeNode newNode) {
        TraversalEdgeNode currentNode = head;

        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = newNode;
    }

    public void addEdge(int from, int to) {
        TraversalEdgeNode newNode = new TraversalEdgeNode(to);

        if (adjList[from] == null) {
            adjList[from] = newNode;
        } else {
            appendLL(adjList[from], newNode);
        }

        TraversalEdgeNode reverseNode = new TraversalEdgeNode(from);

        if (adjList[to] == null) {
            adjList[to] = reverseNode;
        } else {
            appendLL(adjList[to], reverseNode);
        }
    }

}
