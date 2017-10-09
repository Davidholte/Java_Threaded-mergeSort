import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Dave on 14/02/2017.
 */
public class ForkJoinMergeSort {

    private static final ForkJoinPool threadPool = new ForkJoinPool();
    private static final int SIZE_THRESHOLD = 16;

    /** Recursive HelperMethod called to use this ForkJoinMergeSort tool */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    /** Recursive Method Body */
    public static void sort(Comparable[] a, int startPos, int endPos) {
        if (endPos - startPos < SIZE_THRESHOLD) {
            insertionSort(a, startPos, endPos);
            return;
        }
        Comparable[] temp = new Comparable[a.length];
        threadPool.invoke(new SortTask(a, temp, startPos, endPos));
    }

    /** SortTask Class */
    private static class SortTask extends RecursiveAction {

        Comparable[] a;
        Comparable[] temp;
        int startPos;
        int endPos;

    /** SortTask is the recursive action taken when sorting the splitted arrays */
        private SortTask(Comparable[] a, Comparable[] temp, int startPos, int endPos) {
            this.a = a;
            this.startPos = startPos;
            this.endPos = endPos;
            this.temp = temp;
        }

    /** compute defines how SortTask is runned */
        @Override
        protected void compute() {
            if (endPos - startPos < SIZE_THRESHOLD) {
                insertionSort(a, startPos, endPos);
                return;
            }

            int m = (startPos + endPos) / 2;

            invokeAll(new SortTask(a, temp, startPos, m), new SortTask(a, temp, m + 1, endPos));
            merge(a, temp, startPos, m, endPos);
        }

    /** merge is called when the two halves is merged together again */
        private static void merge(Comparable[] a, Comparable[] b, int startPos, int m, int endPos) {

            // base case
            if (a[m].compareTo(a[m+1]) <= 0) {
                return;

            } else {
                System.arraycopy(a, startPos, b, startPos, m - startPos + 1);

                int i = startPos;
                int j = m + 1;
                int k = startPos;

                while (k < j && j <= endPos) {
                    if (b[i].compareTo(a[j]) <= 0) {
                        a[k++] = b[i++];
                    } else {
                        a[k++] = a[j++];
                    }
                }
                System.arraycopy(b, i, a, k, j - k);
            }
        }
    }
    /** InsertionSort is called if the array.size() is below SIZE_THRESHOLD */
        private static void insertionSort(Comparable[] a, int startPos, int endPos) {

            for (int i = startPos+1; i <= endPos; i++) {
                int j = i;
                Comparable t = a[j];

                while (j > startPos && t.compareTo(a[j-1]) < 0) {
                    a[j] = a[j-1];
                    --j;
                }
                a[j] = t;
            }
        }
    
}
