package com.study.chap01;

public class ThreadInterruptDemo {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                int count = 0;
                while (!Thread.interrupted()){
                    System.out.println(name + ": " + count++);
                }
            }
        };
        Thread ta = new Thread(r);
        Thread tb = new Thread(r);
        ta.start();
        tb.start();
        while (true){
            double n = Math.random();
            if(n >= 0.49999999 && n <= 0.50000001){
                break;
            }
        }
        ta.interrupt();
        tb.interrupt();
    }
}
