package com.github.davert0.grade_calculator;

import java.util.Arrays;
import java.util.List;

public class GradeCalculator {
    static final List<Integer> allowedGrades = Arrays.asList(2, 3, 4, 5);

    public static double calculateAverage(List<Integer> grades) {

        if (grades == null) {
            throw new IllegalArgumentException("List should not be null");
        }

        if (grades.size() == 0) {
            throw new IllegalArgumentException("List should not be empty");
        }

        int sum = 0;

        for (int grade : grades) {
            if (!allowedGrades.contains(grade)) {
                throw new IllegalArgumentException("Grade " + grade + " is not allowed");
            }
            sum += grade;
        }

        return (double) sum / grades.size();

    }
}
