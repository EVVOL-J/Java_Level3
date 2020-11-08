package Lesson1.Exercize3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit>{
    private List<T> box=new ArrayList<>();


   Box(){

   }
    public void addFruit(T fruit){
       box.add(fruit);

   }

    public float getWeight(){
       float weight=0.0f;
       for (T boxFruit:box){
           weight+=boxFruit.getWeight();
       }
       return weight;
    }

   public boolean compare(Box compareBox){
        return Math.abs(this.getWeight()-compareBox.getWeight())<0.0001;
    }

    public void pourOver(Box pourBox){
       pourBox.box.addAll(box);
       box.clear();
    }


}
