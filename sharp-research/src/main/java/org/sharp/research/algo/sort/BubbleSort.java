package org.sharp.research.algo.sort;

import java.util.Arrays;

public class BubbleSort {

    private static void swap(int[] arr,int a,int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void sort(int[] list){
        int len = list.length;
        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-1-i;j++){
                if(list[j]<list[j+1]){
                    /*int tmp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = tmp;*/
                    swap(list,j,j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,7,6,10,9};
        BubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
