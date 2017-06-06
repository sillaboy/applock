package com.siui.sysapp.applock.data.model;

import android.content.Context;

import com.siui.sysapp.applock.data.entity.InstallAppInfo;
import com.siui.sysapp.applock.data.entity.LockAppInfo;
import com.siui.sysapp.applock.data.service.InstallAppInfoService;
import com.siui.sysapp.applock.data.service.LockAppInfoService;

import java.util.HashMap;

/**
 * Created by yunlong.zhang on 2017/6/5.
 */
public class StorageManager {

    private Context mContext;
    private HashMap<String, InstallAppInfo> installApps;
    private HashMap<String, LockAppInfo> lockApps;
    private InstallAppInfoService installAppInfoService;
    private LockAppInfoService lockAppInfoService;

    public StorageManager(Context mContext) {
        this.mContext = mContext;
        installApps = new HashMap<>();
        lockApps = new HashMap<>();
        installAppInfoService = new InstallAppInfoService(mContext);
        lockAppInfoService = new LockAppInfoService(mContext);
        try {
            for (InstallAppInfo appInfo : installAppInfoService.getInstallApp()) {
                installApps.put(appInfo.getPackageName(), appInfo);
            }
            for (LockAppInfo appInfo : lockAppInfoService.getLockApps()) {
                InstallAppInfo installAppInfo = appInfo.getInstallappinfo();
                lockApps.put(installAppInfo.getPackageName(), appInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removePackage(String packageName, LockAppInfo lockAppInfo) {
        if (installApps.containsKey(packageName)) installApps.remove(packageName);
        if (lockApps.containsKey(packageName)) lockApps.remove(packageName);
        lockAppInfoService.removeLockAppinfo(lockAppInfo);
        installAppInfoService.delInstallApp(packageName);
    }

    public void removeFromLockList(String packageName, LockAppInfo lockAppInfo) {
        if (lockApps.containsKey(packageName)) lockApps.remove(packageName);
        lockAppInfoService.removeLockAppinfo(lockAppInfo);
    }

    public boolean isLocked (String packageName) {
        LockAppInfo lockAppInfo = lockAppInfoService.getLockAppByName(packageName);
        if (lockAppInfo != null) {
            return lockAppInfo.getLock();
        } else {
            return false;
        }
    }

    public void setLocked (String packageName) {
        InstallAppInfo installAppInfo = installAppInfoService.getInstallAppByName(packageName);
        if (installAppInfo != null) {
            lockAppInfoService.addAppLocked(installAppInfo);
        }
    }

}
