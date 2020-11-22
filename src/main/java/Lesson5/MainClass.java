package Lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;







    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(CARS_COUNT,new Road(60), new Tunnel(CARS_COUNT), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            race.startCLD.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            race.win.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>>"+race.winCarName+ " WIN");
            race.finishCLD.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private boolean lastStage;
    private int countTrack;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.lastStage=false;

    }

    public boolean isLastStage() {
        return lastStage;
    }

    public Race getRace() {
        return race;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            race.startCLD.countDown();
            race.cb.await();



        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            if(i==race.getStages().size()-1) lastStage=true;
            race.getStages().get(i).go(this);


        }
    }
}
abstract class Stage {

    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
    public void checkFinishAndWin(Car c){
        c.getRace().winCarName=c.getName();
        c.getRace().win.countDown();
        c.getRace().finishCLD.countDown();

    }
}
class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);

            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if (c.isLastStage()) checkFinishAndWin(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Tunnel extends Stage {
    volatile Semaphore semaphore;
    public Tunnel(int carsCount) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore=new Semaphore(carsCount/2);


    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                this.semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);

                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                this.semaphore.release();
                if (c.isLastStage()) checkFinishAndWin(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Race {
    public String winCarName;
    public CountDownLatch finishCLD ;
    public  CountDownLatch win;
    public CyclicBarrier cb ;
    public  CountDownLatch startCLD;
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(int carsCount,Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.cb= new CyclicBarrier(carsCount);
        this.startCLD= new CountDownLatch(carsCount);
        this.win= new CountDownLatch(1);
        this.finishCLD= new CountDownLatch(carsCount);
        this.winCarName=null;
    }
}
