/**
 * Created by Dave on 13/02/2017.
 */
public class SequentialMergeSort {

    /** SequentialMergeSort method */
    @SuppressWarnings("rawTypes")
    public static Comparable[] mergeSort(Comparable[] list) {

        //Base case: if list is empty; do nothing
        if (list.length <= 1) {
            return list;
        }

        else {
            // Split array in two parts
            Comparable[] firstHalf = new Comparable[list.length / 2];
            Comparable[] secondHalf = new Comparable[list.length - firstHalf.length];

            System.arraycopy(list, 0, firstHalf, 0, firstHalf.length);
            System.arraycopy(list, firstHalf.length, secondHalf, 0, secondHalf.length);

            // sort each half
            mergeSort(firstHalf);
            mergeSort(secondHalf);

            // merge the halves together
            merge(firstHalf, secondHalf, list);

            return list;
        }
    }

    /** Merge method */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void merge(Comparable[] first, Comparable[] second, Comparable[] temp) {

        // index positions - staring with first elements
        int indexFirst = 0;
        int indexSecond = 0;
        // index positions - staring with first positions
        int indexMerged = 0;

        // compare elements at first and second index positions
        //  move smaller element at indexMerged
        while (indexFirst < first.length && indexSecond < second.length) {

            if (first[indexFirst].compareTo(first[indexFirst]) <= 0) {
                temp[indexMerged] = first[indexFirst];
                indexFirst++;
            }
            else {
                temp[indexMerged] = second[indexSecond];
                indexSecond++;
            }
            indexMerged++;
        }

    }
}
