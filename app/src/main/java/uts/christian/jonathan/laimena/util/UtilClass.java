package uts.christian.jonathan.laimena.util;
import java.util.Scanner;

public class UtilClass {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputString(String pesan) {
        System.out.print(pesan);
        return scanner.next();
    }

    public static int inputInt(String pesan) {
        System.out.print(pesan);
        return scanner.nextInt();
    }

    public static double inputDouble(String pesan) {
        System.out.print(pesan);
        return scanner.nextDouble();
    }
}
