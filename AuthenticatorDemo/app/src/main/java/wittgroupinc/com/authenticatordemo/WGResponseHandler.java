package wittgroupinc.com.authenticatordemo;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.net.HttpURLConnection;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Pawan Gupta on 05-03-2017.
 */

public class WGResponseHandler extends AsyncHttpResponseHandler {
    private int requestCode;
    private NetworkCallBack networkCallback;
    private String request;
    private int percentage = 0;

    public WGResponseHandler(int requestCode, String request, NetworkCallBack networkCallback) {
        this.requestCode = requestCode;
        this.networkCallback = networkCallback;
        this.request = request;
        percentage = 0;
    }

    @Override
    public void onStart() {
        super.onStart();
        Utils.log("Request to Server:" + requestCode + ",Url:" + request);
        if(networkCallback!=null)
            networkCallback.onStartRequest(requestCode);
    }

    @Override
    public void onCancel() {
        super.onCancel();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED || statusCode == HttpURLConnection.HTTP_ACCEPTED) {
            String response = new String(responseBody);
            Utils.log("Response :"+requestCode+"-->" + response);
            if(networkCallback!=null)

                networkCallback.onSuccess(requestCode, response);
        } else {
            if(networkCallback!=null)
                networkCallback.onFailure(requestCode, "Server returns the code" + statusCode);
        }
    }

    @Override
    public void onProgress(long bytesWritten, long totalSize) {

        super.onProgress(bytesWritten, totalSize);
        if(networkCallback!=null)
            networkCallback.onProgress((int) ((bytesWritten * 100) / totalSize));

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        String errorResponse = error.getMessage();
        error.printStackTrace();
        if(responseBody!=null && responseBody.length>0)
        {
            String response = new String(responseBody);
            Utils.log("Response :"+requestCode+"-->" + response);
        }
        Utils.log("Response from Server for Request Code :" + requestCode + "-->" + errorResponse + statusCode + error.getMessage());
        if(networkCallback!=null)
            networkCallback.onFailure(requestCode, errorResponse);
    }
}
