package com.siui.sysapp.applock.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.siui.sysapp.applock.IAppLockManager;

/**
 * Created by yunlong.zhang on 2017/6/5.
 */
public class ComService extends Service {

    LockHandler lockHandler;

    class LockHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        lockHandler = new LockHandler();
    }

    /** remote call **/
    private IAppLockManager.Stub mbinder = new IAppLockManager.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) throws RemoteException {
        }

        @Override
        public boolean isLock(String packageName) throws RemoteException {
            return false;
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }


}
