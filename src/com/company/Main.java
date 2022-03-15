package com.company;

public class Main {

    public static void main(String[] args) {
        GenDigits generator = new GenDigits();
        TanThread sort = new TanThread(generator);
        ArcTanThread print = new ArcTanThread(sort);

        generator.start();
        sort.start();
        print.start();
    }
}