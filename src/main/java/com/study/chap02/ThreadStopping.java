package com.study.chap02;

public class ThreadStopping {
    public static void main(String[] args) {
        class StoppadleThread extends Thread {

            private volatile boolean stopped;

            @Override
            public void run() {
                /*synchronized (this){*/
                    while (!stopped) {
                        System.out.println("running");
                    }
                /*}*/
            }

            /*synchronized*/ void stopThread() {
                System.out.println("run stopThread()");
                stopped = true;
            }
        }

        StoppadleThread thd = new StoppadleThread();
        thd.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thd.stopThread();
    }
}
