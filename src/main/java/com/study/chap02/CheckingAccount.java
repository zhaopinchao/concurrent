package com.study.chap02;

public class CheckingAccount {
    private int balance;
    public CheckingAccount(int initialBalance){
        balance = initialBalance;
    }
    public synchronized boolean withdraw(int amount){
        if(amount <= balance){
            try {
                Thread.sleep((int)(Math.random()*200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        final CheckingAccount ca = new CheckingAccount(100);
        Runnable r = () ->{
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                System.out.println(name + "withdraw $10 " + ca.withdraw(10));
            }
        };
        Thread thread = new Thread(r);
        thread.setName("Husband");
        Thread thread1 = new Thread(r);
        thread1.setName("Wife");
        thread.start();
        thread1.start();
    }
}
