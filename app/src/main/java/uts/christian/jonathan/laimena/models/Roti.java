package uts.christian.jonathan.laimena.models;

import java.util.List;

public class Roti extends MenuItem {
    private int gula;       // 0-5 (free)
    private int meses;      // 0-5 (+500)
    private int karamel;    // 0-5 (+3000)

    public Roti(String id, String nama, double hargaBase, int stok) {
        super(id, nama, hargaBase, stok);
    }

    @Override
    public void customize(List<Integer> customizations) {
        if (customizations.size() >= 3) {
            this.gula = customizations.get(0);
            this.meses = customizations.get(1);
            this.karamel = customizations.get(2);
        }
    }

    @Override
    public void customize(int[] customizations) {
        if (customizations.length >= 3) {
            this.gula = customizations[0];
            this.meses = customizations[1];
            this.karamel = customizations[2];
        }
    }

    @Override
    public double hitungHargaTotal() {
        double tambahanMeses = meses * 500;
        double tambahanKaramel = karamel * 3000;
        return hargaBase + tambahanMeses + tambahanKaramel;
    }

    @Override
    public String getOrderDetail() {
        return String.format("Roti: %s | Kustomisasi -> Gula: %d, Meses: %d, Karamel: %d", nama, gula, meses, karamel);
    }
}