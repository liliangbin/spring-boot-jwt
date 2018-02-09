package com.jwt.start;

import com.jwt.start.model.LoginPara;
import com.jwt.start.model.UserInfo;
import com.jwt.start.repository.UserInfoRepository;
import com.jwt.start.utils.MyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StartApplicationTests {


	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	UserInfoRepository userInfoRepository;
	@Test
	public void contextLoads() {


		UserInfo user = userInfoRepository.findUserInfoByName("admin");
		LoginPara loginPara = new LoginPara();
		loginPara.setClientId("10");
		loginPara.setCaptchaCode("2");
		loginPara.setUserName("admin");
		loginPara.setPassword("123456");

		String md5Password = MyUtils.getMD5(loginPara.getPassword() + user.getSalt());

		logger.info(md5Password  +  "  mam");
	}

}
