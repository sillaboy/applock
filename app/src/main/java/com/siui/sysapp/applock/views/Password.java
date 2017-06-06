package com.siui.sysapp.applock.views;

public enum Password {

    NUMBER(0),
    TEXT(1);

    private int type;

    private Password(int type) {
        this.type = type;
    }

}
