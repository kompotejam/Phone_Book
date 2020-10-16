import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner userinput = new Scanner(System.in);
        int count = 0;
        int arraysize = userinput.nextInt();

        int [] array = new int[arraysize];

        for (int i = 0; i < arraysize; i++) {
            array[i] = userinput.nextInt();
        }
        int n = userinput.nextInt();

        for (int val : array) {
            if (val > n ){
                count += val;
            }
        }

        System.out.println(count);

    }
}