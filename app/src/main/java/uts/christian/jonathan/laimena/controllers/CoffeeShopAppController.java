package uts.christian.jonathan.laimena.controllers;
import java.util.List;

import uts.christian.jonathan.laimena.models.MenuItem;
import uts.christian.jonathan.laimena.util.UtilClass;
import uts.christian.jonathan.laimena.views.CoffeeShopAppConsoleView;
import uts.christian.jonathan.laimena.exceptions.InputTidakValidException;
import uts.christian.jonathan.laimena.exceptions.MenuTidakDitemukanException;
import uts.christian.jonathan.laimena.exceptions.StokTidakCukupException;
import uts.christian.jonathan.laimena.models.*;



public class CoffeeShopAppController {
    private List<MenuItem> daftarMenu;
    private CoffeeShopAppConsoleView view;

    public CoffeeShopAppController(CoffeeShopAppConsoleView view, List<MenuItem> daftarMenu) {
        this.view = view;
        this.daftarMenu = daftarMenu;
    }

    public void runApp() {
        boolean lanjut = true;
        while (lanjut) {
            view.tampilkanDaftarMenu(daftarMenu);

            MenuItem selectedItem = null;
            while (selectedItem == null) {
                try {
                    String idMenu = view.mintaInputIdMenu();
                    selectedItem = cariMenu(idMenu);
                } catch (MenuTidakDitemukanException e) {
                    view.tampilkanPesan(e.getMessage() + " Silakan coba lagi.");
                }
            }

            int jumlah = 0;
            boolean jumlahValid = false;
            while (!jumlahValid) {
                try {
                    jumlah = view.mintaInputJumlah();
                    if (jumlah <= 0) {
                        throw new InputTidakValidException("Jumlah pesanan harus lebih besar dari 0!");
                    }
                    if (jumlah > selectedItem.getStok()) {
                        throw new StokTidakCukupException("Stok tidak cukup! Sisa stok: " + selectedItem.getStok());
                    }
                    jumlahValid = true;
                } catch (InputTidakValidException | StokTidakCukupException e) {
                    view.tampilkanPesan(e.getMessage() + " Silakan input ulang.");
                }
            }

            // 3. Kustomisasi berdasarkan tipe produk
            List<Integer> customizeMenu = new ArrayList<>();
            if (selectedItem instanceof Kopi) {
                customizeMenu.add(view.mintaKustomisasi("Rum", 0, 1));
                customizeMenu.add(view.mintaKustomisasi("Es", 0, 5));
                customizeMenu.add(view.mintaKustomisasi("Gula", 0, 5));
                customizeMenu.add(view.mintaKustomisasi("Karamel", 0, 5));
            } else if (selectedItem instanceof Teh) {
                customizeMenu.add(view.mintaKustomisasi("Boba", 0, 3));
                customizeMenu.add(view.mintaKustomisasi("Es", 0, 5));
                customizeMenu.add(view.mintaKustomisasi("Gula", 0, 5));
                customizeMenu.add(view.mintaKustomisasi("Karamel", 0, 5));
            } else if (selectedItem instanceof Roti) {
                customizeMenu.add(view.mintaKustomisasi("Gula", 0, 5));
                customizeMenu.add(view.mintaKustomisasi("Meses", 0, 5));
                customizeMenu.add(view.mintaKustomisasi("Karamel", 0, 5));
            }
            selectedItem.customize(customizeMenu);

            int pilihanUkuran = view.mintaPilihanUkuran();
            EnumUkuran ukuran = EnumUkuran.SMALL;
            if (pilihanUkuran == 2) ukuran = EnumUkuran.MEDIUM;
            else if (pilihanUkuran == 3) ukuran = EnumUkuran.LARGE;

            double hargaSatuanCustom = selectedItem.hitungHargaTotal();
            double totalHarga = selectedItem.hitungHargaTotal(jumlah, ukuran) + ((hargaSatuanCustom - selectedItem.getHargaBase()) * jumlah);

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