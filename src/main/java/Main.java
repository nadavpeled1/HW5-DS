import java.util.Random;
public class Main {
    private static final int NUMITER = 10; //amount of checks for all the algos,
    private static final int[] inputSizes = {5000, 10000, 15000};
    // For example:
    // NUMITER = 5 will repeat making a random array and sort it by each algorithm, 5 times of each input size
    public static void main(String[] args) {
        Sort sorter = new Sort();
        Random random = new Random();

        for (int n : inputSizes) {
            double[][] durationList = new double[6][NUMITER];

            for (int k = 0; k < NUMITER; k++) {
                Integer[] array = new Integer[n];
                Integer[] copy;

                for (int i = 0; i < n; i++) {
                    array[i] = random.nextInt(n);
                }

                for (int i = 0; i < 6; i++) {
                    copy = copyIntegerArray(array);

                    long startTime = System.currentTimeMillis();
                    switch (i) {
                        case 0:
                            sorter.quickSortClass(copy);
                            break;
                        case 1:
                            sorter.quickSortRecitation(copy);
                            break;
                        case 2:
                            sorter.mergeSortRecursive(copy);
                            break;
                        case 3:
                            sorter.mergeSortIterative(copy);
                            break;
                        case 4://this one gets an other input since it works specificly with int[] type
                            sorter.radixSort(copyIntegerTOint(copy),3);
                            break;
                        case 5://naive Sort
                            sorter.bubbleSort(copy);
                            break;
                    }
                    long endTime = System.currentTimeMillis();
                    //store the runtime of the current run to the current sort Algo session
                    durationList[i][k] = ((double) endTime - startTime );
                }
            }

            // Print average durations for each algorithm and input size
            System.out.println("Input Size: " + n);
            System.out.println("Algorithm\tAverage Duration (ms)");
            for (int i = 0; i < 6; i++) {
                double averageDuration = calculateAverage(durationList[i]);
                System.out.println(getAlgorithmName(i) + "\t\t" + averageDuration);
            }
            System.out.println();
        }
    }

    public static Integer[] copyIntegerArray(Integer[] a) {
        Integer[] copy = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            copy[i] = a[i];
        }
        return copy;
    }

    public static int[] copyIntegerTOint(Integer[] a) {
        int[] copy = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            copy[i] = a[i];
        }
        return copy;
    }


    private static double calculateAverage(double[] array) {
        double sum = 0;
        for (double value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    private static String getAlgorithmName(int algorithm) {
        switch (algorithm) {
            case 0:
                return "Class Quick Sort";
            case 1:
                return "Recitation Quick Sort";
            case 2:
                return "Merge Sort Recursive";
            case 3:
                return "Iterative Merge Sort";
            case 4:
                return "Radix Sort";
            case 5:
                return "Bubble Sort";
            default:
                return "Unknown Algorithm";
        }
    }
}
