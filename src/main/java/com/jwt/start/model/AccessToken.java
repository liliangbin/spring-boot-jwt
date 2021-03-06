package com.jwt.start.model;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/1/21  15:13
 */
public class AccessToken implements AuthenticationToken {

    private String access_token;
    private String token_type;

    public AccessToken(String access_token) {
        this.access_token = access_token;
    }

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

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    private long expires_in;

    public AccessToken() {
    }

    @Override
    public Object getPrincipal() {
        return access_token;
    }

    @Override
    public Object getCredentials() {
        return access_token;
    }
}
