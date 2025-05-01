public class Sort {

    // Insertion Sort
    public static void insertionSort(int arr[]) {
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j > 0; j--) {
                if(arr[j - i] > arr[j]) Utility.swapValuesAtIndexes(arr, j, j - 1);
            }
        }
    }
    // ------------------------------------------------------------------------------------------

    // Selection Sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) if (arr[j] < arr[index]) index = j;
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }
    // ------------------------------------------------------------------------------------------
    
    // Bubble Sort
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++){
            for(int j = 1; j < (n - i); j++){
                if(arr[j - 1] > arr[j]) Utility.swapValuesAtIndexes(arr, j - 1, j);
            }
        }
    }
    // ------------------------------------------------------------------------------------------

    // Merge Sort
    public static void mergeSort(int[] a, int n) {
        if (n < 2) return;
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        for (int i = 0; i < mid; i++) l[i] = a[i];
        for (int i = mid; i < n; i++) r[i - mid] = a[i];
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(a, l, r, mid, n - mid);
    }

    private static void merge (int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) a[k++] = l[i++];
            else a[k++] = r[j++];
        }
        while (i < left) a[k++] = l[i++];
        while (j < right) a[k++] = r[j++];
    }
    // ------------------------------------------------------------------------------------------

    // Heap Sort
    public void heapSort(int arr[]) {
        int n = arr.length;
  
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
  
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
  
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private void heapify(int arr[], int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
  
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest]) largest = l;
  
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest]) largest = r;
  
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
  
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    // ------------------------------------------------------------------------------------------
}
