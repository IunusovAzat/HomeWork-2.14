package org.example;

import java.util.Random;
import java.util.function.Consumer;

public class Sorting {

    private static final Random RANDOM = new Random();

    public static void main (String[] args){
        double timeForBubbleSort = timeForSorting(10,Sorting::sortBubble);
        System.out.println("Время сортировки пузырьком: " + timeForBubbleSort + "мс");
        double timeForSelectionSort = timeForSorting(10,Sorting::sortSelection);
        System.out.println("Время сортировки пузырьком: " + timeForSelectionSort + "мс");
        double timeForInsertionSort = timeForSorting(10,Sorting::sortInsertion);
        System.out.println("Время сортировки пузырьком: " + timeForInsertionSort + "мс");
    }

    private static double timeForSorting(int iteration, Consumer<int[]> sorting){
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(100000);
            long start = System.currentTimeMillis();
            sorting.accept(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum/iteration;
    }

    private static int[] generateArray(int size){
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(-100,100);
        }
        return array;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
