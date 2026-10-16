package uts.christian.jonathan.laimena.views;
import java.util.List;

import uts.christian.jonathan.laimena.models.MenuItem;

public class CoffeeShopAppConsoleView {
    public void showDaftarMenu(List<MenuItem> daftarMenu) {
        System.out.println("\nIni adalah Daftar Menu untuk Coffee Shop KOPIITHB:");
        for (MenuItem item : daftarMenu) {
            System.out.printf("[%s] %s - Rp%.0f (Stok: %d)\n", item.getId(), item.getNama(), item.getHargaBase(), item.getStok());
        }
        System.out.println("===============================");
    }

    public void showPesan(String pesan) {
        System.out.println(pesan);
    }

    public void showMenuUkuran() {
        System.out.println("Pilih Size Item nya Untuk Menu Anda (yang jelas, Small? Medium? atau Large?):");
        System.out.println("1. SMALL");
        System.out.println("2. MEDIUM (+Rp2000)");
        System.out.println("3. LARGE (+Rp4000)");
    }

    public void cetakStruk(MenuItem item, int jumlah, double totalHarga) {
        System.out.println("\n================ Struk Pemesanan KOPIITHB ================");
        System.out.println(item.getOrderDetail());
        System.out.println("Jumlah Pesanan : " + jumlah);
        System.out.printf("Total Harga    : Rp%.0f\n", totalHarga);
        System.out.println("=================================================");
    }
}
