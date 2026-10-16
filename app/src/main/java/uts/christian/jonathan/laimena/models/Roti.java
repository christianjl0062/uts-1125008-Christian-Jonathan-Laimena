package uts.christian.jonathan.laimena.models;

public class Roti extends MenuItem implements Customizable {
    private int gula;
    private int meses;
    private int karamel;

    public Roti(String id, String nama, double harga, int stok) {
        super(id, nama, harga, stok);
    }

    public int getGula() {
        return gula;
    }
    public void setGula(int gula) {
        this.gula = gula;
    }
    public int getMeses() {
        return meses;
    }
    public void setMeses(int meses) {
        this.meses = meses;
    }
    public int getKaramel() {
        return karamel;
    }
    public void setKaramel(int karamel) {
        this.karamel = karamel;
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
    public void customize(java.util.List<String> customizations) {
        if (customizations.size() >= 3) {
            this.gula = Integer.parseInt(customizations.get(0));
            this.meses = Integer.parseInt(customizations.get(1));
            this.karamel = Integer.parseInt(customizations.get(2));
        }
    }

    @Override
    public double hitungHargaTotal(int jumlah) {
        double tambahan = (gula * 500) + (meses * 2000) + (karamel * 3000);
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
        return "Roti: " + getName() + ", Harga: " + hitungHargaTotal(1);
    }
}
