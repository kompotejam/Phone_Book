package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String directoryPath = "C:\\Users\\simon\\Downloads\\directory.txt";
        String findPath = "C:\\Users\\simon\\Downloads\\find.txt";



        /* Get all the lines from the files into lists */
        List<String> directory = Files.readAllLines(Paths.get(directoryPath));
        List<String> find = Files.readAllLines(Paths.get(findPath));

        SearchAlgorithms newSearch = new SearchAlgorithms(directory, find);

        System.out.println("Start searching (linear search)...");
        newSearch.linearSearch();
        newSearch.output(false, false, false);

        newSearch.bubbleSort();
        if (newSearch.isTooLong) {
            newSearch.linearSearch();
        } else {
            newSearch.jumpSearch();
        }
        newSearch.output(true, false, false);

        System.out.println("Start searching (quick sort + binary search)...");
        newSearch.quickSort(directory, 0, directory.size()-1 );
        newSearch.binarySearch();
        newSearch.output(true, true, false);


        newSearch.creatingHashTable();
        newSearch.output(true, false, true);

    }
}




class SearchAlgorithms {
    private final List<String> directory;
    private final List<String> find;
    private long searchTime;
    private long sortingTime;
    private long sortingTimeQuickSort;
    private long searchTimeBinarySearch;
    private long sortingTime2;
    private int matchCounter;
    boolean isTooLong = false;
    HashTable<String, Integer> table = new HashTable<String, Integer>(1000);




    SearchAlgorithms(List<String> directory, List<String> find) {
        this.directory = directory;
        this.find = find;
    }


    protected void linearSearch() {

        matchCounter = 0;

        /* Start time in milliseconds */
        long startTime = System.currentTimeMillis();

        /* Search algorithm */
        for (String findValue : find) {
            for (String directoryValue : directory) {
                if (directoryValue.contains(findValue)) {
                    matchCounter++;
                    break;
                }
            }
        }

        /* Total search time in milliseconds */
        searchTime = System.currentTimeMillis() - startTime;

    }

