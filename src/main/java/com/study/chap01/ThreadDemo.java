package com.study.chap01;

public class ThreadDemo {
    public static void main(String[] args) {
        boolean isDaemon = args.length == 0;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Thread thd = Thread.currentThread();
                while(true){
                    System.out.printf("%s is %s alive and in %s "+ "state%n",thd.getName(), thd.isAlive(), thd.getState());
                }
            }
        };
        Thread t1 = new Thread(r, "thd1");
        if(isDaemon){
            t1.setDaemon(true);
        }
        System.out.printf("%s is %s alive and in %s state%n",t1.getName(),t1.isAlive() ? "" :"not ",t1.getState());
        Thread t2 = new Thread(r);
        t2.setName("thd2");
        if(isDaemon){
            t2.setDaemon(true);
        }
        System.out.printf("%s is %s alive and in %s state%n",t2.getName(),t2.isAlive() ? "" :"not ",t2.getState());
        t1.start();
        t2.start();
        Thread t3 = new Thread(r);
        t3.start();
    }
}
