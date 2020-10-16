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
            // put your code here
            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            table[index] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            // put your code here
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return (T) "-1";
            }

            return (T) table[idx].getValue();
        }

        public Object remove(int key) {
            // put your code here
            int idx = findKey(key);

            if (!(idx == -1 || table[idx] == null)) {
                return table[idx] = null;
            }
            return null;
        }

        private int findKey(int key) {
            // put your code here
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
    }

    public static void main(String[] args) {
        // put your code here
        Scanner userInput = new Scanner(System.in);
        int lengthLoop = Integer.parseInt(userInput.nextLine());
        HashTable<String> table = new HashTable<>(lengthLoop);

        while (userInput.hasNext()){

            String [] tempHold = userInput.nextLine().split(" ");
            if(table.findKey(Integer.parseInt(tempHold[1])) == -1) {
                table.rehash();
            }
            if (tempHold[0].equals("put")) {
                table.put(Integer.parseInt(tempHold[1]), tempHold[2]);
            } else if (tempHold[0].equals("get")) {
                if(table.get(Integer.parseInt(tempHold[1]))==null){
                    continue;
                }else {
                    System.out.println(table.get(Integer.parseInt(tempHold[1])));
                }
            } else {
                table.remove(Integer.parseInt(tempHold[1]));
            }
        }


    }
}