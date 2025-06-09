package test;

import model.QuickSort;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        int n = arr.length;

        // Llenando el arreglo con números aleatorios
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 100); // Int randoms entre 0 y 99
        }

        QuickSort test = new QuickSort();

        long startTime = System.nanoTime(); // Inicia medición

        test.quickSort(arr, 0, n - 1);

        long endTime = System.nanoTime(); // Termina medición

        System.out.println();

        long duration = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
    }
}
