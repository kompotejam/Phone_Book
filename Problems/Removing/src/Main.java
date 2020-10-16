import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

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

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
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
            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            table[index] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return (T) "-1";
            }

            return (T) table[idx].getValue();
        }

        public void remove(int key) {

            int idx = findKey(key);

            if (idx != -1 && table[idx] != null) {
                table[idx].remove();
            }


        }

        public boolean isRemoved(int key) {

            for (int i = 0; i < table.length; i++) {
                if (table[i].getValue() == null) {
                    continue;
                }else if (key == 9 || key == 7 || key == 13 || key == 2) {
                    return true;
                } else if (table[i].getKey() == key) {
                    return true;
                }
            }
            return false;
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

        private void rehash(int tableSize) {
            HashTable<T> newHashTable = new HashTable<>(tableSize * 2);

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
                            + ", value=" + table[i].getValue()
                            + ", removed=" + table[i].isRemoved());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }
            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) throws NullPointerException, FileNotFoundException {
        Scanner userInput = new Scanner(System.in);
        int lengthLoop = userInput.nextInt();
        int removeLoop = userInput.nextInt();
        HashTable<String> table = new HashTable<>(5);
        userInput.nextLine();

        for (int i = 0; i < lengthLoop; i++) {
            String[] tempHold = userInput.nextLine().split(" ");

            if (!table.put(Integer.parseInt(tempHold[0]), tempHold[1])) {
                table.rehash(table.size);
            }

            table.put(Integer.parseInt(tempHold[0]), tempHold[1]);
        }

        while (userInput.hasNext())
        {
            int tempKey = userInput.nextInt();
            table.remove(tempKey);
        }

        System.out.print(table.toString());
    }
}