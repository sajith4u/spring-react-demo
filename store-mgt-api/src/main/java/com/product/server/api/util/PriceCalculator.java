package com.product.server.api.util;

public class PriceCalculator {

    private static double PERCENTAGE = 100;


    /**
     * Calculate Price for Product Units
     *
     * @param cartonPrice
     * @param unitsPerCarton
     * @param numOfUnits
     * @param incrementPercentage
     * @return
     */
    public static double calculateUnitPrices(double cartonPrice, int unitsPerCarton, int numOfUnits, double incrementPercentage) {
        return numOfUnits * (cartonPrice / unitsPerCarton) * ((incrementPercentage + PERCENTAGE) / PERCENTAGE);
    }


    /**
     * Calculate Price for Cartons
     *
     * @param cartonPrice
     * @param numOfCartons
     * @param discountPercentage
     * @param discountMinCount
     * @return
     */
    public static double calculateCartonPrices(double cartonPrice, int numOfCartons, double discountPercentage, int discountMinCount) {
        return numOfCartons >= discountMinCount ? (numOfCartons * cartonPrice * ((PERCENTAGE - discountPercentage) / PERCENTAGE)) : numOfCartons * cartonPrice;
    }
}
