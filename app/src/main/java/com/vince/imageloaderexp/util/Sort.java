package com.vince.imageloaderexp.util;

/**
 * Created by admin on 17/3/31.
 */
public class Sort {

    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * @param numbers 需要排序的整型数组
     */
    public static void bubbleSort(int[] numbers){

        int size = numbers.length;
        int temp = 0;

        for (int i = 0; i < size -1; i++) {

            for (int j = 0; j < size -1 -i; j++) {

                if (numbers[j] > numbers[j+1]){
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序算法
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
     * 以此类推，直到所有元素均排序完毕。
     * @param numbers
     */
    public static void selectSort(int[] numbers){

        int size = numbers.length;
        int temp = 0;

        for (int i = 0; i < size-1; i++) {

            int k = i;

            for (int j = i+1; j < size; j++) {
                if (numbers[j]<numbers[k]){
                    k = j;
                }
            }

            temp = numbers[k];
            numbers[k] = numbers[i];
            numbers[i] = temp;

        }


    }

    /**
     * 插入排序
     *
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     * @param numbers  待排序数组
     */
    public static void insertSort(int[] numbers){

        int size = numbers.length;

        for (int i = 1; i < size; i++) {

            int temp = numbers[i];
            int j = i-1;

            for (;j>=0 && numbers[j] > temp;j--){
                numbers[j+1] = numbers[j];
            }

            numbers[j+1] = temp;

        }

    }

    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param numbers 带查找数组
     * @param low   开始位置
     * @param high  结束位置
     * @return  中轴所在位置
     */
    public static int getMiddle(int[] numbers, int low ,int high){

        int temp  = numbers[low];
        while (low<high){

            while (low <high && numbers[high] > temp){
                high--;
            }
            numbers[high] = temp;

            while (low<high && numbers[low] > temp){
                low ++;
            }
            numbers[low] = numbers[high];

        }
        numbers[low] = temp;

        return low;
    }

    public static void quickSort(int[] numbers,int low , int high){

        int mid = getMiddle(numbers,low,high);
        quickSort(numbers,low,mid-1);
        quickSort(numbers,mid+1,high);

    }


}
