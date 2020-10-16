import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        float distance = scanner.nextFloat();
        float speed = scanner.nextFloat();

        System.out.println(distance/speed);
    }
}