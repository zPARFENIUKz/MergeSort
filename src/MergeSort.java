import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeSort {
    public static void main(String[] args)
    {
        final int arr[] = new int[100];
        IntStream.range(0, 100).forEach(index -> arr[index] = (int)(Math.random() * 500));


        mergeSort(arr, 0, arr.length-1);
        for (int el : arr){
            System.out.print(el + " ");
        }
        System.out.println();

        final int[] arr_clone = arr.clone();
        Arrays.sort(arr_clone);
        if (Arrays.equals(arr, arr_clone)){
            System.out.println("Test passed!");
        }
        else
        {
            System.out.println("Test failed!");
        }
    }

    public static void mergeSort(final int[] arr, int l, int r) throws NullPointerException{
        if (arr == null) throw new NullPointerException("arr cannot be null");
        if (l < r){
            final int m = (r + l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(final int[] arr, int l, int m, int r){
        // Arrays to store parts
        final int[] leftSubarray = new int[m-l+1];
        final int[] rightSubarray = new int[r-m];

        // Write data in arrays
        for (int i = 0; i < leftSubarray.length; ++i){
            leftSubarray[i] = arr[l + i];
        }
        for (int i = 0; i < rightSubarray.length; ++i){
            rightSubarray[i] = arr[m + 1 + i];
        }

        // Initialize indexes
        int i = 0, j = 0, writeIndex = l;
        while (i < leftSubarray.length && j < rightSubarray.length){
            if (leftSubarray[i] < rightSubarray[j]){
                if (arr[writeIndex] != leftSubarray[i]){
                    arr[writeIndex] = leftSubarray[i];
                }
                ++i;
            }
            else{
                if (arr[writeIndex] != rightSubarray[j]){
                    arr[writeIndex] = rightSubarray[j];
                }
                ++j;
            }
            ++writeIndex;
        }

        // Add values if something left
        while (i < leftSubarray.length){
            if (arr[writeIndex] != leftSubarray[i]){
                arr[writeIndex] = leftSubarray[i];
            }
            ++i;
            ++writeIndex;
        }
        while(j < rightSubarray.length){
            if (arr[writeIndex] != rightSubarray[j]){
                arr[writeIndex] = rightSubarray[j];
            }
            ++j;
            ++writeIndex;
        }
    }
}
