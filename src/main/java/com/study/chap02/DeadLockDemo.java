package com.study.chap02;

public class DeadLockDemo {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void instanceMethod1(){
        synchronized(lock1){
            synchronized (lock2){
                System.out.println("first thread in instanceMethod1");
            }
        }
    }

    public void instanceMethod2(){
        synchronized (lock2){
            synchronized (lock1){
                System.out.println("second thread in instanceMethod2");
            }
        }
    }

    public static void main(String[] args) {
        final DeadLockDemo dld = new DeadLockDemo();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                while (true){
                    dld.instanceMethod1();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thd1 = new Thread(r1);
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                while (true){
                    dld.instanceMethod2();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thd2 = new Thread(r2);
        thd1.start();
        thd2.start();
    }
}
