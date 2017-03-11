package wittgroupinc.com.authenticatordemo;

/**
 * Created by Pawan Gupta on 05-03-2017.
 */
public class ApiUrl {
    private static final String BASE_URL = "http://onblickapiqa1.azurewebsites.net";
    public static final String LOGIN_URL = "Token";
    public static final String GET_JOB_BY_SKILL = "GetBasicUserInfo";

    public static String getEndUrl(String url){
       return String.format("%s/%s", BASE_URL, url);
    }
}
