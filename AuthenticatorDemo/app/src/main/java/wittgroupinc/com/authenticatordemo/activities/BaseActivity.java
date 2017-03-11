package wittgroupinc.com.authenticatordemo.activities;

import android.support.v7.app.AppCompatActivity;

import wittgroupinc.com.authenticatordemo.NetworkCallBack;

/**
 * Created by Pawan Gupta on 05-03-2017.
 */

public class BaseActivity extends AppCompatActivity implements NetworkCallBack {
    @Override
    public void onSuccess(int requestCode, Object object) {
    }

    @Override
    public void onFailure(int requestCode, String message) {
    }

    @Override
    public void onStartRequest(int requestCode) {
    }

    @Override
    public void onCancel(int requestCode) {
    }

    @Override
    public void onProgress(int percentage) {
    }
}
