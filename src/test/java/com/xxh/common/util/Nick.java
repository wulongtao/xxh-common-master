package com.xxh.common.util;

/**
 * Created by wulongtao on 2017/7/28.
 */
public class Nick {
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    @Override
    public String toString() {
        return "Nick{" +
                "nick='" + nick + '\'' +
                '}';
    }
}
