package com.study.chap04;

public class ExceptionThread01 {
    public static void main(String[] args) {
        Runnable r = () ->{
          int x = 1/0;
        };
        Thread t = new Thread(r);
        t.start();
    }
}
