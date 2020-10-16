import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner userinput = new Scanner (System.in);
        int arraySize = userinput.nextInt();

        int [] array = new int[arraySize];

        for (int i = 0; i < arraySize ; i++) {
            array[i] = userinput.nextInt();
        }

        int target = userinput.nextInt();

        if (array[0]== target) {
            System.out.println("1");
        }else {
            System.out.println(findNumberOfComparisons(array, target, arraySize));

        }

    }
    public static int findNumberOfComparisons (int[] array, int target, int arraysize){
        int jumpsearch = (int) Math.sqrt(arraysize);
        int currentRight = 0;
        int previousRight = 0;
        int numberOfCamparisons = 0;

        while  (currentRight <= array.length-1) {
            currentRight = Math.min(array.length-1, currentRight+jumpsearch);
            numberOfCamparisons++;

            if (currentRight == array.length-1){
                break;
            }

            if (array[currentRight] >= target){
                break;
            }
            previousRight = currentRight;
        }
        return numberOfComparisonsStep2(array, target, jumpsearch, previousRight, currentRight, numberOfCamparisons);
    }

    public static int numberOfComparisonsStep2 (int [] array, int target, int jump, int left, int right, int comparisons){
        for (int i = right; i > left; i-- ) {
            if  (array[i] == target || array[i]<target) {
                comparisons++;
                break;
            }
            comparisons++;

        }
        return comparisons;
    }

}