package com.siui.sysapp.applock.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yunlong.zhang on 2017/6/3.
 */
@Entity
public class InstallAppInfo {
    @Id
    private Long id;
    private String packageName;
    @Generated(hash = 900635321)
    public InstallAppInfo(Long id, String packageName) {
        this.id = id;
        this.packageName = packageName;
    }
    @Generated(hash = 1329284829)
    public InstallAppInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPackageName() {
        return this.packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
