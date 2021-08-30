package ru.geekbarins;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage{

    Semaphore semaphore = new Semaphore(Main.CARS_COUNT / 2);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }


    @Override
    public void go(Car car) {
        try {
            try {
                System.out.println(car.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                System.out.println(car.getName() + " начал этап " + description);
                Thread.sleep(length / car.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(car.getName() + " закончил этап " + description);
                semaphore.release();
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
