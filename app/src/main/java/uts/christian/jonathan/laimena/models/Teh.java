package uts.christian.jonathan.laimena.models;

public class Teh extends MenuItem implements Customizable {
    private final String nama;
    private int boba;
    private int es;
    private int gula;
    private int karamel;

    public Teh(String id, String nama, double harga, int stok) {
        super(id, nama, harga, stok);
        this.nama = nama;
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
    public void customize(java.util.List<String> customizations) {
        if (customizations.size() >= 4) {
            this.boba = Integer.parseInt(customizations.get(0));
            this.es = Integer.parseInt(customizations.get(1));
            this.gula = Integer.parseInt(customizations.get(2));
            this.karamel = Integer.parseInt(customizations.get(3));
        }
    }


    @Override
    public double hitungHargaTotal(int jumlah) {
        double tambahan = (boba * 1000) + (karamel * 3000);
        return (getHargaBase() + tambahan) * jumlah;
    }

    @Override 
    public double hitungHargaTotal(int jumlah, String kodePromo) {
        double hargaTotal = hitungHargaTotal(jumlah);
        if (kodePromo.equalsIgnoreCase("DISKON10")) {
            hargaTotal *= 0.9;
        }
        return hargaTotal;
    }

    @Override
    public double hitungHargaTotal(int jumlah, EnumUkuran ukuran) {
        double hargaTotal = hitungHargaTotal(jumlah);
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
        return String.format("- Teh: %s | Kustomisasi -> Boba: %d, Es: %d, Gula: %d, Karamel: %d", nama, boba, es, gula, karamel);
    }
}
