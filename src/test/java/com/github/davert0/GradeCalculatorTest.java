package com.github.davert0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.davert0.grade_calculator.GradeCalculator;

public class GradeCalculatorTest {
    
    @Test
    void shouldCalculateAverageForValidGrades() {
        List<Integer> grades = Arrays.asList(2, 3, 4, 5);
        double expected = 3.5;
        
        double result = GradeCalculator.calculateAverage(grades);
        
        assertEquals(expected, result, 0.001);
    }
    
    @Test
    void shouldCalculateAverageForSameGrades() {
        List<Integer> grades = Arrays.asList(4, 4, 4, 4);
        double expected = 4.0;
        
        double result = GradeCalculator.calculateAverage(grades);
        
        assertEquals(expected, result, 0.001);
    }
    
    @Test
    void shouldCalculateAverageForMixedValidGrades() {
        List<Integer> grades = Arrays.asList(2, 5, 3, 4, 5);
        double expected = 3.8;
        
        double result = GradeCalculator.calculateAverage(grades);
        
        assertEquals(expected, result, 0.001);
    }
    
    @Test
    void shouldThrowExceptionForInvalidGradeBelow() {
        List<Integer> grades = Arrays.asList(1, 3, 4, 5);
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> GradeCalculator.calculateAverage(grades)
        );
        assertEquals("Grade 1 is not allowed", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionForInvalidGradeAbove() {
        List<Integer> grades = Arrays.asList(2, 3, 6, 4);
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> GradeCalculator.calculateAverage(grades)
        );
        assertEquals("Grade 6 is not allowed", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionForNegativeGrade() {
        List<Integer> grades = Arrays.asList(2, 3, -1, 4);
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> GradeCalculator.calculateAverage(grades)
        );
        assertEquals("Grade -1 is not allowed", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionForZeroGrade() {
        List<Integer> grades = Arrays.asList(2, 0, 3, 4);
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> GradeCalculator.calculateAverage(grades)
        );
        assertEquals("Grade 0 is not allowed", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionForNullList() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> GradeCalculator.calculateAverage(null)
        );
        assertEquals("List should not be null", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionForEmptyList() {
        List<Integer> emptyGrades = Collections.emptyList();
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> GradeCalculator.calculateAverage(emptyGrades)
        );
        assertEquals("List should not be empty", exception.getMessage());
    }
}
