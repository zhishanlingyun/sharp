package org.sharp.research.algo.sort;

/**
 * 简单桶排序
 */
public class BucketSort {

    /**
     * list in [0-10] 范围的排序
     * @param list
     */
    public static void sort(int[] list){
        int len = 11;
        int[] record = new int[len];
        for(int i=0;i<len;i++){
            record[i] = 0;
        }
        for(int k=0;k<list.length;k++){
            record[list[k]]++;
        }
        for(int i=len-1;i>=0;i--){
            if(record[i]>0){
                for(int j=record[i];j>0;j--){
                    System.out.print(i+" ");
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,3,5,2,8};
        BucketSort.sort(arr);
    }


}
