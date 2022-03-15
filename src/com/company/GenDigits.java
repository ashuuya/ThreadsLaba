package com.company;
import java.util.ArrayList;

public class GenDigits extends Thread{

    private volatile ArrayList<Integer> genBuffer = new ArrayList<>(); //буфер для генерации

    public ArrayList<Integer> getGenBuffer() {
        return genBuffer;
    }
    @Override
    public void run() {

        int count = 0;
        while (count < 1000){
            synchronized (genBuffer) {
                while (genBuffer.size() == 300) {
                    try {
                        genBuffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                genBuffer.add((int)((Math.random() * ((1001 - 1) + 1)) + 1));
                count++;
                genBuffer.notifyAll();
            }
        }
        System.out.println("Первый поток закончил работу.");
    }
}