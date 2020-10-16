import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double liquidDensity = scanner.nextDouble();
        double heightColumn = scanner.nextDouble();
        double gravity = 9.8;
        double liquidPressure = gravity*liquidDensity*heightColumn;

        System.out.println(liquidPressure);
    }
}