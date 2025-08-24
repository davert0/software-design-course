package com.github.davert0.hoare_triple;

public class LoopInvariant {

    // {arr.length > 0} findMax(arr) {result = max(arr)}

    public static int findMax(int[] arr) {
        if (arr.length <= 0) {
            throw new IllegalArgumentException("list should not be empty");
        }

        // max всегда хранит максимальный элемент для текущей итерации
        int max = arr[0];

        for (int n : arr) {
            // если итый элемент больше текущего максимального,
            // он становится новым максимальным, инвариант сохраняется
            if (n > max) {
                max = n;
            }
            // иначе, max продолжает сохранять максимальный элемент для текущей итерации
        }

        return max;
    }

}
