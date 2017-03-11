package wittgroupinc.com.authenticatordemo.helpers;

import android.content.Context;

import com.loopj.android.http.RequestParams;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import wittgroupinc.com.authenticatordemo.ApiUrl;
import wittgroupinc.com.authenticatordemo.NetworkCallBack;
import wittgroupinc.com.authenticatordemo.WGRequester;

/**
 * Created by Pawan Gupta on 03-03-2017.
 */

public class WGServerAuthenticator implements ServerAuthenticator {
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static Map<String, String> mCredentialsRepo;

    static {
        Map<String, String> credentials = new HashMap<String, String>();
        credentials.put("demo@example.com", "demo");
        credentials.put("foo@example.com", "foobar");
        credentials.put("user@example.com", "pass");
        mCredentialsRepo = Collections.unmodifiableMap(credentials);
    }

    @Override
    public String signUp(String email, String username, String password) {
        // TODO: register new user on the server and return its auth token
        return null;
    }


    public String signIn(String email, String password) {
        String authToken = null;
        final DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        if (mCredentialsRepo.containsKey(email)) {
            if (password.equals(mCredentialsRepo.get(email))) {
                authToken = email + "-" + df.format(new Date());
            }
        }
        return authToken;
    }

    @Override
    public String signIn(Context context, String email, String password, NetworkCallBack callBack) {
        String authToken = null;
        RequestParams params = new RequestParams();
        params.put("UserName", email);
        params.put("password", password);
        params.put("grant_type", "password");
        WGRequester.getInstance(context).post(1000, ApiUrl.LOGIN_URL, params, callBack);

        return authToken;
    }
}
