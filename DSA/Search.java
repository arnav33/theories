public class Search {
    public static void iterativeBinarySearch(int arr[], int first, int last, int key) {
        int mid = (first + last)/2;
        while(first <= last) {
            if (arr[mid] < key) first = mid + 1;
            else if (arr[mid] == key) {
                System.out.println("Element is found at index: " + mid);
                break;
            } else last = mid - 1;
            mid = (first + last)/2;
        }
        if (first > last) System.out.println("Element is not found!");
    }

    public static int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) return mid;
            if (arr[mid] > x) return binarySearch(arr, l, mid - 1, x);
            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }
}
