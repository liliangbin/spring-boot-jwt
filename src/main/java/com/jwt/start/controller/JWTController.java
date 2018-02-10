package com.jwt.start.controller;

import com.jwt.start.Bean.ResponseBean;
import com.jwt.start.model.AccessToken;
import com.jwt.start.model.LoginPara;
import com.jwt.start.model.UserInfo;
import com.jwt.start.repository.UserInfoRepository;
import com.jwt.start.service.Audience;
import com.jwt.start.service.JwtHelper;
import com.jwt.start.utils.JwtUtil;
import com.jwt.start.utils.MyUtils;
import com.jwt.start.utils.ResultMsg;
import com.jwt.start.utils.ResultStatusCode;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/1/21  15:15
 */

@RestController
public class JWTController {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private Audience audienceEntity;

    @PostMapping("/login")
    public String string(){

        String jwt = JwtUtil.generateToken("admin");
        return jwt;



    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }

    @GetMapping("/name")
    public String bjb(){
        System.out.println("nihaosijie");
        return  "wulala;;a";
    }

    @RequestMapping("/oauth/token")
    public Object getAccessToken(String clientId,String username,String password) {
        LoginPara loginPara= new LoginPara(clientId,username,password);
        ResultMsg resultMsg;
        try {
            if (loginPara.getClientId() == null
                    || (loginPara.getClientId().compareTo(audienceEntity.getClientId()) != 0)) {
                resultMsg = new ResultMsg(ResultStatusCode.INVALID_CLIENTID.getErrcode(),
                        ResultStatusCode.INVALID_CLIENTID.getErrmsg(), null);
                return resultMsg;
            }

            //验证码校验在后面章节添加
            UserInfo user = userInfoRepository.findUserInfoByName(loginPara.getUserName());
            if (user == null) {
                resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
                        ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
                return resultMsg;
            } else {
                String md5Password = MyUtils.getMD5(loginPara.getPassword() + user.getSalt());

                if (md5Password.compareTo(user.getPassword()) != 0) {
                    resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
                            ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
                    return resultMsg;
                }
            }

            //拼装accessToken
            String accessToken = JwtHelper.createJWT(loginPara.getUserName(), String.valueOf(user.getName()),
                    user.getRole(), audienceEntity.getClientId(), audienceEntity.getName(),
                    audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());

            //返回accessToken
            AccessToken accessTokenEntity = new AccessToken();
            accessTokenEntity.setAccess_token(accessToken);
            accessTokenEntity.setExpires_in(audienceEntity.getExpiresSecond());
            accessTokenEntity.setToken_type("bearer");
            resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(),
                    ResultStatusCode.OK.getErrmsg(), accessTokenEntity);
            return resultMsg;

        } catch (Exception ex) {
            resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrcode(),
                    ResultStatusCode.SYSTEM_ERR.getErrmsg(), null);
            return resultMsg;
        }


    }

}



