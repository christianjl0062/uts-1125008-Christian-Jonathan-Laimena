package uts.christian.jonathan.laimena.models;

public abstract class MenuItem implements Customizable {
    protected String id;
    protected String nama;
    protected double hargaBase;
    protected int stok;

    public MenuItem(String id, String nama, double hargaBase, int stok) {
        this.id = id;
        this.nama = nama;
        this.hargaBase = hargaBase;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public double getHargaBase() {
        return hargaBase;
    }
    public int getStok() {
        return stok;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setHargaBase(double hargaBase) {
        this.hargaBase = hargaBase;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }

    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }

    public double hitungHargaTotal(int jumlah) {
        return hitungHargaTotal() * jumlah;
    }

    public double hitungHargaTotal(int jumlah, String kodePromo) {
        double total = hitungHargaTotal(jumlah);
        if (kodePromo != null && kodePromo.equalsIgnoreCase("ContohPromoHemat")) {
            total *= 0.85;
        }
        return total;
    }

    public double hitungHargaTotal(int jumlah, EnumUkuran ukuran) {
        double hargaPerItem = hitungHargaTotal() + ukuran.getTambahanHarga();
        return hargaPerItem * jumlah;
    }

    public abstract double hitungHargaTotal();
    public abstract String getOrderDetail();
}