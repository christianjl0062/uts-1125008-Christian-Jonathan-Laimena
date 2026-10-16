package uts.christian.jonathan.laimena.models;
import java.util.List;

public class Kopi extends MenuItem {
    private int rum;
    private int es;
    private int gula;
    private int karamel;

    public Kopi(String id, String nama, double hargaBase, int stok) {
        super(id, nama, hargaBase, stok);
    }

    public int getRum() {
        return rum;
    }
    public void setRum(int rum) {
        this.rum = rum;
    }
    public int getEs() {
        return es;
    }
    public void setEs(int es) {
        this.es = es;
    }
    public int getGula() {
        return gula;
    }
    public void setGula(int gula) {
        this.gula = gula;
    }
    public int getKaramel() {
        return karamel;
    }
    public void setKaramel(int karamel) {
        this.karamel = karamel;
    }

    @Override
    public void customize(List<Integer> customizations) {
        if (customizations.size() >= 4) {
            this.rum = customizations.get(0);
            this.es = customizations.get(1);
            this.gula = customizations.get(2);
            this.karamel = customizations.get(3);
        }
    }

    @Override
    public void customize(int[] customizations) {
        if (customizations.length >= 4) {
            this.rum = customizations[0];
            this.es = customizations[1];
            this.gula = customizations[2];
            this.karamel = customizations[3];
        }
    }

    @Override
    public double hitungHargaTotal() {
        double tambahanRum = rum * 3000;
        double tambahanKaramel = karamel * 3000;
        return hargaBase + tambahanRum + tambahanKaramel;
    }

    @Override
    public String getOrderDetail() {
        return String.format("Kopi: %s | Kustomisasi -> Rum: %d, Es: %d, Gula: %d, Karamel: %d", nama, rum, es, gula, karamel);
    }
}
