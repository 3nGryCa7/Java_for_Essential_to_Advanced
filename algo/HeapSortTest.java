import java.util.Arrays;

public class HeapSortTest {
        // Main method to test sorting and searching
        public static void main(String[] args) {

            // Test with Integer array
            HeapSort<Integer> intSorter = new HeapSort<>();
            Integer[] arr = {3, 5, 1, 2, 4, 6};
            System.out.println("Original Array: " + Arrays.toString(arr));
    
            intSorter.heapSort(arr);
            System.out.println("Sorted Array: " + Arrays.toString(arr));
    
            int searchResult = intSorter.binarySearch(arr, 4);
            if (searchResult >= 0) {
                System.out.println("Element 4 found at index: " + searchResult);
            } else {
                System.out.println("Element 4 not found");
            }

            // Test with Double array
            HeapSort<Double> doubleSorter = new HeapSort<>();
            Double[] doubleArr = {3.5, 5.1, 1.2, 2.9, 4.4, 6.6};
            System.out.println("\nOriginal Double Array: " + Arrays.toString(doubleArr));

            doubleSorter.heapSort(doubleArr);
            System.out.println("Sorted Double Array: " + Arrays.toString(doubleArr));

            int doubleSearchResult = doubleSorter.binarySearch(doubleArr, 4.4);
            if (doubleSearchResult >= 0) {
                System.out.println("Element 4.4 found at index: " + doubleSearchResult);
            } else {
                System.out.println("Element 4.4 not found");
            }

            // Test with String array
            HeapSort<String> stringSorter = new HeapSort<>();
            String[] stringArr = {"banana", "apple", "grape", "cherry", "orange", "pear"};
            System.out.println("\nOriginal String Array: " + Arrays.toString(stringArr));

            stringSorter.heapSort(stringArr);
            System.out.println("Sorted String Array: " + Arrays.toString(stringArr));

            int stringSearchResult = stringSorter.binarySearch(stringArr, "cherry");
            if (stringSearchResult >= 0) {
                System.out.println("Element 'cherry' found at index: " + stringSearchResult);
            } else {
                System.out.println("Element 'cherry' not found");
            }
        }
}
