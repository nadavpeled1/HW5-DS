
public class Sort <T extends Comparable<T>> {
    /** Lecture quick sort's subroutines - Main function and private helper functions */
    public void quickSortClass(T[] array){
        quickSortClass(array, 0, array.length - 1);
    }

    private void quickSortClass(T[] array, int p, int r){
        int pivotIndex;
        if (r - p > 2){
            pivotIndex = partition(array, p, r);
            quickSortClass(array, p, pivotIndex - 1);
            quickSortClass(array, pivotIndex, r);
        }
        else {
//            todo: check for a simple sort algorithm to attach
        }
    }

    private int partition(T[] array, int p, int r){
        T rightCell = array[r];
        int end = r, start = p - 1;
        T temp;

        while (true){
            while (array[end].compareTo(rightCell) < 0 || end < p){
                end = end - 1;
            }
            while (array[start].compareTo(rightCell) > 0 || start > r){
                start = start + 1;
            }

            if (start < end){
                temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }
            else{
                temp = array[r];
                array[r] = array[end+1];
                array[end+1] = temp;
                return end+1;
            }
        }
    }

    public void quickSortRecitation(T[] array){
        return;
    }

    public void radixSort(int[] array, int base){
        return;
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