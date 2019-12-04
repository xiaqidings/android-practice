package com.baidumapyongche.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DevicePolicyManager mPolicyManager;
    private ComponentName mComponentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        LockScreen(null);
    }

    private void LockScreen(View view) {
        mComponentName = new ComponentName(this, LockReceiver.class);
        if (mPolicyManager.isAdminActive(mComponentName)) { // 判断是否有权限（激活了设备管理器）
            mPolicyManager.lockNow();
            finish();
        } else {
            activeManager();
            this.finish();
        }
    }

    private void activeManager() {
        // 使用隐式意图调用系统方法来激活指定的设备管理器
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "一键锁屏");
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        if (mPolicyManager != null && mPolicyManager.isAdminActive(mComponentName)) {
            mPolicyManager.lockNow();
            finish();
        }
        super.onResume();
    }
}
