import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner userInput = new Scanner(System.in);

        double a = userInput.nextDouble();
        double b = userInput.nextDouble();
        double c = userInput.nextDouble();

        double p = (a + b + c) / 2.0;

        System.out.println(Math.sqrt(p * (p-a)*(p-b)*(p-c)));


    }
}