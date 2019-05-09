package com.study.chap03;

/**
 * 章节练习题
 */
public class Exercise {


    public static void main(String[] args) {

        GoCount goCount = new GoCount();

        R1 r1 = new R1(goCount);

        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r1, "t2");
        Thread t3 = new Thread(r1, "t3");
        t1.start();
        t2.start();
        t3.start();

        R2 r2 = new R2(goCount);
        Thread t4 = new Thread(r2);
        t4.start();
    }
}

class GoCount {

     static int count = 0;

    synchronized void addCount() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 线程正在等待中");
        try {
            Thread.sleep(2000);
            wait();
            System.out.println(name + " 线程已终止");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void checkCount() {
        while (count != 3) {
            try {
                Thread.sleep(200);
                System.out.println("唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("唤醒所有线程");
        notifyAll();
    }
}

class R1 implements Runnable {

    private final GoCount goCount;

    R1(GoCount goCount) {
        this.goCount = goCount;
    }

    @Override
    public void run() {
        GoCount.count++;
        synchronized (goCount){
            goCount.addCount();
        }

    }
}

class R2 implements Runnable {

    private final GoCount goCount;

    R2(GoCount goCount) {
        this.goCount = goCount;
    }

    @Override
    public void run() {
        synchronized (goCount) {
            goCount.checkCount();
        }
    }
}