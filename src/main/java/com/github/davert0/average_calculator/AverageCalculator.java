package com.github.davert0.average_calculator;

public class AverageCalculator {
    

    public double calculateAverage(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }
        
        // ПРОБЛЕМА 1: Использование int вместо long для суммы
        // Это может привести к переполнению при больших числах
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        
        // ПРОБЛЕМА 2: Неправильное приведение типов
        // Может привести к потере точности
        return sum / numbers.length;
    }
    

} 