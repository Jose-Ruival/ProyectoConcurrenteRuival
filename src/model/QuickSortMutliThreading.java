package model;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

// Clase que extiende RecursiveTask para implementar QuickSort paralelo
public class QuickSortMutliThreading
    extends RecursiveTask<Integer> {

    // Variables de instancia para almacenar los índices de inicio y fin
    // y el array a ordenar
    int start, end;
    int[] arr;

    /**
     * Método de partición que encuentra un pivote aleatorio y
     * divide el array en dos partes.
     * Existen diferentes algoritmos de partición posibles.
     * @param start índice de inicio
     * @param end índice final
     * @param arr array a particionar
     * @return posición final del pivote
     */
    private int partition(int start, int end,
                        int[] arr)
    {
        // Inicialización de índices para recorrer el array
        int i = start, j = end;

        // Selecciona un pivote aleatorio dentro del rango [i,j]
        int pivote = new Random()
                         .nextInt(j - i)
                     + i;

        // Intercambia el pivote con el elemento final del array
        int t = arr[j];
        arr[j] = arr[pivote];
        arr[pivote] = t;
        j--;

        // Comienza el proceso de partición
        while (i <= j) {
            // Si el elemento actual es menor o igual al pivote,
            // avanza el índice i
            if (arr[i] <= arr[end]) {
                i++;
                continue;
            }

            // Si el elemento actual es mayor o igual al pivote,
            // retrocede el índice j
            if (arr[j] >= arr[end]) {
                j--;
                continue;
            }

            // Intercambia elementos si están en el lado incorrecto
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j--;
            i++;
        }

        // Coloca el pivote en su posición correcta final
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }

    // Constructor que inicializa los valores de inicio, fin y el array
    public QuickSortMutliThreading(int start,
                                   int end,
                                   int[] arr)
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    // Método principal que implementa la lógica paralela del QuickSort
    @Override
    protected Integer compute()
    {
        // Caso base: si start >= end, el subarray está ordenado
        if (start >= end)
            return null;

        // Encuentra el punto de partición
        int p = partition(start, end, arr);

        // Crea las subtareas para las mitades izquierda y derecha
        QuickSortMutliThreading left
            = new QuickSortMutliThreading(start,
                                          p - 1,
                                          arr);

        QuickSortMutliThreading right
            = new QuickSortMutliThreading(p + 1,
                                          end,
                                          arr);

        // Ejecuta la mitad izquierda en un nuevo hilo (fork)
        left.fork();
        // Ejecuta la mitad derecha en el hilo actual
        right.compute();

        // Espera a que termine la ejecución del hilo izquierdo
        left.join();

        // Retorna null ya que no necesitamos un valor de retorno
        return null;
    }
}
