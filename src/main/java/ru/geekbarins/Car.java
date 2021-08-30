package ru.geekbarins;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable{
    private CyclicBarrier barrier;
    private static int CARS_COUNT;
    protected Race race;
    protected int speed;
    protected String name;
    private boolean isStart;
    private boolean winner;

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public Car(Race race, int speed, CyclicBarrier barrier) {
        this.race = race;
        this.speed = speed;
        this.barrier = barrier;
        CARS_COUNT++;
        this.name = "Участник № " + CARS_COUNT;
    }

    synchronized void checkFinish(String name) {
        int counter = 0;
        if (counter++ == 0) {
            System.out.println("У нас первый финишировавший " + name);
        } else {
            System.out.println(name + "пришел к финишу " + counter);
        }
    }

    @Override
    public void run() {

        try {
            System.out.println(this.name + " начал готовиться");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            this.barrier.await();
            if (!isStart) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> ГОНКА НАЧАЛАСЬ!!!");
                isStart = true;
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (!winner) {
            System.out.println("У нас есть победитель " + this.name);
            winner = true;
        }

    }
}
