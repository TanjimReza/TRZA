public class MaxHeap {
    Integer[] heap;
    Integer heapSize;
    Integer heapCapacity;

    public MaxHeap(Integer capactiy) {
        if (capactiy == null || capactiy < 0) {
            System.out.println("Capacity cannot be null!");
            return;
        }

        this.heapCapacity = capactiy;
        this.heapSize = 0;
        // Since 1-based, we need one extra space
        this.heap = new Integer[capactiy + 1];

    }

    public void createHeap(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            insertItem(array[i]);
        }
    }

    public void insertItem(Integer value) {
        if (value == null || this.heapSize == this.heapCapacity) {
            System.out.println("Heap is full or value cannot be null");
            return;
        }

        // Adding a new value, so size should increase
        this.heapSize++;

        // new item is placed in the end
        this.heap[heapSize] = value;

        // swim till it's in the right place
        swim(this.heapSize);

    }

    public void swim(Integer index) {

        // we can swim till we are at the start of the array
        // after this point we do not have any parent
        while (index > 1) {
            Integer parent_index = index / 2;
            Integer parent_value = this.heap[parent_index];
            Integer current_value = this.heap[index];

            if (current_value > parent_value) {
                this.heap[index] = parent_value;
                this.heap[parent_index] = current_value;
                index = parent_index;
            } else {
                break;
            }
        }
    }

    public Integer extractMax() {
        // we always extract the first/max item
        if (this.heapSize == 0) {
            System.out.println("Heap is Empty!");
            return null;
        }

        Integer extractedValue = this.heap[1];

        // last item is put in the root
        this.heap[1] = this.heap[this.heapSize];
        // last item is set as null
        this.heap[heapSize] = null;
        // since we have removed one item, heap size decreases
        this.heapSize--;

        // sink till the item is at its correct position
        sink(1);

        return extractedValue;
    }

    public void sink(Integer index) {
        // we can continue checking till there's one left child left
        // this check also ensures the left child existance check
        while (2 * index <= this.heapSize) {
            Integer currentValue = this.heap[index];

            Integer leftChildIndex = 2 * index;
            Integer rightChildIndex = 2 * index + 1;
            Integer leftChildValue = this.heap[leftChildIndex];

            Integer largest_child_index = leftChildIndex;

            if (rightChildIndex <= this.heapSize) {
                // we must check if right child exists
                Integer rightChildValue = this.heap[rightChildIndex];

                if (rightChildValue > leftChildValue) {
                    largest_child_index = rightChildIndex;
                }
            }

            if (this.heap[largest_child_index] > currentValue) {
                this.heap[index] = this.heap[largest_child_index];
                this.heap[largest_child_index] = currentValue;

                index = largest_child_index;
            } else {
                break;
            }

        }

    }

    public void printHeap() {
        for (int i = 1; i <= this.heapCapacity; i++) {
            System.out.print(this.heap[i] + ", ");
        }
    }

    public static void main(String[] args) {
        Integer[] array = { null, 10, 20, 30, 40, 50, 60, 70 };
        MaxHeap myheap = new MaxHeap(7);
        myheap.createHeap(array);
        myheap.printHeap();
        System.out.println(myheap.extractMax());
        myheap.printHeap();

    }
}
