package com.vijdan;
public class Radix_Merge_Quick_Sort {

    /** PURPOSE:The purpose of this assignment is to write a Java program that records the mean time of multiple sorting algorithms.
     *  - Merge Sort
     *  - Quick Sort
     *  - Radix Sort
     */

    //All Final Global variables
    private static final int numSwaps = 2500;        //number of swaps as default
    private static final int numTests = 100;         //number of tests for each algorithm


    /*******************************************************************
     * main() method
     *
     * Purpose: will test out the multiple sorts using different sizes of arrays
     * that have been randomized differently and output the averages of the
     * respective sorting algorithms
     ******************************************************************/
    public static void main(String[] args) {
        System.out.println("******Start of program******\n\n");
        testing();
        System.out.println("\n\n*****End of program*****");

    }


    /*******************************************************************
     * mergeSort()
     *
     * Purpose:Divide and conquer. This method will break down the array repeatedly into
     * smaller elements and then merge them back but in a sorted order
     ******************************************************************/
    public static void mergeSort(int[] array) {
        int len = array.length;
        if (len > 0 && array != null) {
            int[] temp = new int[len];
            mergeSort(array, 0, len, temp);
        }
    }
    /*******************************************************************
     * mergeSort()
     *
     * Purpose:Helper method to solve the recursive step in order to call the
     * metod on each half of the array repeatedly.
     ******************************************************************/
    private static void mergeSort(int[] array, int first, int last, int[] temp) {

        if ((last - first) > 1) {
            int mid = ((last - first) / 2) + first;
            mergeSort(array, first, mid, temp);
            mergeSort(array, mid, last, temp);
            merge(array, first, mid, last, temp);
        }
    }
    /*******************************************************************
     * merge()
     *
     * Purpose:this method will look into both halves and if one half has a smaller
     * value when compared to other half that value will be placed as the resultant value
     * This will repeat until all halves have been looked into.
     ******************************************************************/
    private static void merge(int[] array, int first, int mid, int last, int[] temp) {
        int cLeft = first;
        int cRight = mid;

        for (int t = cLeft; t < last; t++) {

            if (cLeft < mid &&
                    (cRight >= last || array[cLeft] < array[cRight])) {

                temp[t] = array[cLeft];
                cLeft++;
            } else {
                temp[t] = array[cRight];
                cRight++;
            }
        }
        System.arraycopy(temp, first, array, first, last - first);
    }




//    //DID NOT WORK
//    public static void inefficientMergeSort (int[] array){
//        int len = array.length;
//        if (len > 0 && array != null) {
//            int[] temp = new int[len];
//            inefficientMergeSort(array, 0, len);
//        }
//    }
//    private static void inefficientMergeSort (int[] array, int first, int last){
//
//        if ((last - first) > 1) {
//            int mid = ((last - first) / 2) + first;
//            inefficientMergeSort(array, first, mid);
//            inefficientMergeSort(array, mid, last);
//            inefficientMerge(array, first, mid, last);
//        }
//    }
//    private static void inefficientMerge (int[] array,int first, int mid, int last){
//
//        int i=0;
//        int j= 0;
//        int k = 0;
//        int len = array.length;
//        int[] lArr = new int[mid];
//        System.arraycopy(array,first,lArr,first,lArr.length);
//        int[] rArr = new int[mid];
//        System.arraycopy(array,mid,rArr,first,rArr.length);
//
//
//        while (j<lArr.length && k<rArr.length){
//            if(lArr[j]<rArr[k]){
//                array[i] = lArr[j];
//                i++;
//                j++;
//            }else {
//                array[i] = rArr[k];
//                i++;
//                k++;
//            }
//        }
//        while (j<lArr.length){
//            array[i] = lArr[j];
//            i++;
//            j++;
//        }
//        while (k<rArr.length){
//            array[i] = rArr[k];
//            i++;
//            k++;
//        }
//    }











