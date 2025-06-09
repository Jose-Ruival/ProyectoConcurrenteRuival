package model;

public class QuickSort {
    // Función de partición: divide el array en dos partes, 
    // elementos menores y mayores que el pivote
    static int partition(int[] arr, int low, int high) {
        
        // Selecciona el último elemento como pivote
        int pivot = arr[high];
        
        // i es el índice del elemento más pequeño y marca
        // la posición correcta del pivote encontrada hasta ahora
        int i = low - 1;

        // Recorre el array desde low hasta high-1
        // y mueve todos los elementos menores que el pivote
        // hacia la izquierda del array
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        // Mueve el pivote después de los elementos más pequeños
        // y devuelve su posición final
        swap(arr, i + 1, high);  
        return i + 1;
    }

    // Función auxiliar para intercambiar dos elementos en el array
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Implementación principal del algoritmo QuickSort
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            
            // pi es el índice de partición donde queda el pivote
            int pi = partition(arr, low, high);

            // Llamadas recursivas para ordenar:
            // 1. Los elementos menores que el pivote (subarray izquierdo)
            // 2. Los elementos mayores que el pivote (subarray derecho)
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
