package com.nurkiewicz.download;

import org.junit.Test;
import static org.junit.Assert.*;

public class InterestCalculatorTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testCalculateAPRFromAPY() {
        // Test with annual compounding
        assertEquals(0.05, InterestCalculator.calculateAPRFromAPY(0.05, 1), DELTA);
        
        // Test with monthly compounding
        assertEquals(0.0487, InterestCalculator.calculateAPRFromAPY(0.05, 12), DELTA);
        
        // Test with daily compounding
        assertEquals(0.0485, InterestCalculator.calculateAPRFromAPY(0.05, 365), DELTA);
    }

    @Test
    public void testCalculateAPYFromAPR() {
        // Test with annual compounding
        assertEquals(0.05, InterestCalculator.calculateAPYFromAPR(0.05, 1), DELTA);
        
        // Test with monthly compounding
        assertEquals(0.0512, InterestCalculator.calculateAPYFromAPR(0.05, 12), DELTA);
        
        // Test with daily compounding
        assertEquals(0.0513, InterestCalculator.calculateAPYFromAPR(0.05, 365), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAPR() {
        InterestCalculator.calculateAPYFromAPR(-0.05, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAPY() {
        InterestCalculator.calculateAPRFromAPY(-0.05, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCompoundingPeriods() {
        InterestCalculator.calculateAPRFromAPY(0.05, 0);
    }
}