    /*******************************************************************
     * quickSort()
     *
     * Purpose:Also Divide and conquer. This method will break down the array by finding
     * an appropriate pivot preferabbaly a median value in order to sort both sides in a time
     * efficient manner
     ******************************************************************/
    public static void quickSort(int[] array) {
        int len = array.length;
        if (len > 0 && array != null) {
            quickSort(array, 0, len);
        }
    }
    private static void quickSort(int[] array, int first, int last) {

        int pivot;

        if ((last-first) <= 1) {
            //do nothing
        } else if (array.length == 2) {
            if (array[0] > array[1]) {
                swap(array, 0, 1);
            }
        } else {
            medianOfThree(array, first, last);
            pivot = partition(array, first, last);
            quickSort(array, first, pivot);
            quickSort(array, pivot + 1, last);
        }
    }
    private static int partition(int[] array, int first, int last) {

        int pivot = array[first];
        int bigFirst = first + 1;

        for (int i = first + 1; i < last; i++) {
            if (array[i] < pivot) {
                swap(array, bigFirst, i);
                bigFirst++;
            }
        }
        swap(array, first, bigFirst - 1);
        return bigFirst - 1;
    }
    private static void medianOfThree(int[] array, int first, int last) {

        int mid = ((last - first) / 2) + first;
        int one = array[first];
        int two = array[mid];
        int three = array[last - 2];

        if ((one < two && two < three) || (three < two && two < one)) {
            swap(array, first, mid);
        } else if ((two < one && one < three) || (three < one && one < two)) {
            swap(array, first, first);
        } else {
            swap(array, first, last - 1);
        }
    }




    /*******************************************************************
     * radixSort() method
     *
     * Purpose: this method will sort the integers in the array according
     * its uit place values. The method will initially find the biggest value
     * in order to determine the highest exponent we would have to sort by
     * and then one by on e it will sort the place values and therfore the
     * whole array
     ******************************************************************/
    public static void radixSort(int array[]) {

        int len = array.length;
        if (len > 0 && array != null) {

            int biggest = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] > biggest) {
                    biggest = array[i];
                }
            }

            int maxExp = (int) Math.pow(10, (int) Math.log10(biggest));

            for (int i = 1; maxExp / i > 0; i = i * 10) {
                radixCal(array, i);
            }
        }
    }
    /*******************************************************************
     * radixCal() method
     *
     * Purpose: this is a helper metod which will help calculate the number
     * of times a certain place value repeats and therefore will sort and keep
     * process the output in its rightful index
     * We will keep and index[] array in order to find the indexes place values
     * from 0 to 9 for each number. The index array will keep count as to how many
     * numbers we have for similar place values.
     * The next for loop will give us the correct position as to where the value should
     * be. It will add the value of the one index before as those spaces have been taken
     * up and therefore the new value shpuld proceed forward.
     * Once this is completed we will now place the values in the right position along side
     * with subtracting and cleanng up our index array, to denote that the former position
     * has been placed in the right position.
     ******************************************************************/
    private static void radixCal(int array[], int exp) {

        int len = array.length;
        int result[] = new int[len];

        int index[] = new int[10];

        for (int count = 0; count < len; count++) {
            index[(array[count] / exp) % 10]++;
        }

        for (int pos = 1; pos < 10; pos++) {
            index[pos] += index[pos - 1];
        }

        for (int out = (len - 1); out >= 0; out--) {
            result[index[(array[out] / exp) % 10] - 1] = array[out];
            index[(array[out] / exp) % 10]--;
        }


        for (int a = 0; a < len; a++) {
            array[a] = result[a];
        }

    }







    public static void randomize(int[] array, int n) {
        int i = 0;
        int len = array.length;
        while (i < n) {
            swap(array, (int) (Math.random() * len), (int) (Math.random() * len));
            i++;
        }
    }
    public static boolean sorted(int[] array) {
        boolean sorted = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                sorted = false;
            }
        }
        return sorted;
    }
    public static void fill(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }
    /*******************************************************************
     * arithmeticMean
     *
     * Purpose: Compute the average of long values.
     * To avoid long overflow, use type double in the computation.
     *
     ******************************************************************/
    public static double arithmeticMean(long data[]) {
        double sum = 0;
        for (int i = 0; i < data.length; i++)
            sum += (double) data[i];
        return sum / (double) data.length;
    }
    /*******************************************************************
     * swap()
     *
     * Purpose: Helper method to swap to given index in an array
     ******************************************************************/
    private static void swap(int[] array, int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }






    public static void testing() {

        long start, end, duration;
        int size = 10000;
        double swapper = 0.25;

        System.out.println("ALGORITHM  | SIZE  | SWAPS | Duration(ms)");
        System.out.println("_________________________________________");

        long[] mrgSrtTime = new long[numTests];
        for (int i = 0; i < numTests; i++) {
            int[] testArray = new int[size*(i+1)];
            fill(testArray);
            randomize(testArray,(int)(size*(swapper*(i+1))));
            start = System.nanoTime();
            mergeSort(testArray);
            end = System.nanoTime();
            duration = end - start;
            mrgSrtTime[i] = duration/1000000;
            if (!sorted(testArray)) {
                System.out.println("Array was not sorted using MergeSort");
            }
            System.out.println("Merge Sort | "+(size*(i+1))+" | "+(int)(size*(swapper*(i+1)))+ " | "+duration/100000);
        }
        double mergeMean = arithmeticMean(mrgSrtTime);
        System.out.println("_________________________________________");

        size = 10000;
        swapper = 0.25;
        long[] qkSrtTime = new long[numTests];
        for (int i = 0; i < numTests; i++) {
            int[] testArray = new int[size*(i+1)];
            fill(testArray);
            randomize(testArray,(int)(size*(swapper*(i+1))));
            start = System.nanoTime();
            quickSort(testArray);
            end = System.nanoTime();
            duration = end - start;
            qkSrtTime[i] = duration/1000000;
            if (!sorted(testArray)) {
                System.out.println("Array was not sorted using QuickSort");
            }
            System.out.println("Quick Sort | "+(size*(i+1))+" | "+(int)(size*(swapper*(i+1)))+ " | "+duration/1000000);
        }
        double quickMean = arithmeticMean(qkSrtTime);
        System.out.println("_________________________________________");

        size = 10000;
        swapper = 0.25;
        long[] radSrtTime = new long[numTests];
        for (int i = 0; i < numTests; i++) {
            int[] testArray = new int[size*(i+1)];
            fill(testArray);
            randomize(testArray,(int)(size*(swapper*(i+1))));
            start = System.nanoTime();
            radixSort(testArray);
            end = System.nanoTime();
            duration = end - start;
            radSrtTime[i] = duration/1000000;
            if (!sorted(testArray)) {
                System.out.println("Array was not sorted using RadixSort");
            }
            System.out.println("Radix Sort | "+(size*(i+1))+" | "+(int)(size*(swapper*(i+1)))+ " | "+duration/1000000);
        }
        double radMean = arithmeticMean(radSrtTime);

        System.out.println("_________________________________________");
        System.out.println("_________________________________________");

        System.out.println("***Averages for all Sorts***");
        System.out.println("Merge Sort average run time is = " + mergeMean+ " ms");
        System.out.println("Quick Sort average run time is = " + quickMean+ " ms");
        System.out.println("Radix Sort average run time is = " + radMean+ " ms");

    }
}

