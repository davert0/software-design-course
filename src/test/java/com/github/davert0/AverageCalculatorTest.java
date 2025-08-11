package com.github.davert0;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.github.davert0.average_calculator.AverageCalculator;


class AverageCalculatorTest {
    
    private AverageCalculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new AverageCalculator();
    }
    
    
    @Test
    void shouldCalculateAverageForPositiveNumbers() {
        int[] numbers = {1, 2, 3, 4, 5};
        double expected = 3.0;
        
        double result = calculator.calculateAverage(numbers);
        
        assertEquals(expected, result, 0.001);
    }
    
    @Test
    void shouldCalculateAverageForNegativeNumbers() {
        int[] numbers = {-1, -2, -3, -4, -5};
        double expected = -3.0;
        
        double result = calculator.calculateAverage(numbers);
        
        assertEquals(expected, result, 0.001);
    }
    
    @Test
    void shouldCalculateAverageForMixedNumbers() {
        int[] numbers = {-2, 0, 2, 4, 6};
        double expected = 2.0;
        
        double result = calculator.calculateAverage(numbers);
        
        assertEquals(expected, result, 0.001);
    }
    
    @Test
    void shouldCalculateAverageForSingleNumber() {
        int[] numbers = {42};
        double expected = 42.0;
        
        double result = calculator.calculateAverage(numbers);
        
        assertEquals(expected, result, 0.001);
    }
    
    @Test
    void shouldCalculateAverageForTwoNumbers() {
        int[] numbers = {10, 20};
        double expected = 15.0;
        
        double result = calculator.calculateAverage(numbers);
        
        assertEquals(expected, result, 0.001);
    }
    
    @Test
    void shouldThrowExceptionForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(null);
        });
    }
    
    @Test
    void shouldThrowExceptionForEmptyArray() {
        int[] numbers = {};
        
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(numbers);
        });
    }
    
    @Test
    void shouldCalculateAverageWithZeros() {
        int[] numbers = {0, 0, 0, 0, 0};
        double expected = 0.0;
        
        double result = calculator.calculateAverage(numbers);
        
        assertEquals(expected, result, 0.001);
    }
    
    
    @Test
    void shouldDemonstrateOverflowProblem() {
        int[] numbers = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        double result = calculator.calculateAverage(numbers);
        
        // Из-за переполнения int результат будет неправильным
        System.out.println("Результат с переполнением: " + result);
        System.out.println("Ожидаемый результат: " + Integer.MAX_VALUE);
        
        assertNotEquals(Integer.MAX_VALUE, result, 0.001);
    }
    
    @Test
    void shouldDemonstratePrecisionLossProblem() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        double result = calculator.calculateAverage(numbers);
        double expected = 5.5; // Правильный результат
        
        System.out.println("Результат: " + result);
        System.out.println("Ожидаемый результат: " + expected);
        
        // Из-за целочисленного деления результат будет 5.0 вместо 5.5
        assertNotEquals(expected, result, 0.001);
    }


    @Test
    void shouldDemonstrateBoundaryCaseProblem() {
        int[] numbers = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        
        double result = calculator.calculateAverage(numbers);
        double expected = (Integer.MAX_VALUE + Integer.MIN_VALUE + 0.0) / 3;
        
        System.out.println("Результат для граничных случаев: " + result);
        System.out.println("Ожидаемый результат: " + expected);
        
        // Из-за переполнения и потери точности результат может быть неправильным
        assertNotEquals(expected, result, 0.001);
    }
    
} 