package uts.christian.jonathan.laimena.models;

import java.util.List;

public class Teh extends MenuItem {
    private int boba;
    private int es;
    private int gula;
    private int karamel; 

    public Teh(String id, String nama, double hargaBase, int stok) {
        super(id, nama, hargaBase, stok);
    }

    @Override
    public void customize(List<Integer> customizations) {
        if (customizations.size() >= 4) {
            this.boba = customizations.get(0);
            this.es = customizations.get(1);
            this.gula = customizations.get(2);
            this.karamel = customizations.get(3);
        }
    }

    @Override
    public void customize(int[] customizations) {
        if (customizations.length >= 4) {
            this.boba = customizations[0];
            this.es = customizations[1];
            this.gula = customizations[2];
            this.karamel = customizations[3];
        }
    }

    @Override
    public double hitungHargaTotal() {
        double tambahanBoba = boba * 1000;
        double tambahanKaramel = karamel * 3000;
        return hargaBase + tambahanBoba + tambahanKaramel;
    }

    @Override 
    public double hitungHargaTotal(int jumlah) {
        double tambahanBoba = boba * 1000;
        double tambahanKaramel = karamel * 3000;
        return (hargaBase + tambahanBoba + tambahanKaramel) * jumlah;
    }

    @Override 
    public double hitungHargaTotal(int jumlah, String kodePromo) {
        double total = hitungHargaTotal(jumlah);
        if (kodePromo != null && kodePromo.equalsIgnoreCase("PROMOHEMAT")) {
            total *= 0.85;
        }
        return total;
    }

    @Override 
    public double hitungHargaTotal(int jumlah, EnumUkuran ukuran) {
        double hargaPerItem = hitungHargaTotal() + ukuran.getTambahanHarga();
        return hargaPerItem * jumlah;
    }

    @Override
    public String getOrderDetail() {
        return String.format("Teh: %s | Kustomisasi -> Boba: %d, Es: %d, Gula: %d, Karamel: %d", nama, boba, es, gula, karamel);
    }
}