/*-----------------------------------------Report----------------------------------------
1)
ALGORITHM  | SIZE  | SWAPS | Duration(ms)
_________________________________________
Merge Sort | 10000 | 2500 | 49
Merge Sort | 20000 | 5000 | 18
Merge Sort | 30000 | 7500 | 20
Merge Sort | 40000 | 10000 | 32
Merge Sort | 50000 | 12500 | 42
_________________________________________
Quick Sort | 10000 | 2500 | 2
Quick Sort | 20000 | 5000 | 2
Quick Sort | 30000 | 7500 | 1
Quick Sort | 40000 | 10000 | 2
Quick Sort | 50000 | 12500 | 3
_________________________________________
Radix Sort | 10000 | 2500 | 4
Radix Sort | 20000 | 5000 | 3
Radix Sort | 30000 | 7500 | 4
Radix Sort | 40000 | 10000 | 7
Radix Sort | 50000 | 12500 | 5
_________________________________________
***Means for all Sorts***
Merge Sort average run time is = 43.41 ms
Quick Sort average run time is = 43.46 ms
Radix Sort average run time is = 52.84 ms

2)  Quick Sort will be faster for small amounts of data as can be seen
    when comparing the first value of Array size 10,000 and 2500 swaps
    given in the table above. Quick Sort results in 2 ms and Merge Sort
    is at 49 msThis is because Merge Sort requires extra memory
    (consider the temp array made). Quick sort however sorts in
    place and if provided with a substantial pivot; the run time will be
    of Big O (nlogn).

3)  Comparing the first value at length 10000 and swaps of 2500;
    Radix Sort is much faster than the Merge Sort. Giving a duration
    of 4 ms as compared to Merge Sorts 49 ms

4)  Radix Sort's time complexity is based on Big O (kn), where n is
    the number of elements and k is a constant dependent on the largest
    element found and therefore its exponent and therefore the number of
    times we would have to subdivide the array. Accordingly our data shows
    that for small sized arrays, Radix Sort does exceptionally well when
    compared to other sorts. However on average and as values increase Radix
    Sort proves to be highly inefficient.


 ---------------------------------------------------------------------------------------*/

