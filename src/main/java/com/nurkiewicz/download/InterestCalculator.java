package com.nurkiewicz.download;

public class InterestCalculator {
    
    /**
     * Calculates Annual Percentage Rate (APR) from Annual Percentage Yield (APY)
     * APR = n * (Math.pow((1 + APY), 1.0/n) - 1)
     * where n is the number of compounding periods per year
     * 
     * @param apy Annual Percentage Yield as decimal (e.g., 0.05 for 5%)
     * @param compoundingPeriodsPerYear Number of times interest is compounded per year
     * @return Annual Percentage Rate as decimal
     */
    public static double calculateAPRFromAPY(double apy, int compoundingPeriodsPerYear) {
        if (apy < 0) {
            throw new IllegalArgumentException("APY cannot be negative");
        }
        if (compoundingPeriodsPerYear <= 0) {
            throw new IllegalArgumentException("Compounding periods must be positive");
        }
        return compoundingPeriodsPerYear * (Math.pow(1 + apy, 1.0/compoundingPeriodsPerYear) - 1);
    }

    /**
     * Calculates Annual Percentage Yield (APY) from Annual Percentage Rate (APR)
     * APY = Math.pow(1 + APR/n, n) - 1
     * where n is the number of compounding periods per year
     * 
     * @param apr Annual Percentage Rate as decimal (e.g., 0.05 for 5%)
     * @param compoundingPeriodsPerYear Number of times interest is compounded per year
     * @return Annual Percentage Yield as decimal
     */
    public static double calculateAPYFromAPR(double apr, int compoundingPeriodsPerYear) {
        if (apr < 0) {
            throw new IllegalArgumentException("APR cannot be negative");
        }
        if (compoundingPeriodsPerYear <= 0) {
            throw new IllegalArgumentException("Compounding periods must be positive");
        }
        return Math.pow(1 + apr/compoundingPeriodsPerYear, compoundingPeriodsPerYear) - 1;
    }
}
