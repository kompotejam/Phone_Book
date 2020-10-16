import java.util.*;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final List <T> value;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = new ArrayList<>();
            this.value.add(value);
        }

        public int getKey() {
            return key;
        }

        public List<T> getValue() {
            return value;
        }
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            // put your code here
            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            if (table[index] == null) {
                table[index] = new TableEntry(key, value);
            } else {
                table[index].getValue().add(value);
            }
            return true;
        }

        public List <T> get(int key) {
            // put your code here
            int index = findKey(key);

            if (index == 1 || table[index] == null) {
                return null;
            }
            return table[index].getValue();
        }

        public Set entrySet() {
            // put your code here
            return entrySet();

        }

        private int findKey(int key) {
            // put your code here
            int hash = key % size;
            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = ++hash % size;
                if (hash == key % size) {
                    return -1;
                }
            }
            return hash;
        }

        public TableEntry[] getTable() {
            return table;
        }
    }

    public static void main(String[] args) {
        // put your code here
        Scanner scn = new Scanner(System.in);
        int n = Integer.parseInt(scn.nextLine());
        HashTable<String> table = new HashTable<>(n);
        for (int i = 0; i < n; ++i) {
            String[] in = scn.nextLine().split(" ");
            table.put(Integer.parseInt(in[0]), in[1]);
        }
        for (TableEntry<String> entry : table.getTable()) {
            if (entry == null) {
                continue;
            }
            System.out.print(entry.getKey() + ": ");
            for (String s : entry.getValue()) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

    }
}