package uts.christian.jonathan.laimena.views;

import uts.christian.jonathan.laimena.util.UtilClass;

public class CoffeeShopAppConsoleView {
    public int getMenuInput() {
        return UtilClass.readInt();
    }

    public String getInputString(String prompt) {
        System.out.print(prompt);
        return UtilClass.readString();
    }

    public double getInputDouble(String prompt) {
        System.out.print(prompt);
        return UtilClass.readDouble();
    }

    public int getInputInt(String prompt) {
        System.out.print(prompt);
        return UtilClass.readInt();
    }
}
