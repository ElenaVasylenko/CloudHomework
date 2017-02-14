package sort;

import java.util.Scanner;

/**
 * Created by ovasylenko on 20-Dec-16.
 */
public class InsertionSort {

    private Comparable[] sort(Comparable[] array){

        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(less(array[j], array[j-1]))
                    swap(array, j, j-1);
                else break;
            }
        }

    return array;
    }


    private static boolean less (Comparable v, Comparable w){
        return v.compareTo(w)<0;
    }

    private static void swap(Comparable[] v, int i, int j){
        Comparable s = v[i];
        v[i] = v[j];
        v[j] = s;

    }

    private static boolean isSorted(Comparable[] array){

        for (int i = 1; i <array.length ; i++) {
            if(!less(array[i-1], array[i])) return false;
        }
         return true;
    }

    public void print(Comparable[] a){
        for (int i = 0; i <a.length ; i++) {
            System.out.print(a[i]+", ");
        }
    }

    public static void sort(Comparable[] a, int l, int h){
        for (int i=l;i<=h;i++){
            for (int j = i;j>0;j--)
                if (less(a[j],a[j-1]))
                    swap(a,j,j-1);
                else break;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        Comparable[] array = new Comparable[size];

        for (int i = 0; i <size ; i++) {
            array[i] = scanner.nextInt();
        }

        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(array);
        insertionSort.print(array);
    }
}
