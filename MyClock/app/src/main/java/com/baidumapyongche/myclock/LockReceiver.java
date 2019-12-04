package com.baidumapyongche.myclock;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

public class LockReceiver extends DeviceAdminReceiver {

    public static final String TAG = "xia";

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);
        Log.e(TAG, "onReceive: ");
    }

    @Override
    public void onEnabled(@NonNull Context context, @NonNull Intent intent) {
        super.onEnabled(context, intent);
        Log.e(TAG, "onEnabled: 激活使用");
    }

    @Override
    public void onDisabled(@NonNull Context context, @NonNull Intent intent) {
        super.onDisabled(context, intent);
        Log.e(TAG, "onDisabled: 取消激活");

        System.out.println("测试提交是否成功");
    }
}
