package uts.christian.jonathan.laimena.util;
import java.util.Scanner;

public class UtilClass {
    private static final Scanner input = new Scanner(System.in);

    public static int readInt() {
        return input.nextInt();
    }

    public static double readDouble() {
        return input.nextDouble();
    }

    public static String readString() {
        return input.nextLine();
    }
}
