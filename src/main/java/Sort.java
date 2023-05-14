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
        mergeSortRecursive(array,0, array.length-1);
    }
    public void mergeSortRecursive(T[] arr, int left, int right){
        int mid;
        if(left<right){
            mid = (left+right) / 2;
            mergeSortRecursive(arr,left,mid);
            mergeSortRecursive(arr,mid+1,right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(T[] arr, int left, int mid, int right) {
        //size of subbarrays
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

        int l=0,r=0;
        int k = left;
        while(l+r < (n1 + n2)){
            if(l==n1){//finished left -> insert from right
                arr[k++] = rightAr[r++];
            } else if (r==n2) {//else, if finished right -> insert from left
                arr[k++] = leftAr[l++];
            }
            else{//both sub-arr have elements
                arr[k++] = leftAr[l].compareTo(rightAr[r])<=0 ? leftAr[l++] : rightAr[r++];
            }
        }
    }

    public void mergeSortIterative(T[] array) {
        int n = array.length;

        // Divide the array into subarrays of size 1, then merge them pairwise
        for (int size = 1; size < n; size *= 2) {
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1); //prevent out of bound at last iteration
                merge(array, left, mid, right);
            }
        }
    }
    
    public void setNaiveSortThreshold(int threshold){
        return;
    }



}
