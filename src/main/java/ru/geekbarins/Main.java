package ru.geekbarins;

import java.util.concurrent.CyclicBarrier;

public class Main {

    protected static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        Main.doWork();

    }

    public static void doWork() {
        CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), barrier);
        }

        System.out.println("ВАЖНОЕ ОБЪЯЛЕНИЕ - ПОДГОТОВКА!!!");

        Thread[] carThreads = new Thread[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            carThreads[i] = new Thread(cars[i]);
            carThreads[i].start();
        }

        for (Thread carThread : carThreads) {
            try {
                carThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ - ГОНКА ЗАКОНЧИЛАСЬ");
    }

}
