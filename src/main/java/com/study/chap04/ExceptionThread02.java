package com.study.chap04;

public class ExceptionThread02 {

    public static void main(String[] args) {
        Runnable r = () -> {
            int x = 1 / 0;
        };
        Thread t = new Thread(r);
        Thread.UncaughtExceptionHandler uceh;
        uceh = new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Caught throwable " + e + "for thread " + t);
            }
        };
        t.setUncaughtExceptionHandler(uceh);
        uceh = new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Default uncaught exception handler");
                System.out.println("Caught throwable " + e + "for thread " + t);
            }
        };
        Thread.setDefaultUncaughtExceptionHandler(uceh);
        t.start();
    }
}
