package wittgroupinc.com.authenticatordemo.models;

/**
 * Created by Pawan Gupta on 05-03-2017.
 */

public class LoginResponse extends BaseModel{
    private String access_token;
    private String token_type;
    private String expires_in;
    private String userName;
    private String designation;
    private String roles;
    private String qbUserId;
    private String issued;
    private String expires;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getQbUserId() {
        return qbUserId;
    }

    public void setQbUserId(String qbUserId) {
        this.qbUserId = qbUserId;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }


}
