import java.util.Arrays;
import java.util.Random;

/**
 * Created by Dave on 13/02/2017.
 */
public class BenchMarkTest {

    public static void main(String[] args) {

        // Initializing and filling int array with random integers
        Integer[] list1 = new Integer[7000000];
        Integer[] list2 = new Integer[7000000];
        Integer[] list3 = new Integer[7000000];
        String[] list4 = {"c", "a", "awd", "ophgb", "ijufg", "poru", "bvnvhd"};
        Random r = new Random();

        for (int i = 0; i < list1.length; i++) {
            list1[i] = Math.abs(r.nextInt(10000000)); // generate a random number
            list2[i] = Math.abs(r.nextInt(10000000)); // generate a random number
            list3[i] = Math.abs(r.nextInt(10000000)); // generate a random number
        }


        /** ForkJoin MergeSort */
        long startTime = System.currentTimeMillis();
        ForkJoinMergeSort.sort(list1);
        long endTime = System.currentTimeMillis();
        System.out.println("ForkJoinMergeSort time: " + "with " + Runtime.getRuntime().availableProcessors() + " processing units is " + (endTime - startTime) + " milliseconds");
//        System.out.println(Arrays.toString(list1));


        /** Der er fejl i algoritmen, der hvor den sorterer pga sammenligningen med comparable interfaces */
        /** Parallel MergeSort */
        startTime = System.currentTimeMillis();
        ParallelMergeSort.parallelSort(list2);
        endTime = System.currentTimeMillis();
        System.out.println("ParallelMergeSort time: " + "with " + Runtime.getRuntime().availableProcessors() + " processing units is " + (endTime - startTime) + " milliseconds");
//        System.out.println(Arrays.toString(list2));

        /** Der er fejl i algoritmen, der hvor den sorterer pga sammenligningen med comparable interfaces */
        /** Sequential MergeSort */
        startTime = System.currentTimeMillis();
        SequentialMergeSort.mergeSort(list3);
        endTime = System.currentTimeMillis();
        System.out.println("Sequential time: " + (endTime - startTime) + " milliseconds");
//        System.out.println(Arrays.toString(list3));

    }

}
