package uts.christian.jonathan.laimena.controllers;
import uts.christian.jonathan.laimena.util.UtilClass;

public class CoffeeShopAppController {
    public void runApp() {
        int userMenuInput = -1;

        while (userMenuInput != 0) {
            System.out.println("=== Menu ===");
            System.out.println("1. Tambah Coffee");
            System.out.println("2. Tampilkan Coffee");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            userMenuInput = UtilClass.readInt();

            if (userMenuInput == 1) {
                // Tambah Coffee
                System.out.println("Menambahkan Coffee...");
            } else if (userMenuInput == 2) {
                // Tampilkan Coffee
                System.out.println("Menampilkan Coffee...");
            } else if (userMenuInput == 0) {
                System.out.println("Keluar dari aplikasi.");
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

}
