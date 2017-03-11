package wittgroupinc.com.authenticatordemo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

import wittgroupinc.com.authenticatordemo.helpers.AccountHelper;
import wittgroupinc.com.authenticatordemo.utils.AuthPreferences;

/**
 * Created by Pawan Gupta on 05-03-2017.
 */

public class WGRequester {

    private static final int DEFAULT_CONNECTION_TIMEOUT = 60000;  //reduced to 30 secs
    private static WGRequester wgRequester;
    private static AsyncHttpClient asyncHttpClient;
    private Context context;

    private static AsyncHttpClient client = new AsyncHttpClient();


    public WGRequester(Context context) {
        this.context = context;
        if (asyncHttpClient == null) {
            asyncHttpClient = new AsyncHttpClient();
        }

    }

    public static WGRequester getInstance(Context context) {
        return wgRequester == null ? new WGRequester(context)
                : wgRequester;
    }


    private  AsyncHttpClient getClient() {
        // Return the synchronous HTTP client when the thread is not prepared
        asyncHttpClient.setTimeout(DEFAULT_CONNECTION_TIMEOUT);
        asyncHttpClient.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
        asyncHttpClient.setResponseTimeout(DEFAULT_CONNECTION_TIMEOUT);
        AuthPreferences mAuthPreferences = new AuthPreferences(context);
        AccountManager mAccountManager = AccountManager.get(context);
        Account account = AccountHelper.getAccount(context, mAuthPreferences.getAccountName());
       // String bearerToken = "bearer " + mAuthPreferences.getAuthToken();
       // asyncHttpClient.addHeader("Authorization", bearerToken);
        if (account != null) {
            mAccountManager.getAuthToken(account, AccountHelper.AUTH_TOKEN_TYPE, null, false, new AccountManagerCallback<Bundle>() {
                @Override
                public void run(AccountManagerFuture<Bundle> result) {
                    try {
                        Bundle bundle = result.getResult();
                        String authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
                        String bearerToken = "bearer " + authToken;
                        asyncHttpClient.addHeader("Authorization", bearerToken);
                    } catch (OperationCanceledException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (AuthenticatorException e) {
                        e.printStackTrace();
                    }


                }
            }, null);

        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return asyncHttpClient;
    }

    public void get(int requestCode, String url, RequestParams params, NetworkCallBack callBack) {
        final String requestUrl = ApiUrl.getEndUrl(url);

        WGResponseHandler responseHandler = new WGResponseHandler(requestCode, requestUrl, callBack);
        RequestHandle requestHandle = getClient().get(requestUrl, params, responseHandler);
        requestHandle.setTag(requestCode);


    }

    public void post(int requestCode, String url, RequestParams params, NetworkCallBack callBack) {
        final String requestUrl = ApiUrl.getEndUrl(url);
        WGResponseHandler responseHandler = new WGResponseHandler(requestCode, requestUrl, callBack);
        getClient().removeAllHeaders();
        RequestHandle requestHandle = getClient().post(context,requestUrl, params, responseHandler);
        requestHandle.setTag(requestCode);

    }

    public void cancelRequests(int... requestCodes) {
        if (getClient() != null && requestCodes != null) {
            for (int requestCode : requestCodes) {
                Utils.log("Cancelling the request-->" + requestCode);
                getClient().cancelRequestsByTAG(requestCode, true);
            }

        }
    }


    public void put(int requestCode, String url, RequestParams params, NetworkCallBack callBack) {
        final String requestUrl = ApiUrl.getEndUrl(url);
        WGResponseHandler responseHandler = new WGResponseHandler(requestCode, requestUrl, callBack);

        RequestHandle requestHandle = getClient().put(context,requestUrl, params, responseHandler);
        requestHandle.setTag(requestCode);
    }
}
