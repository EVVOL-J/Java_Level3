package Lesson6;

public class TestArray {
    public int[] lastNumbersAfterFour(int[] array) {
        int indexFour = -1;
        int arrayLength = array.length;
        for (int i = 0; i < arrayLength; ++i) {
            if (array[i] == 4) indexFour = i;
        }
        if (indexFour == -1) throw new RuntimeException("Отсутсвует 4 в массиве");
        else if (indexFour == arrayLength - 1) return null;
        else {
            int[] convertArray = new int[arrayLength - indexFour - 1];
            for (int i = 0; i < arrayLength - indexFour-1; i++) {
                convertArray[i]=array[indexFour+1+i];
            }
            return convertArray;
        }
    }

   public boolean isOneOrFourInArray(int[] array){
        for (int number:array){
            if(number==4||number==1) return true;
        }
        return false;
   }

}
