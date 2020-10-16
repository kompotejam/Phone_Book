import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner userInput = new Scanner(System.in);

        int length = userInput.nextInt();
        int i = 0;
        int [] array = new int[length];
        for (  i = 0; i <length; i++){
            array[i] = userInput.nextInt();
        }
        int left = 0;


        System.out.println(binarySearch(array, left, length-1));
    }

    public static boolean binarySearch(int[] array, int left, int right){
        if (right >= left) {

            int middle = (left + right) / 2;
            if (middle == array[middle]){
                return true;
            } else if (middle > array[middle]) {
                return binarySearch(array, (middle +1), right);
            } else if (middle < array[middle]) {
                return binarySearch(array, left, (middle - 1));
            } else {
                return false;
            }
        }
            return false;
    }
}