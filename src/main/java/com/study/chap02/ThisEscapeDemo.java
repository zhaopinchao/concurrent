package com.study.chap02;

public class ThisEscapeDemo {
    private static ThisEscapeDemo lastCreatedInstance;

    public ThisEscapeDemo() {
        lastCreatedInstance = this;
    }
}