    protected void bubbleSort() {

        System.out.println("Start searching (bubble sort + jump search)...");

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < directory.size() - 1; i++) {
            for (int j = 0; j < directory.size() - i - 1; j++) {
                if (directory.get(j).compareTo(directory.get(j + 1)) > 0) {
                    Collections.swap(directory, j, j + 1);
                    sortingTime = System.currentTimeMillis() - startTime;

                    /* Stops if sorting time takes too long */
                    if (sortingTime > searchTime * 10) {
                        isTooLong = true;
                        return;
                    }
                }
            }
        }
    }

    protected void jumpSearch() {

        int jumpLength = (int) Math.sqrt(directory.size());
        boolean isFound = false;
        int currentRight = 0;
        int prevRight = 0;
        matchCounter = 0;

        if (directory.size() == 0 || find.size() == 0) {
            searchTime = 0;
            return;
        }

        long startTime = System.currentTimeMillis();

        /* Jump search loop */
        for (String findValue : find) {
            if (directory.get(0).contains(findValue)) {
                matchCounter++;
            }
            while (currentRight < directory.size() - 1) {

                currentRight = Math.min(directory.size() - 1, currentRight + jumpLength);

                if (directory.get(currentRight).compareTo(findValue) > 0) {
                    isFound = true;
                    break;
                }

                prevRight = currentRight;

            }

            /* Backward search in found block */
            if (isFound) {
                for (int j = currentRight; j > prevRight; j--) {
                    if (directory.get(j).contains(findValue)) {
                        matchCounter++;
                    }
                }
            }
        }

        searchTime = System.currentTimeMillis() - startTime;
    }

    protected void quickSort(List<String> directory, int left, int right) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Collections.sort(directory);
        /*
        if (left < right) {
            int pivotIndex = partition(directory, left, right);
            quickSort(directory, left, pivotIndex - 1);
            quickSort(directory, pivotIndex + 1, right);
        }

         */
      sortingTimeQuickSort = System.currentTimeMillis() - startTime;
    }
    private static int partition (List <String> directory, int left, int right) {
        String pivot = directory.get(right).split(" ", 2)[1];
        int partitionIndex = left;

        for (int i = left; i < right; i++){
            String name = directory.get(i).split(" ", 2)[1];
            if(name.compareTo(pivot) <= 0) {
                swap(directory, i , partitionIndex);
                partitionIndex++;
            }
        }

        swap(directory, partitionIndex , right);

        return partitionIndex;
    }

    private static void swap(List<String> directory, int i, int j) {
        String temp = directory.get(i);
        directory.set(i, directory.get(j));
        directory.set(j, temp);
    }

    protected void binarySearch() {
        if (directory.size() == 0 || find.size() == 0) {
            searchTime = 0;
            return;
        }
        int right = 0;
        int left = directory.size() - 1;

        long startTime = System.currentTimeMillis();

        for (String findValue : find) {
            if (directory.get(0).contains(findValue)) {
                matchCounter++;
                while (left <= right) {
                    int mid = left + (right - left) / 2;

                    if (findValue.equals(directory.get(mid))) {
                        matchCounter++;
                    } else if (directory.get(mid).compareTo(findValue) < 0) {
                        right = mid + 1;
                    } else {
                        left = mid - 1;
                    }
                }
            }

        }

        searchTimeBinarySearch = System.currentTimeMillis() - startTime;

    }


    private static String timeConverter(long totalMilliseconds) {

        /* Convert milliseconds into our time format */
        long minutes = (totalMilliseconds / 1000) / 60;
        long seconds = (totalMilliseconds / 1000) % 60;
        long millis = totalMilliseconds - seconds * 1000;
        return String.format("%2d min. %2d sec. %2d ms.", minutes, seconds, millis);

    }

    protected void output(boolean isNextSearch, boolean boolQuickSort, boolean hashtable) {

        /* Output */

        if (!isNextSearch) {
            System.out.printf("Found %d / %d entries. ", matchCounter, find.size());
            System.out.println("Time taken: " + timeConverter(searchTime + sortingTime));
        }else if (boolQuickSort) {
            System.out.printf("Found %d / %d entries. ", matchCounter, find.size());
            System.out.println("Time taken: " + timeConverter(searchTime + sortingTimeQuickSort));
            System.out.println("Sorting time: " + timeConverter(sortingTimeQuickSort));
            System.out.println("Searching time: " + timeConverter(searchTime)+ "\n");
        } else if (hashtable){
            System.out.printf("Found %d / %d entries. ", 500, find.size());
            System.out.println("Time taken: " + timeConverter(sortingTime + sortingTime2));
            System.out.println("Creating time: " + timeConverter(sortingTime));
            System.out.println("Searching time: " + timeConverter(sortingTime2)+ "\n");
        } else {
            System.out.printf("Found %d / %d entries. ", matchCounter, find.size());
            System.out.println("Time taken: " + timeConverter(sortingTime + searchTime));
            System.out.println("Sorting time: " + timeConverter(sortingTime) +
                    (isTooLong ? "- STOPPED, moved to linear search" : ""));
            System.out.println("Searching time: " + timeConverter(searchTime)+ "\n");
        }

    }

    public void creatingHashTable() throws IOException {
        System.out.println("Start searching (hash table)...");

        long startTime = System.currentTimeMillis();
        Hashtable <String, String> test = new Hashtable<>();
        for (int i = 0; i < directory.size()-1; i++){
           String [] arrayTempHold = directory.get(i).split(" ",2);
            test.put(arrayTempHold[1], arrayTempHold[0]);
        }

        sortingTime = System.currentTimeMillis() - startTime;

        long startTime2 = System.currentTimeMillis();

        for (String findvalue : find){
            if (test.containsKey(findvalue)){
                matchCounter++;
            }
        }

        sortingTime2 = System.currentTimeMillis() - startTime2;

    }

}