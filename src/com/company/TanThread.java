package com.company;

import java.util.ArrayList;

public class TanThread extends Thread {

    private volatile ArrayList<Integer> genBuffer;
    private volatile ArrayList<Integer> secBuffer = new ArrayList<>();

    public TanThread(GenDigits generator) {
        genBuffer = generator.getGenBuffer();
    }

    public ArrayList<Integer> getSecBuffer() {

        return secBuffer;
    }

    @Override
    public void run() {
        int count = 0;

        while (count < 1000){
            synchronized (genBuffer) {
                synchronized (secBuffer) {
                    while (genBuffer.isEmpty()) {
                        try {
                            genBuffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (secBuffer.size() == 300) {
                        try {
                            secBuffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    secBuffer.add((int)Math.tan(genBuffer.remove(0)));
                    count++;

                    genBuffer.notifyAll();
                    secBuffer.notifyAll();
                }
            }
        }
    }
}