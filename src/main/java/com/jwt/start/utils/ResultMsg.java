package com.jwt.start.utils;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/1/21  15:38
 */
public class ResultMsg {

    private int errcode;
    private String errmsg;
    private Object p2pdata;

    public ResultMsg() {
    }

    public ResultMsg(int errcode, String errmsg, Object p2pdata) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.p2pdata = p2pdata;
    }

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

    public Object getP2pdata() {
        return p2pdata;
    }

    public void setP2pdata(Object p2pdata) {
        this.p2pdata = p2pdata;
    }
}
