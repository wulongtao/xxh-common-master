package com.xxh.common.dp.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wulongtao on 2017/7/5.
 */
public class MyObserver implements Observer {

    public void update(Observable o, Object arg) {
        System.out.println("update : " + arg);
    }
}
