import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner input = new Scanner (System.in);

        double ux = input.nextDouble();
        double uy = input.nextDouble();
        double vx = input.nextDouble();
        double vy = input.nextDouble();

        System.out.println(Math.toDegrees(Math.acos((ux * vx + vy * uy) / (Math.hypot(ux, uy) * Math.hypot(vx, vy)))));

    }
}