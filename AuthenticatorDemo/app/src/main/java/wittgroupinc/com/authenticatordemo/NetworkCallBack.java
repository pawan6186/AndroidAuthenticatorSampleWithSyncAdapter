package wittgroupinc.com.authenticatordemo;

/**
 * Created by Pawan Gupta on 05-03-2017.
 */
public interface NetworkCallBack {
    public void onSuccess(int requestCode, Object object);

    public void onFailure(int requestCode, String message);

    public void onStartRequest(int requestCode);

    public void onCancel(int requestCode);

    public void onProgress(int percentage);
}
