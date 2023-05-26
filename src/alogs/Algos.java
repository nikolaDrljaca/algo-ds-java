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

    public static void quickSort(int[] arr) {

    }
}
