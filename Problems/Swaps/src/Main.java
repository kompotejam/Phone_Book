import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner userInput = new Scanner (System.in);
        String s = userInput.nextLine();
        String [] temp = s.split(" ");
        int [] arrayOfInt = new int[temp.length];

        for (int i=0; i<arrayOfInt.length;i++)
            arrayOfInt[i] = Integer.parseInt(temp[i]);


        System.out.println(bubbleSort(arrayOfInt));
    }

    public static int bubbleSort(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j] > array[j + 1]) {
                    count++;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return count;
    }
}