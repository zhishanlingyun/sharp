package org.sharp.research.algo.sort;

import java.util.Arrays;

public class QuickSort {

    private static void swap(int[] arr,int x,int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void sort(int[] list){
        qsort(list,0,list.length-1);
    }

    private static void qsort(int[] arr,int x,int y){
        if(x>y) return;
        int i = x;
        int j = y;
        int k = arr[x];
        while (i!=j){
            while (arr[j]>k && i<j){
                j--;
            }
            while (arr[i]<=k && i<j){
                i++;
            }
            if(i<j){
                swap(arr,i,j);
            }
        }
        swap(arr,x,i);
        qsort(arr,x,i-1);
        qsort(arr,i+1,y);
    }


    public static void main(String[] args) {
        int[] arr = {10,2,5,3,9,6,1,5};
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
