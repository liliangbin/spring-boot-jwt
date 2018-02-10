package com.jwt.start.utils;

import com.jwt.start.model.AccessToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/9  18:41
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");
        //String aut = request.getHeader("Authorization");
        System.out.println(authToken);
        try {
            System.out.println("nihao ee");
            String auth = "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1MjE4NTE5NzUsInVzZXJJZCI6ImFkbWluIn0.o4hc49e03FIW33rVtFxn5Lj4SQ5Hv-PRsj53SNFeC-tzqPOFx5ilvbMrSQ5OqWJNwXRzRC5oerHr3qaJvLKsbg";
            AccessToken token = new AccessToken(authToken);
            System.out.println("token :------>=" + authToken);
            getSubject(request, response).login(token);
            System.out.println("登陆成功");
            chain.doFilter(request, response);

        } catch (Exception e) {
            System.out.println("youwenti l     ------>");
            return;
        }

    }
}
