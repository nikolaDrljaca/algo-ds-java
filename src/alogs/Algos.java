package alogs;


public class Algos {

    public static boolean binarySearch(int[] haystack, int needle) {
        int lo = 0;
        int hi = haystack.length;
        while (lo < hi) {
            int mid = lo + Math.floorDiv((hi - lo), 2);
            if (mid < 0) break;
            if (haystack[mid] == needle) return true;
            if (haystack[mid] < needle) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }

    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi];
        int idx = lo - 1;

        for (int i = lo; i < hi; i++) {
            if (arr[i] <= pivot) {
                idx++;
                int temp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = temp;
            }
        }
        idx++;
        arr[hi] = arr[idx];
        arr[idx] = pivot;

        return idx;
    }

    private static void qs(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = partition(arr, lo, hi);
        qs(arr, lo, pivot - 1);
        qs(arr, pivot + 1, hi);
    }

    public static void quickSort(int[] arr) {
        qs(arr, 0, arr.length - 1);
    }
}
