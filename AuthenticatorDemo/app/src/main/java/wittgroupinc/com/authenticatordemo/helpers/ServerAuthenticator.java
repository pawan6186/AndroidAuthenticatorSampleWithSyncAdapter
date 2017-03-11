package wittgroupinc.com.authenticatordemo.helpers;

import android.content.Context;

import wittgroupinc.com.authenticatordemo.NetworkCallBack;

/**
 * Created by Pawan Gupta on 03-03-2017.
 */
public interface ServerAuthenticator {

    /**
     * Tells the server to create the new user and return its auth token.
     * @param email
     * @param username
     * @param password
     * @return Access token
     */
    public String signUp (final String email, final String username, final String password);

    /**
     * Logs the user in and returns its auth token.
     * @param email
     * @param password
     * @return Access token
     */
    public String signIn (final String email, final String password);

    public String signIn (Context context, final String email, final String password, NetworkCallBack callBack);

}