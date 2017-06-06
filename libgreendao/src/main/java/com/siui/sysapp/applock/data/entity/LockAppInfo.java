package com.siui.sysapp.applock.data.entity;

import com.siui.sysapp.applock.data.model.DaoSession;
import com.siui.sysapp.applock.data.model.InstallAppInfoDao;
import com.siui.sysapp.applock.data.model.LockAppInfoDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by yunlong.zhang on 2017/6/3.
 */
@Entity
public class LockAppInfo {
    @Id
    private Long id;
    private long lockappid;
    @ToOne(joinProperty = "lockappid")
    private InstallAppInfo installappinfo;
    private boolean islock;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 831569381)
    private transient LockAppInfoDao myDao;
    @Generated(hash = 1273804519)
    public LockAppInfo(Long id, long lockappid) {
        this.id = id;
        this.lockappid = lockappid;
        islock = true;
    }
    @Generated(hash = 1230897063)
    public LockAppInfo() {
        islock = true;
    }

    public boolean getLock() {
        return islock;
    }
    public void setLock(boolean islock) {
        this.islock = islock;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getLockappid() {
        return this.lockappid;
    }
    public void setLockappid(long lockappid) {
        this.lockappid = lockappid;
    }
    @Generated(hash = 1402082194)
    private transient Long installappinfo__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1405033120)
    public InstallAppInfo getInstallappinfo() {
        long __key = this.lockappid;
        if (installappinfo__resolvedKey == null
                || !installappinfo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InstallAppInfoDao targetDao = daoSession.getInstallAppInfoDao();
            InstallAppInfo installappinfoNew = targetDao.load(__key);
            synchronized (this) {
                installappinfo = installappinfoNew;
                installappinfo__resolvedKey = __key;
            }
        }
        return installappinfo;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 212399369)
    public void setInstallappinfo(@NotNull InstallAppInfo installappinfo) {
        if (installappinfo == null) {
            throw new DaoException(
                    "To-one property 'lockappid' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.installappinfo = installappinfo;
            lockappid = installappinfo.getId();
            installappinfo__resolvedKey = lockappid;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1960203128)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLockAppInfoDao() : null;
    }
    
}
