package wittgroupinc.com.authenticatordemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import wittgroupinc.com.authenticatordemo.helpers.WGAuthenticator;

/**
 * Created by Pawan Gupta on 02-03-2017.
 */

public class AuthenticationService extends Service {

    private static final String TAG = "AuthenticationService";

    private WGAuthenticator mAuthenticator;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }


    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new WGAuthenticator(this);
    }


}
