package uts.christian.jonathan.laimena.util;
import java.util.Scanner;

public class UtilClass {
    private static Scanner input = new Scanner(System.in);

    public static String inputString(String pesan) {
        System.out.print(pesan);
        return input.next();
    }

    public static int inputInt(String pesan) {
        System.out.print(pesan);
        return input.nextInt();
    }

    public static double inputDouble(String pesan) {
        System.out.print(pesan);
        return input.nextDouble();
    }
}
