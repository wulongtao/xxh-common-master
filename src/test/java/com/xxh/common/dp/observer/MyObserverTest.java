package com.xxh.common.dp.observer;

/**
 * Created by wulongtao on 2017/7/5.
 */
public class MyObserverTest {

    public static void main(String[] args) {
        MyObserver myObserver = new MyObserver();
        MyObservable myObservable = new MyObservable();
        myObservable.addObserver(myObserver);

        myObservable.setName("abc");

    }
}
