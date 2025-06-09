package test;

import java.util.concurrent.ForkJoinPool;
import model.QuickSortMutliThreading;

public class QuickSortMultiThreadingTest {
    public static void main(String[] args) {
        int[] arr = new int[100000]; // Aumentando el tamaño del arreglo
        int n = arr.length;

        // Llenando el arreglo con números aleatorios
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 100); // Int randoms entre 0 y 99
        }

        ForkJoinPool pool = ForkJoinPool.commonPool();

        long startTime = System.nanoTime(); // Inicia medición

        pool.invoke(new QuickSortMutliThreading(0, n - 1, arr));

        long endTime = System.nanoTime(); // Termina medición

        System.out.println();

        long duration = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
    }
}
