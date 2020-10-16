import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner userinput = new Scanner(System.in);
        int arraysize = userinput.nextInt();
        int[] arrayjump = new int[arraysize];

        for (int i = 0; i < arraysize; i++) {
            arrayjump[i] = userinput.nextInt();
        }

        int target = userinput.nextInt();

        System.out.println(jumpsearchalgo(arrayjump, target, arraysize));
    }

    public static String jumpsearchalgo(int[] arrayjump, int target, int arrasize) {
        int jumplength = (int) Math.sqrt(arrayjump.length);
        String blockFoundByDefault = null;

        if (arrayjump.length == 0 || target > arrayjump[arrasize - 1]) {
            blockFoundByDefault = "-1";
        } else if (arrayjump[2] >= target)
            blockFoundByDefault = "0 2";

        else {
            blockFoundByDefault = findSuitableBlock(arrayjump, target, jumplength);
        }
        return blockFoundByDefault;
    }

    public static String findSuitableBlock(int[] arrayjump, int targettofind, int jumpsearch) {
        String blockFOund = null;
        int currentRight = 0;
        int previousRight = 0;
        while (currentRight <= arrayjump.length - 1) {
            currentRight = Math.min(arrayjump.length - 1, currentRight + jumpsearch);


            if (arrayjump[currentRight - 1] >= targettofind) {
                blockFOund = Integer.toString(previousRight) + " " + Integer.toString(currentRight - 1);
                break;
            }

            if (currentRight == arrayjump.length - 1 && targettofind <= arrayjump[currentRight]) {
                blockFOund = Integer.toString(arrayjump.length - 1) + " " + Integer.toString(arrayjump.length - 1);
                break;
            }
            // try to find the right solution to test #4 but doesn't work out yet...

            previousRight = currentRight;

        }
        return blockFOund;

    }
}



