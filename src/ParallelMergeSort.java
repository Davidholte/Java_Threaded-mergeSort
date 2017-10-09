import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Dave on 13/02/2017.
 */
public class ParallelMergeSort {

    /** Method call */
    public static void parallelSort(Comparable[] list) {
        RecursiveAction mainTask = new SortTask(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    /** Sorting method, called recursively */
    private static class SortTask extends RecursiveAction {
        private final int THRESHOLD = 16;
        private Comparable[] list;

        SortTask(Comparable[] list) {
            this.list = list;
        }


    /** compute defines how the SortTask is runned */
        @Override
        protected void compute() {
            if (list.length < THRESHOLD) {
                Arrays.sort(list);
            } else {
                // Get first half
                Comparable[] firstHalf = new Comparable[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

                // Get second half
                Integer secondHalflength = list.length - list.length / 2;
                Comparable[] secondHalf = new Comparable[secondHalflength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalflength);

                // Recursively sort the two halves
                invokeAll(new SortTask(firstHalf), new SortTask(secondHalf));

                // Merge firstHalf and secondHalf into one list
                SequentialMergeSort.merge(firstHalf, secondHalf, list);
              
            }
        }
    }
}
