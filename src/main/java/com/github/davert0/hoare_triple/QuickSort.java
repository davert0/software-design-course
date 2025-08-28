package com.github.davert0.hoare_triple;

public class QuickSort {
    
    /*
     Предусловие: arr != null && left >= 0 && right < arr.length
     Постусловие: массив arr[left..right] отсортирован в неубывающем порядке
     Инвариант цикла: после каждого рекурсивного вызова подмассивы
     arr[left..pivotIndex-1] и arr[pivotIndex+1..right] отсортированы
     */
    public static void quickSort(int[] arr, int left, int right) {
        // Проверка предусловия
        if (arr == null || left < 0 || right >= arr.length) {
            throw new IllegalArgumentException("Некорректные параметры");
        }
        
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            
            // Рекурсивно сортируем левую часть
            // Предусловие: left < pivotIndex-1
            // Постусловие: arr[left..pivotIndex-1] отсортирован
            quickSort(arr, left, pivotIndex - 1);
            
            // Рекурсивно сортируем правую часть
            // Предусловие: pivotIndex+1 < right
            // Постусловие: arr[pivotIndex+1..right] отсортирован
            quickSort(arr, pivotIndex + 1, right);
        }
        // Постусловие выполнено: весь массив arr[left..right] отсортирован
    }
    
    /*
     Предусловие: arr != null && left >= 0 && right < arr.length
     Постусловие: 
     1. pivot находится на правильной позиции
     2. все элементы слева от pivot <= pivot
     3. все элементы справа от pivot > pivot
     4. возвращается индекс позиции pivot
     
     Инвариант цикла: после каждой итерации
     arr[left..i] содержит элементы <= pivot
     arr[i+1..j-1] содержит элементы > pivot
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        
        // Инвариант цикла: arr[left..i] <= pivot && arr[i+1..j-1] > pivot
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
            // Инвариант сохраняется: arr[left..i] <= pivot && arr[i+1..j-1] > pivot
        }
        
        // Ставим pivot на правильное место
        swap(arr, i + 1, right);
        
        // Постусловие выполнено:
        // arr[left..i] <= pivot, arr[i+1] = pivot, arr[i+2..right] > pivot
        return i + 1;
    }
    
    /* 
     Метод для обмена элементов
     
     Предусловие: arr != null && i >= 0 && j >= 0 && i < arr.length && j < arr.length
     Постусловие: arr[i] = old(arr[j]) && arr[j] = old(arr[i])
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
