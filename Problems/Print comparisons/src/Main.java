import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner userinput = new Scanner(System.in);
        int arrayLength = userinput.nextInt();
        int [] array = new int[arrayLength];
        int comparisons = 0;

        for (int i = 0 ; i < arrayLength ; i++) {
            array[i] = i;
        }

        for (int i = 0 ; i < arrayLength ; i++){

            System.out.print(jumpSearch(array, i, comparisons)+" ");

            if (i >= (int) Math.sqrt(arrayLength)){
                if (i % (int) Math.sqrt(arrayLength)==0){
                    comparisons++;
                }
            }


        }

    }
        public static String jumpSearch (int [] array,int target, int comparisons) {
            int currentRight = 0;
            int previousRight = 0;

            if (array.length == 0) {
                return "-1";
            }

            if (target == array[0]) {
                return "1";
            }

            int jumpLength = (int) Math.sqrt(array.length);

            while (currentRight < array.length - 1){
                currentRight = Math.min(array.length - 1, currentRight + jumpLength);

                if (array[currentRight] >= target) {
                    comparisons++;
                    break;
                }
                    previousRight = currentRight;

            }
            return backwardSearch(array, target, previousRight, currentRight, comparisons);
        }

        public static String backwardSearch (int [] array, int target, int leftExcl, int rightIncl,int comparisons) {
        for (int i = rightIncl; i > leftExcl; i--) {
                    comparisons++;
                    if (array[i] == target) {
                        break;
                    }
            }
            return Integer.toString(comparisons);
    }
}