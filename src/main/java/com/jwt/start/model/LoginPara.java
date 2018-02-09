package com.jwt.start.model;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/1/21  15:06
 */
public class LoginPara {


    private String clientId;
    private String userName;
    private String password;
    private String captchaCode;

    public String getClientId() {

        return clientId;

    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    private String captchaValue;

    public LoginPara(String clientId, String userName, String password) {
        this.clientId = clientId;
        this.userName = userName;
        this.password = password;
    }

    public LoginPara() {
    }
}
