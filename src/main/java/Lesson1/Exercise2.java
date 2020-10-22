package Lesson1;

import java.util.ArrayList;

public class Exercise2 {
    public static void main(String[] args) {
        Integer[] testArr={0,1,2,3,4};
        ArrayList<Integer> doubleArrayList=ChangeArrayToArrList(testArr);
        System.out.println(doubleArrayList);


    }

    public static <T> ArrayList ChangeArrayToArrList(T[] arr){
        ArrayList<T> arrayList=new ArrayList<>();
        for (T arrToList:arr){
            arrayList.add(arrToList);
        }
        return arrayList;
    }
}
