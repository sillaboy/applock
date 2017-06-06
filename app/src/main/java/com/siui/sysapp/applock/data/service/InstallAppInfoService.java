package com.siui.sysapp.applock.data.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.siui.sysapp.applock.data.entity.InstallAppInfo;
import com.siui.sysapp.applock.data.model.DaoMaster;
import com.siui.sysapp.applock.data.model.DaoSession;
import com.siui.sysapp.applock.data.model.InstallAppInfoDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by yunlong.zhang on 2017/6/3.
 */
public class InstallAppInfoService {
    private Context context = null;
    private DaoSession daoSession = null;
    private InstallAppInfoDao installAppInfoDao = null;

    public InstallAppInfoService(Context ctx) {
        this.context = ctx;
        init();
    }

    public void init() {
        if (installAppInfoDao == null) {
            DaoMaster.DevOpenHelper devOpenHelper =
                    new DaoMaster.DevOpenHelper(context, "installApps", null);
            SQLiteDatabase db = devOpenHelper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            installAppInfoDao = daoSession.getInstallAppInfoDao();
        }
    }

    public InstallAppInfoDao getInstallAppInfoDao() {
        if (installAppInfoDao == null) {
            DaoMaster.DevOpenHelper devOpenHelper =
                    new DaoMaster.DevOpenHelper(context, "installApps", null);
            SQLiteDatabase db = devOpenHelper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            installAppInfoDao = daoSession.getInstallAppInfoDao();
        }
        return installAppInfoDao;
    }

    public long addInstalApp(String packageName) {
        if (installAppInfoDao != null) {
            return installAppInfoDao.insert(new InstallAppInfo(null, packageName));
        }
        return -1;
    }

    public boolean delInstallApp(String packageName) {
        if (installAppInfoDao != null) {
            DeleteQuery<InstallAppInfo> insDelQuery = installAppInfoDao
                    .queryBuilder()
                    .where(InstallAppInfoDao.Properties.PackageName.eq(packageName))
                    .buildDelete();
            insDelQuery.executeDeleteWithoutDetachingEntities();
            return true;
        }
        return false;
    }

    public boolean isInstallApp(String packageName) {
        if (installAppInfoDao != null) {
            List<InstallAppInfo> installApps = installAppInfoDao.queryBuilder().
                            where(InstallAppInfoDao.Properties.PackageName.eq(packageName)).list();
            if (installApps.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public InstallAppInfo getInstallAppByName(String packageName) {
        if (installAppInfoDao != null) {
            List<InstallAppInfo> installApps = installAppInfoDao.queryBuilder().
                    where(InstallAppInfoDao.Properties.PackageName.eq(packageName)).list();
            if (installApps.size() > 0) {
                return installApps.get(0);
            }
        }
        return null;
    }

    public InstallAppInfo getInstallApp(String packageName) {
        if (installAppInfoDao !=null) {
            Query<InstallAppInfo> query = installAppInfoDao.queryBuilder().
                    where(InstallAppInfoDao.Properties.PackageName.eq(packageName)).build();
            return query.unique();
        }
        return null;
    }

    public List<InstallAppInfo> getInstallApp() {
        if (installAppInfoDao != null) {
            List<InstallAppInfo> installApps = installAppInfoDao.queryBuilder().list();
            return installApps;
        }
        return null;
    }
}
