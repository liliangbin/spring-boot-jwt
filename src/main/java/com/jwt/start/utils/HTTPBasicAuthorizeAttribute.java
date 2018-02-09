package com.jwt.start.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.start.service.Audience;
import com.jwt.start.service.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/1/21  16:42
 */

@SuppressWarnings("restriction")
public class HTTPBasicAuthorizeAttribute implements Filter{

    private static String Name = "admin";
    private static String Password = "123456";

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub

        ResultStatusCode resultStatusCode = checkHTTPBasicAuthorize(request);
        if (resultStatusCode != ResultStatusCode.OK)
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ObjectMapper mapper = new ObjectMapper();

            ResultMsg resultMsg = new ResultMsg(ResultStatusCode.PERMISSION_DENIED.getErrcode(), ResultStatusCode.PERMISSION_DENIED.getErrmsg(), null);
            httpResponse.getWriter().write(mapper.writeValueAsString(resultMsg));
            return;
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    private ResultStatusCode checkHTTPBasicAuthorize(ServletRequest request)
    {
        try
        {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String auth = httpRequest.getHeader("Authorization");
            if ((auth != null) && (auth.length() > 6))
            {
                String HeadStr = auth.substring(0, 5).toLowerCase();
                if (HeadStr.compareTo("basic") == 0)
                {
                    auth = auth.substring(6, auth.length());
                    String decodedAuth = getFromBASE64(auth);
                    if (decodedAuth != null)
                    {
                        String[] UserArray = decodedAuth.split(":");

                        if (UserArray != null && UserArray.length == 2)
                        {
                            if (UserArray[0].compareTo(Name) == 0
                                    && UserArray[1].compareTo(Password) == 0)
                            {
                                return ResultStatusCode.OK;
                            }
                        }
                    }
                }
            }
            return ResultStatusCode.PERMISSION_DENIED;
        }
        catch(Exception ex)
        {
            return ResultStatusCode.PERMISSION_DENIED;
        }

    }

    private String getFromBASE64(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

}
