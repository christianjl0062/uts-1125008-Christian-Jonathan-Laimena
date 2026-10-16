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
        List<MenuItem> menuCoffeeShop = new ArrayList<>();
        menuCoffeeShop.add(new Kopi("M-01", "Kopi Susu", 18000, 5));
        menuCoffeeShop.add(new Teh("M-02", "Teh Tarik", 12000, 10));
        menuCoffeeShop.add(new Roti("M-03", "Roti Cokelat", 15000, 8));
        return menuCoffeeShop;
    }

    public void runApp() {
        boolean lanjut = true;
        while (lanjut) {
            view.showDaftarMenu(daftarMenu);

            MenuItem selectedMenuItem = null;
            while (selectedMenuItem == null) {
                try {
                    String idMenu = UtilClass.inputString("Masukkan ID Menu yang ingin anda pesan (tuliskan id nya secara benar): ");
                    selectedMenuItem = cariMenu(idMenu);
                } catch (MenuTidakDitemukanException e) {
                    view.showPesan(e.getMessage() + " Maaf pengguna, silahkan coba lagi.");
                }
            }

            int jumlah = 0;
            boolean jumlahPesananValid = false;
            while (!jumlahPesananValid) {
                try {
                    jumlah = UtilClass.inputInt("Masukkan jumlah pesanan: ");
                    if (jumlah <= 0) {
                        throw new InputTidakValidException("Jumlah pesanan harus > 0!");
                    }
                    if (jumlah > selectedMenuItem.getStok()) {
                        throw new StokTidakCukupException("Stok tidak cukup! Sisa stok: " + selectedMenuItem.getStok());
                    }
                    jumlahPesananValid = true;
                } catch (InputTidakValidException | StokTidakCukupException e) {
                    view.showPesan(e.getMessage() + " Maaf pengguna, input nya belum benar.");
                }
            }

            List<Integer> customizeMenuItem = new ArrayList<>();
            if (selectedMenuItem instanceof Kopi) {
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Rum (nilai = 0-1): "));
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Es (nilai = 0-5): "));
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Gula (nilai = 0-5): "));
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Karamel (nilai = 0-5): "));
            } else if (selectedMenuItem instanceof Teh) {
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Boba (nilai = 0-3): "));
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Es (nilai = 0-5): "));
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Gula (nilai = 0-5): "));
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Karamel (nilai = 0-5): "));
            } else if (selectedMenuItem instanceof Roti) {
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Gula (nilai = 0-5): "));
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Meses (nilai = 0-5): "));
                customizeMenuItem.add(UtilClass.inputInt("Masukkan jumlah Karamel (nilai = 0-5): "));
            }
            selectedMenuItem.customize(customizeMenuItem);

            view.showDaftarMenu(daftarMenu);
            int pilihanUkuran = UtilClass.inputInt("Pilihan (1-3): ");
            EnumUkuran ukuran = EnumUkuran.SMALL;

            if (pilihanUkuran == 2) {
                ukuran = EnumUkuran.MEDIUM;
            } else if (pilihanUkuran == 3) {
                ukuran = EnumUkuran.LARGE;
            } else {
                view.showPesan("Error!!! pilihan ukuran anda tidak valid.");
            }

            double hargaSatuanCustom = selectedMenuItem.hitungHargaTotal();
            double biayaTambahanCustomSatuan = hargaSatuanCustom - selectedMenuItem.getHargaBase();
            double totalHarga = selectedMenuItem.hitungHargaTotal(jumlah, ukuran) 
            + (biayaTambahanCustomSatuan * jumlah);

            selectedMenuItem.kurangiStok(jumlah);

            view.cetakStruk(selectedMenuItem, jumlah, totalHarga);
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