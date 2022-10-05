import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

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

    public static void mergeSort(final int[] arr, final int leftIndex, final int rightIndex) throws NullPointerException, ArrayIndexOutOfBoundsException{
        if (arr == null) throw new NullPointerException("arr cannot be null");
        if (leftIndex < 0 || rightIndex >= arr.length) throw new ArrayIndexOutOfBoundsException("Incorrect indexes was passed");
        if (leftIndex < rightIndex){
            final int midIndex = (rightIndex + leftIndex) / 2;

            mergeSort(arr, leftIndex, midIndex);
            mergeSort(arr, midIndex+1, rightIndex);
            merge(arr, leftIndex, midIndex, rightIndex);
        }
    }

    private static void merge(final int[] arr, final int leftIndex, final int midIndex, final int rightIndex){
        // Arrays to store parts
        final List<Integer> leftSubarray = Collections.unmodifiableList(Arrays.stream(Arrays.copyOfRange(arr, leftIndex, midIndex + 1)).boxed().collect(Collectors.toList()));
        final List<Integer> rightSubarray = Collections.unmodifiableList(Arrays.stream(Arrays.copyOfRange(arr, midIndex + 1, rightIndex + 1)).boxed().collect(Collectors.toList()));

        // Initialize indexes
        int i = 0, j = 0, writeIndex = leftIndex;
        while (i < leftSubarray.size() && j < rightSubarray.size()){
            if (leftSubarray.get(i) < rightSubarray.get(j)){
                if (arr[writeIndex] != leftSubarray.get(i)){
                    arr[writeIndex] = leftSubarray.get(i);
                }
                ++i;
            }
            else{
                if (arr[writeIndex] != rightSubarray.get(j)){
                    arr[writeIndex] = rightSubarray.get(j);
                }
                ++j;
            }
            ++writeIndex;
        }

        // Add values if something left
        while (i < leftSubarray.size()){
            if (arr[writeIndex] != leftSubarray.get(i)){
                arr[writeIndex] = leftSubarray.get(i);
            }
            ++i;
            ++writeIndex;
        }
        while(j < rightSubarray.size()){
            if (arr[writeIndex] != rightSubarray.get(j)){
                arr[writeIndex] = rightSubarray.get(j);
            }
            ++j;
            ++writeIndex;
        }
    }
}
