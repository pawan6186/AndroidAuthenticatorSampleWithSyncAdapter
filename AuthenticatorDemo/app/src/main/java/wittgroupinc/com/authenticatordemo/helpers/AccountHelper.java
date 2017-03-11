package wittgroupinc.com.authenticatordemo.helpers;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import wittgroupinc.com.authenticatordemo.utils.AuthPreferences;

/**
 * Created by Pawan Gupta on 03-03-2017.
 */
public class AccountHelper {
    public static final String ACCOUNT_TYPE = "wittgroupinc.com.authenticatordemo.basicsyncadapter";
    public static final String AUTH_TOKEN_TYPE = "wittgroupinc.com.authenticatordemo.basicsyncadapter";
    public static final String ACCOUNT_NAME = "sync";
    public AuthPreferences mAuthPreferences;
    public static ServerAuthenticator mServerAuthenticator = new WGServerAuthenticator();

    public static Account getAccount(Context context, String accountName) {
        AccountManager accountManager = AccountManager.get(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
        for (Account account : accounts) {
            if (account.name.equalsIgnoreCase(accountName)) {
                return account;
            }
        }
        return null;
    }

    public static void logout(Activity context, AccountManagerCallback callback) {
        AuthPreferences mAuthPreferences = new AuthPreferences(context);
        AccountManager mAccountManager = AccountManager.get(context);
        mAccountManager.invalidateAuthToken(AccountHelper.ACCOUNT_TYPE, mAuthPreferences.getAuthToken());
        mAuthPreferences.setAuthToken(null);
        mAuthPreferences.setUsername(null);
        mAccountManager.getAuthTokenByFeatures(AccountHelper.ACCOUNT_TYPE, AccountHelper.AUTH_TOKEN_TYPE, null, context, null, null, callback, null);

    }


}
