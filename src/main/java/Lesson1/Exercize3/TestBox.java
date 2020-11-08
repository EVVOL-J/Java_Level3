package Lesson1.Exercize3;

import java.util.Arrays;
import java.util.Random;

public class TestBox {
    private static final int SIZE=10;
    public static void main(String[] args) {
        Random random = new Random();
        Orange[] oranges = new Orange[SIZE];
        Apple[] apples = new Apple[SIZE];


        for (int i = 0; i < SIZE; i++) {
            oranges[i] = new Orange(random.nextFloat());
            apples[i] = new Apple(random.nextFloat());
        }


        Box<Apple> boxApple1 = new Box();
        Box<Apple> boxApple2 = new Box();
        Box<Orange> boxOrange1 = new Box();

        for(Apple apple:apples){
            boxApple1.addFruit(apple);
            boxApple2.addFruit(apple);

        }
        for(Orange orange:oranges){
            boxOrange1.addFruit(orange);
        }
        System.out.println("Вес первой коробки с яблоками= "+ boxApple1.getWeight());
        System.out.println("Вес второй коробки с яблоками= "+ boxApple2.getWeight());
        System.out.println("Вес первой коробки с апельсинами= "+ boxOrange1.getWeight());

        System.out.println("Вес коробок с яблоками одинаков? " + boxApple1.compare(boxApple2));
        System.out.println("Вес коробки с яблоками равен весу коробки с апельсинами? " + boxApple1.compare(boxOrange1));
        boxApple2.pourOver(boxApple1);
        System.out.println("Вес первой коробки с яблоками= "+ boxApple1.getWeight());
        System.out.println("Вес второй коробки с яблоками= "+ boxApple2.getWeight());




    }
}
