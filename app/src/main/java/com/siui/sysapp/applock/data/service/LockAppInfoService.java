package com.siui.sysapp.applock.data.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.siui.sysapp.applock.data.entity.InstallAppInfo;
import com.siui.sysapp.applock.data.entity.LockAppInfo;
import com.siui.sysapp.applock.data.model.DaoMaster;
import com.siui.sysapp.applock.data.model.DaoSession;
import com.siui.sysapp.applock.data.model.LockAppInfoDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by yunlong.zhang on 2017/6/4.
 */
public class LockAppInfoService {
    private LockAppInfoDao lockAppInfoDao;
    private DaoSession daoSession;
    private Context context;
    private InstallAppInfoService installAppInfoService;

    public LockAppInfoService(Context context) {
        this.context = context;
        installAppInfoService = new InstallAppInfoService(context);
    }

    public LockAppInfoDao getLockAppInfoDaoInstance() {
        if (lockAppInfoDao == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    "lockappinfo");
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            lockAppInfoDao = daoSession.getLockAppInfoDao();
        }
        return lockAppInfoDao;
    }

    public List<LockAppInfo> getLockApps() {
        if (lockAppInfoDao != null) {
            List<LockAppInfo> lockAppInfos = lockAppInfoDao.queryBuilder().list();
            return lockAppInfos;
        }
        return null;
    }

    public void addAppLocked(InstallAppInfo installAppInfo) {
        if (lockAppInfoDao != null) {
            LockAppInfo lockAppInfo = new LockAppInfo();
            lockAppInfo.setInstallappinfo(installAppInfo);
            lockAppInfo.setLockappid(installAppInfo.getId());
            lockAppInfo.setLock(true);
            lockAppInfoDao.save(lockAppInfo);
        }
    }

    public void removeLockAppinfo(LockAppInfo lockAppInfo) {
        if (lockAppInfoDao != null) {
            lockAppInfoDao.delete(lockAppInfo);
        }
    }

    public LockAppInfo getLockAppByName(String packageName) {
        InstallAppInfo installAppInfo = installAppInfoService.getInstallAppByName(packageName);
        if (lockAppInfoDao != null) {
            Query<LockAppInfo> query = lockAppInfoDao.queryBuilder().
                    where(LockAppInfoDao.Properties.Lockappid.eq(installAppInfo.getId())).build();
            return query.unique();
        }
        return null;
    }

}
