class HashNode {
    int key;
    String value;
    HashNode next;

    HashNode(int key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

public class HashTable {
    HashNode[] table;
    int size;

    public HashTable(int capacity) {
        if (capacity <= 0) {
            System.out.println("Capacity must be greater than 0. Using capacity 5.");
            capacity = 5;
        }

        table = new HashNode[capacity];
        size = 0;
    }

    public int hashFunction(int key) {
        return key % table.length;
    }

    public void insert(int key, String value) {
        int index = hashFunction(key);
        HashNode newNode = new HashNode(key, value);

        if (table[index] == null) {
            table[index] = newNode;
            size++;
            return;
        }

        HashNode currentNode = table[index];

        while (currentNode != null) {
            if (currentNode.key == key) {
                System.out.println("Key already exists. Duplicate key not inserted: " + key);
                return;
            }

            if (currentNode.next == null) {
                currentNode.next = newNode;
                size++;
                return;
            }

            currentNode = currentNode.next;
        }
    }

    public String search(int key) {
        int index = hashFunction(key);
        HashNode currentNode = table[index];

        while (currentNode != null) {
            if (currentNode.key == key) {
                return currentNode.value;
            }

            currentNode = currentNode.next;
        }

        return null;
    }

    public void display() {
        System.out.println("HashTable:");

        for (int i = 0; i < table.length; i++) {
            System.out.print("Index " + i + ": ");

            HashNode currentNode = table[i];

            while (currentNode != null) {
                System.out.print("(" + currentNode.key + ", " + currentNode.value + ") -> ");
                currentNode = currentNode.next;
            }

            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        HashTable animals = new HashTable(5);

        animals.insert(3, "CAT");
        animals.insert(4, "EELS");
        animals.insert(5, "LION");
        animals.insert(2, "DOG");
        animals.insert(9, "CROW");
        animals.insert(27, "WOLF");
        animals.insert(10, "FISH");
        animals.insert(11, "OWL");

        // 8 and 3 both map to index 3 when table length is 5.
        animals.insert(8, "GOAT");

        animals.display();

        System.out.println("Search key 8: " + animals.search(8));
        System.out.println("Search key 27: " + animals.search(27));
        System.out.println("Search key 100: " + animals.search(100));

        animals.insert(8, "HORSE");
        System.out.println("Search key 8 after duplicate insert: " + animals.search(8));
    }
}
