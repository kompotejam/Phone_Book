import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
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

            if (index > table.length-1 ){
                rehash();
            }

            table[index] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();          
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            // put your code here
            HashTable<T> newHashTable = new HashTable<>(size * 2);
            for (TableEntry<T> entry : table) {
                newHashTable.put(entry.key, entry.value);
            }
            size = newHashTable.size;
            table = newHashTable.table;

        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(i + ": key=" + table[i].getKey()
                                                + ", value=" + table[i].getValue());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        // put your code here
        Scanner userInput = new Scanner(System.in);
        HashTable<String> table = new HashTable<>(5);
        int lengthLoop = Integer.parseInt(userInput.nextLine());


        while(userInput.hasNext()) {
            String [] tempHold = userInput.nextLine().split(" ");
            if(table.findKey(Integer.parseInt(tempHold[0])) == -1) {
                table.rehash();
            }

            table.put(Integer.parseInt(tempHold[0]), tempHold[1]);


        }

        System.out.print(table.toString());
    }
}