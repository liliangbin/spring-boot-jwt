package com.jwt.start.utils;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/1/21  15:39
 */
public enum ResultStatusCode {
    OK(0, "OK"),
    SYSTEM_ERR(30001, "System error"),
    INVALID_CLIENTID(30003, "Invalid clientid"),
    INVALID_PASSWORD(30004, "User name or password is incorrect"),
    INVALID_CAPTCHA(30005, "Invalid captcha or captcha overdue"),
    INVALID_TOKEN(30006, "Invalid token"),
    PERMISSION_DENIED(3007,"不能用");

    private int errcode;
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    private ResultStatusCode(int Errode, String ErrMsg) {
        this.errcode = Errode;
        this.errmsg = ErrMsg;
    }
}
