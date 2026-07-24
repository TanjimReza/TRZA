public class RotateSecret {
    public static void rotateSecret(char[][] board) {
        int totalLayers = board.length / 2;
        int rotations = 1;

        // Start from the innermost layer
        for (int layer = totalLayers - 1; layer >= 0; layer--) {
            for (int count = 0; count < rotations; count++) {
                rotateOneTime(board, layer);
            }

            rotations++;
        }

        // Print the recovered message
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                System.out.print(board[row][column]);
            }
        }

        System.out.println();
    }

    public static void rotateOneTime(char[][] board, int layer) {
        int last = board.length - 1 - layer;

        // Save the top-left value
        char temporary = board[layer][layer];

        // Move the left side upward
        for (int row = layer; row < last; row++) {
            board[row][layer] = board[row + 1][layer];
        }

        // Move the bottom side to the left
        for (int column = layer; column < last; column++) {
            board[last][column] = board[last][column + 1];
        }

        // Move the right side downward
        for (int row = last; row > layer; row--) {
            board[row][last] = board[row - 1][last];
        }

        // Move the top side to the right
        for (int column = last; column > layer + 1; column--) {
            board[layer][column] = board[layer][column - 1];
        }

        // Put the saved value in its new position
        board[layer][layer + 1] = temporary;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'T', 'A', 'U', 'S'},
                {'A', 'R', 'I', '.'},
                {'D', 'T', 'T', 'N'},
                {'S', 'C', 'F', 'U'}
        };

        rotateSecret(board);
    }
}
