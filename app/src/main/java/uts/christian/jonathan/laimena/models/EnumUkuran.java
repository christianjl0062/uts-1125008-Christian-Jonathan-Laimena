package uts.christian.jonathan.laimena.models;

public enum EnumUkuran {
    SMALL(0),
    MEDIUM(2000),
    LARGE(4000);

    private final double tambahanHarga;

    EnumUkuran(double tambahanHarga) {
        this.tambahanHarga = tambahanHarga;
    }

    public double getTambahanHarga() {
        return tambahanHarga;
    }
}
