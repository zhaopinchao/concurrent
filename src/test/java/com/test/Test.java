package com.test;

public class Test {

    @org.junit.Test
    public void test(){
        System.out.println(getCount());
        System.out.println(getCount());
    }
    public static int count = 0;

    public int getCount(){
        return count++;
    }
}
