public class Sort <T extends Comparable<T>> {
    /** Lecture quick sort's subroutines - Main function and private helper functions */
    public void quickSortClass(T[] array){
        quickSort(array, 0, array.length - 1, "Class");
    }

    private void quickSort(T[] array, int p, int r, String type){
        int pivotIndex;
        if (r - p > 2){
            if (type.equals("Class")) {
                pivotIndex = partitionClass(array, p, r);
            }
            else {
                pivotIndex = partitionRecitation(array, p, r);
            }
            quickSort(array, p, pivotIndex - 1, type);
            quickSort(array, pivotIndex, r, type);
        }
        else {
            bubbleSort(array);
        }
    }

    private int partitionClass(T[] array, int p, int r){
        T pivot = array[r];
        int rightIndex = r, leftIndex = p - 1;
        T temp;

        while (true){
            do {
                rightIndex --;
            } while (rightIndex >= p && array[rightIndex].compareTo(pivot) > 0);

            do {
                leftIndex ++;
            } while (leftIndex <= r && array[leftIndex].compareTo(pivot) < 0);

            if (leftIndex < rightIndex){
                temp = array[leftIndex];
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = temp;
            }

            else{
                temp = array[r];
                array[r] = array[rightIndex+1];
                array[rightIndex+1] = temp;
                return rightIndex+1;
            }
        }
    }

    private void bubbleSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (array[j].compareTo(array[j + 1]) > 0 ) {
                    // swap arr[j+1] and arr[j]
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
    }

    public void quickSortRecitation(T[] array){
        quickSort(array, 0, array.length - 1, "Recitation");
    }

    private int partitionRecitation(T[] array, int p, int r){
        T pivot = array[r];
        int left = p - 1;
        T temp;

        for (int right = p; right < r - 1; right++) {
            if (array[right].compareTo(pivot) <= 0){
                left ++;
                // swap array[left] <-> array[right]
                temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
            // swap array[left + 1] <-> pivot
            temp = array[left + 1];
            array[left + 1] = array[r];
            array[r] = temp;
        }
        return left + 1;
    }

    public static void radixSort(int[] array, int base){
        // Find the number with the max digits
        int maxNumber = 0;

        for (int i = 0; i < array.length; i++){
            if (array[i] > maxNumber){
                maxNumber = array[i];
            }
        }

        for (int digit = 1; maxNumber / digit > 0; digit *= base) {
            countingSort(array, base, digit);
        }
    }

    private static void countingSort(int[] arrA, int base, int digit){
        int[] arrB = new int[arrA.length];
        int[] arrC = new int[base];

        for (int i = 0; i < base; i++){
            arrC[i] = 0;
        }

        for (int j = 1; j < arrA.length; j++){
            arrC[(arrA[j] / digit) % base] ++;
        }

        for (int i = 1; i < base; i++){
            arrC[i] += arrC[i-1];
        }

        for (int j = arrA.length - 1; j >= 0; j--){
            arrB[arrC[(arrA[j] / digit) % base]] = arrA[j];
            arrC[(arrA[j] / digit) % base] --;
        }

        // copy array B to array A
        for (int i = 0; i < arrA.length; i++){
            arrA[i] = arrB[i];
        }
    }

    public void mergeSortRecursive(T[] array){
        return;
    }

    public void mergeSortIterative(T[] array){
        return;
    }

    public void setNaiveSortThreshold(int threshold){
        return;
    }



}