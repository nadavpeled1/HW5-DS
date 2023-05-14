import java.lang.reflect.Array;

public class Sort<T extends Comparable<T>> {
    private int threshold = 1; //a threshold to determine if to use the naive sort or the algorithm
    /** Lecture quick sort's subroutines - Main function and private helper functions */
    public void quickSortClass(T[] array){
        quickSort(array, 0, array.length - 1, "Class");
    }

    private void quickSort(T[] array, int p, int r, String type){
        int pivotIndex;
        if (r - p > threshold - 1){
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
            bubbleSort(array,p,r);
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

            if (leftIndex < rightIndex){ //if found 2 elements to swap
                temp = array[leftIndex]; //Swap them
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = temp;
            }

            else{ //leftIndex and RightIndex crossed,
                // swap pivot with R+1
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
                    swap(array,j,j+1);
                }
    }

    private void bubbleSort(T[] array, int start, int end) {
        for (int i = start; i <= end; i++) {
            for (int j = start; j < end - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
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

        for (int i = 0; i < base; i++){ //we can delete that, by default java puts 0
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
        mergeSortRecursive(array, 0, array.length-1);
    }

    public void mergeSortRecursive(T[] arr, int left, int right){
        if (left < right) {
            if (right - left + 1 <= threshold) {
                bubbleSort(arr, left, right);
            } else {
                int mid = (left + right) / 2;
                mergeSortRecursive(arr, left, mid);
                mergeSortRecursive(arr, mid + 1, right);
                merge(arr, left, mid, right);
            }
        }
    }

    private void merge(T[] arr, int left, int mid, int right) {
        //size of sub-arrays
        int n1 = mid - left + 1;
        int n2 = right - mid;
        T[] leftAr = (T[]) Array.newInstance(Comparable.class, n1);
        T[] rightAr = (T[]) Array.newInstance(Comparable.class, n2);

        for(int i=0;i<n1;i++){
            leftAr[i]=arr[left+i];
        }
        for(int i=0;i<n2;i++){
            rightAr[i]=arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftAr[i].compareTo(rightAr[j]) <= 0) {
                arr[k++] = leftAr[i++];
            } else {
                arr[k++] = rightAr[j++];
            }
        }
        while (i < n1) {
            arr[k++] = leftAr[i++];
        }

        while (j < n2) {
            arr[k++] = rightAr[j++];
        }
    }

    public void mergeSortIterative(T[] array) {
        int n = array.length;
        if(n<=threshold){
            bubbleSort(array);
        }
        else{
            // Divide the array into sub-arrays of size 1, then merge them pairwise
            for (int size = 1; size < n; size *= 2) {
                for (int left = 0; left < n - size; left += 2 * size) {
                    int mid = left + size - 1;
                    int right = Math.min(left + 2 * size - 1, n - 1); //prevent out of bound at last iteration
                    merge(array, left, mid, right);
                }
            }
        }
    }
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void setNaiveSortThreshold(int threshold){
        this.threshold = threshold;
    }


}
