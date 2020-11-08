package Lesson1;

import java.util.ArrayList;
import java.util.List;

public class Exercise2 {
    public static void main(String[] args) {
        Integer[] testArr={0,1,2,3,4};
        List<Integer> doubleArrayList=ChangeArrayToArrList(testArr);
        System.out.println(doubleArrayList);


    }

    public static <T> List ChangeArrayToArrList(T[] arr){
      List<T> list=new ArrayList<>();
        for (T arrToList:arr){
            list.add(arrToList);
        }
        return list;
    }
}
