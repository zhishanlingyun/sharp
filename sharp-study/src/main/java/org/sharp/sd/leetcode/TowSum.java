package org.sharp.sd.leetcode;


import java.util.Arrays;

/**
 * 给定一个数组和一个数,求解相加等于这个数的数组下标
 * Example:
 * nums = {2,7,11,15} target = 9
 * return [0,1]
 */
public class TowSum {

    /**
     * 暴力找
     * @return
     */
    public static int[] findForceSum(int[] array,int sum){
        int[] result = new int[array.length+1];
        int index = 0;
        int dif = 0;
        for(int i=0;i<array.length;i++){
            dif = sum - array[i];
            for(int k=0;k<array.length;k++){
                if((i!=k)&&(dif==array[k])){
                    result[index++] = i;
                    result[index++] = k;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] n1 = {2,5,3,9,5,1,3};
        System.out.println(Arrays.toString(findForceSum(n1,10)));
    }

}
