package com.siui.sysapp.applock.data.model;

/**
 * Created by yunlong.zhang on 2017/6/7.
 */
public class AppModel {

    private int resid;
    private String packageName;
    private String sortLetter;
    private boolean choosed;

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSortLetter() {
        return sortLetter;
    }

    public void setSortLetter(String sortLetter) {
        this.sortLetter = sortLetter;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }
}
