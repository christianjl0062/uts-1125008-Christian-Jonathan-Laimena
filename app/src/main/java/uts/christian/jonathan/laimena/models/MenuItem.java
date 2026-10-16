package uts.christian.jonathan.laimena.models;

public abstract class MenuItem {
    private String id;
    private String name;
    private double hargaBase;
    private int stok;

    public MenuItem(String id, String name, double hargaBase, int stok) {
        this.id = id;
        this.name = name;
        this.hargaBase = hargaBase;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getHargaBase() {
        return hargaBase;
    }
    public void setHargaBase(double hargaBase) {
        this.hargaBase = hargaBase;
    }
    public int getStok() {
        return stok;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }

    public abstract double hitungHargaTotal();
    public abstract String getOrderDetail();
}
