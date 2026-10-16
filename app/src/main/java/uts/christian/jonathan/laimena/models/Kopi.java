package uts.christian.jonathan.laimena.models;
import java.util.List;

public class Kopi extends MenuItem implements Customizable {
    private String rum;
    private String es;
    private String gula;
    private String karamel;

    public Kopi(String id, String nama, double harga, int stok) {
        super(id, nama, harga, stok);
    }

    public String getRum() {
        return rum;
    }
    public void setRum(String rum) {
        this.rum = rum;
    }
    public String getEs() {
        return es;
    }
    public void setEs(String es) {
        this.es = es;
    }
    public String getGula() {
        return gula;
    }
    public void setGula(String gula) {
        this.gula = gula;
    }
    public String getKaramel() {
        return karamel;
    }
    public void setKaramel(String karamel) {
        this.karamel = karamel;
    }

    @Override 
    public double hitungHargaTotal(int jumlah) {
        return getHargaBase() * jumlah;
    }

    @Override
    public double hitungHargaTotal(int jumlah, String kodePromo) {
        double hargaTotal = getHargaBase() * jumlah;
        if (kodePromo.equalsIgnoreCase("DISKON10")) {
            hargaTotal *= 0.9;
        }
        return hargaTotal;
    }

    @Override
    public double hitungHargaTotal(int jumlah, EnumUkuran ukuran) {
        double hargaTotal = getHargaBase() * jumlah;
        switch (ukuran) {
            case SMALL:
                hargaTotal *= 0.9;
                break;
            case MEDIUM:
                hargaTotal *= 1.0;
                break;
            case LARGE:
                hargaTotal *= 1.2;
                break;
        }
        return hargaTotal;
    }

    @Override 
    public String getOrderDetail() {
        return "Kopi: " + getName() + ", Harga: " + hitungHargaTotal(1);
    }

    @Override
    public void customize(List<String> customizations) {
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
            this.rum = String.valueOf(customizations[0]);
            this.es = String.valueOf(customizations[1]);
            this.gula = String.valueOf(customizations[2]);
            this.karamel = String.valueOf(customizations[3]);
        }
    }
}
