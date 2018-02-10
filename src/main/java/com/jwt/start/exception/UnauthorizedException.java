package com.jwt.start.exception;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/9  16:22
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
