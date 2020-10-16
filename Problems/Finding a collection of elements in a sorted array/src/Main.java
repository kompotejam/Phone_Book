import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner userinput = new Scanner(System.in);
        int length1 = userinput.nextInt();
        int[] array1 = new int[length1];
        for (int i = 0; i < length1; i++) {
            array1[i] = Integer.parseInt(userinput.next());
        }
        int length2 = userinput.nextInt();
        int[] array2 = new int[length2];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = Integer.parseInt(userinput.next());
            System.out.print(binarySearch(array1, array2[i], 0, array1.length-1)+" ");
        }

    }

    public static int binarySearch(int[] array1, int elem, int left, int right) {
        while (left <= right ) {
            int mid = left + (right - left) / 2;

            if (elem == array1[mid]) {
                return mid + 1;
            } else if (elem < array1[mid]) {
                right = mid - 1;
            } else  {
                left = mid +1;
            }
        }
        return -1;
    }
}