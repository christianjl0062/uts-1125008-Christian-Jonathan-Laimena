package uts.christian.jonathan.laimena.controllers;
import java.util.List;

import uts.christian.jonathan.laimena.models.MenuItem;
import uts.christian.jonathan.laimena.util.UtilClass;
import uts.christian.jonathan.laimena.views.CoffeeShopAppConsoleView;

public class CoffeeShopAppController {
    private List<MenuItem> menuItems;
    private CoffeeShopAppConsoleView view;

    public CoffeeShopAppController(CoffeeShopAppConsoleView view, List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.view = view;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    public CoffeeShopAppConsoleView getView() {
        return view;
    }
    public void setView(CoffeeShopAppConsoleView view) {
        this.view = view;
    }

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
                System.out.println("Menambahkan Coffee...");
            } else if (userMenuInput == 2) {
                System.out.println("Menampilkan Coffee...");
            } else if (userMenuInput == 0) {
                System.out.println("Keluar dari aplikasi.");
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

}
