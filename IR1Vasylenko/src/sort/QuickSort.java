package sort;

import java.util.Scanner;

/**
 * Created by ovasylenko on 20-Dec-16.
 */
public class QuickSort {

    private static final int CUTOFF = 10;

    //separate
    static int partition(Comparable[] arr, int left, int right) {
        int i = left, j = right;
        int tmp;
        Comparable pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (less(arr[i], pivot))
                i++;
            while (less(pivot,arr[j]))
                j--;
            if (i <= j) {
                exch(arr, i, j);
                i++;
                j--;
            }
        };
        return i;
    }

    public static void sort(Comparable[] a){

        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        System.out.println("SORTING -----------------------------------------------------");
        if (hi <= lo + CUTOFF - 1){
            InsertionSort.sort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }


    //for arrays with the same kays
    public static void sortD(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt){
            int cmp = a[i].compareTo(v);   //a[i] compare to v (a[lo])
            if (cmp < 0) exch(a, lt++, i++);  // a[i] < v
            else if (cmp > 0) exch(a, i, gt--); // a[i] > v
            else i++;  //a[i] = v
        }
        sortD(a, lo, lt - 1);
        sortD(a, gt + 1, hi);
    }


    private  static boolean less(Comparable a, Comparable b){
        return a.compareTo(b)<0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable s = a[i];
        a[i] = a[j];
        a[j] = s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        Comparable[] array = new Comparable[size];

        for (int i = 0; i <size ; i++) {
            array[i] = scanner.nextInt();
        }


        QuickSort q = new QuickSort();
        q.sortD(array,0,array.length-1);

        for (int i = 0; i < size; i++) {
            System.out.print(array[i]+", ");
        }
    }

}
