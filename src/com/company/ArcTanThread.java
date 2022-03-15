package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class ArcTanThread extends Thread {
    private ArrayList<Integer> secBuffer; //второй буфер

    public ArcTanThread(TanThread sort)
    {
        secBuffer = sort.getSecBuffer();
    }

    @Override
    public void run() {
        int count = 0;
        while (count < 1000) {
            synchronized (secBuffer) {
                while (secBuffer.isEmpty()) {
                    try {
                        secBuffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                count++;
                secBuffer.notifyAll();
            }
        }
        System.out.println(Math.atan(secBuffer.remove(0)));
    }
}