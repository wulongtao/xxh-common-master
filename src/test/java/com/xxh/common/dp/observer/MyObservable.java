package com.xxh.common.dp.observer;

import java.util.Observable;

/**
 * Created by wulongtao on 2017/7/5.
 */
public class MyObservable extends Observable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        super.setChanged();
        super.notifyObservers(name);
        this.name = name;
    }
}
