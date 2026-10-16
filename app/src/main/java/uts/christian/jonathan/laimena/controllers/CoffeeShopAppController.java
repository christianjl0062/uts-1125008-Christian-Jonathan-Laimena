package uts.christian.jonathan.laimena.controllers;

import java.util.ArrayList;
import java.util.List;

import uts.christian.jonathan.laimena.exceptions.InputTidakValidException;
import uts.christian.jonathan.laimena.exceptions.MenuTidakDitemukanException;
import uts.christian.jonathan.laimena.exceptions.StokTidakCukupException;
import uts.christian.jonathan.laimena.models.EnumUkuran;
import uts.christian.jonathan.laimena.models.Kopi;
import uts.christian.jonathan.laimena.models.MenuItem;
import uts.christian.jonathan.laimena.models.Roti;
import uts.christian.jonathan.laimena.models.Teh;
import uts.christian.jonathan.laimena.util.UtilClass;
import uts.christian.jonathan.laimena.views.CoffeeShopAppConsoleView;

public class CoffeeShopAppController {
    private List<MenuItem> daftarMenu;
    private CoffeeShopAppConsoleView view;

    public CoffeeShopAppController(CoffeeShopAppConsoleView view) {
        this.view = view;
        this.daftarMenu = initializeMenu();
    }

    private List<MenuItem> initializeMenu() {
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new Kopi("M-01", "Kopi Susu", 18000, 5));
        menu.add(new Teh("M-02", "Teh Tarik", 12000, 10));
        menu.add(new Roti("M-03", "Roti Cokelat", 15000, 8));
        return menu;
    }

    public void runApp() {
        boolean lanjut = true;
        while (lanjut) {
            view.tampilkanDaftarMenu(daftarMenu);

            MenuItem selectedItem = null;
            while (selectedItem == null) {
                try {
                    String idMenu = UtilClass.inputString("Masukkan ID Menu yang ingin anda pesan (tuliskan id nya secara benar): ");
                    selectedItem = cariMenu(idMenu);
                } catch (MenuTidakDitemukanException e) {
                    view.tampilkanPesan(e.getMessage() + " Silakan coba lagi.");
                }
            }

            int jumlah = 0;
            boolean jumlahPesananValid = false;
            while (!jumlahPesananValid) {
                try {
                    jumlah = UtilClass.inputInt("Masukkan jumlah pesanan: ");
                    if (jumlah <= 0) {
                        throw new InputTidakValidException("Jumlah pesanan harus lebih besar dari 0!");
                    }
                    if (jumlah > selectedItem.getStok()) {
                        throw new StokTidakCukupException("Stok tidak cukup! Sisa stok: " + selectedItem.getStok());
                    }
                    jumlahPesananValid = true;
                } catch (InputTidakValidException | StokTidakCukupException e) {
                    view.tampilkanPesan(e.getMessage() + " Silakan input ulang.");
                }
            }

            List<Integer> customize = new ArrayList<>();
            if (selectedItem instanceof Kopi) {
                customize.add(UtilClass.inputInt("Masukkan jumlah Rum (0-1): "));
                customize.add(UtilClass.inputInt("Masukkan jumlah Es (0-5): "));
                customize.add(UtilClass.inputInt("Masukkan jumlah Gula (0-5): "));
                customize.add(UtilClass.inputInt("Masukkan jumlah Karamel (0-5): "));
            } else if (selectedItem instanceof Teh) {
                customize.add(UtilClass.inputInt("Masukkan jumlah Boba (0-3): "));
                customize.add(UtilClass.inputInt("Masukkan jumlah Es (0-5): "));
                customize.add(UtilClass.inputInt("Masukkan jumlah Gula (0-5): "));
                customize.add(UtilClass.inputInt("Masukkan jumlah Karamel (0-5): "));
            } else if (selectedItem instanceof Roti) {
                customize.add(UtilClass.inputInt("Masukkan jumlah Gula (0-5): "));
                customize.add(UtilClass.inputInt("Masukkan jumlah Meses (0-5): "));
                customize.add(UtilClass.inputInt("Masukkan jumlah Karamel (0-5): "));
            }
            selectedItem.customize(customize);

            view.tampilkanMenuUkuran();
            int pilihanUkuran = UtilClass.inputInt("Pilihan (1-3): ");
            EnumUkuran ukuran = EnumUkuran.SMALL;
            if (pilihanUkuran == 2) ukuran = EnumUkuran.MEDIUM;
            else if (pilihanUkuran == 3) ukuran = EnumUkuran.LARGE;

            double hargaSatuanCustom = selectedItem.hitungHargaTotal();
            double biayaTambahanKustomSatuan = hargaSatuanCustom - selectedItem.getHargaBase();
            double totalHarga = selectedItem.hitungHargaTotal(jumlah, ukuran) + (biayaTambahanKustomSatuan * jumlah);

            selectedItem.kurangiStok(jumlah);

            view.cetakStruk(selectedItem, jumlah, totalHarga);
            lanjut = false;
        }
    }

    private MenuItem cariMenu(String id) throws MenuTidakDitemukanException {
        for (MenuItem item : daftarMenu) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        throw new MenuTidakDitemukanException("Menu dengan ID " + id + " tidak ditemukan!");
    }
}