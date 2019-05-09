package com.study.chap03;

public class PC {
    public static void main(String[] args) {
        Shared s = new Shared();
        new Producer(s).start();
        new Consumer(s).start();
    }
}

class Shared {
    private char c;
    private volatile boolean writeable = true;

    synchronized void setSharedChar(char c) {
        while (!writeable) {
            try {
                System.out.println("set 线程的等待");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.c = c;
        writeable = false;
        notify();
        System.out.println("set 线程被唤醒");
    }

    synchronized char getSharedChar() {
        while (writeable) {
            try {
                System.out.println("get 线程的等待");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("get 线程被唤醒");
        writeable = true;
        notify();
        return c;
    }
}

class Producer extends Thread {
    private final Shared s;

    Producer(Shared s) {
        this.s = s;
    }

    @Override
    public void run() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            synchronized (s) {
                s.setSharedChar(ch);
                System.out.println(ch + " produced by producer");
            }
        }
    }
}

class Consumer extends Thread {
    private final Shared s;

    Consumer(Shared s) {
        this.s = s;
    }

    @Override
    public void run() {
        char ch;
        do {
            synchronized (s) {
                ch = s.getSharedChar();
                System.out.println(ch + " consumed by consumer");
            }
        } while (ch != 'Z');
    }
}