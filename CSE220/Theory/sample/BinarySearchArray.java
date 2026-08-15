public class BinarySearchArray {

    public static int binarySearch(int[] array, int start, int end, int searchItem) {
        int mid = (start + end) / 2;
        System.out.printf("Start %d, End: %d, Mid: %d --- ", start, end, mid);
        for (int i = start; i <= end; i++) {
            System.out.print(array[i]);
        }
        System.out.println("\n");
        if (start >= end) {
            if (array[start] == searchItem) {
                System.out.printf("Found %d at index %d\n", searchItem, start);
                return start;
            }
            return -1;
        }

        if (searchItem > array[mid]) {
            start = mid + 1;
            return binarySearch(array, start, end, searchItem);
        } else {
            end = mid - 1;
            return binarySearch(array, start, end, searchItem);
        }
    }

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6 };
        if (BinarySearchArray.binarySearch(array, 0, array.length - 1, 2) > -1) {
            System.out.println("Item found!");
        } else {
            System.out.println("Item Not found!");
        }

    }
}
