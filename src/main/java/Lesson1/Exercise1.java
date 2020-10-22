package Lesson1;

import java.util.Arrays;

public class Exercise1 {

    public static void main(String[] args) {
        Integer[] arrInteger={1,2,3,4,5};
        Double[] arrDouble={1.0,2.1,3.1,4.1};

        moveElementArrTo(arrInteger,1,3);
        moveElementArrTo(arrDouble,0,3);

        System.out.println(Arrays.toString(arrInteger));
        System.out.println(Arrays.toString(arrDouble));
    }



    public static <T> void moveElementArrTo(T[] arr, int indexFrom, int indexTo){
        T tSave;
        tSave=arr[indexTo];
        arr[indexTo]=arr[indexFrom];
        arr[indexFrom]=tSave;
    }

}
