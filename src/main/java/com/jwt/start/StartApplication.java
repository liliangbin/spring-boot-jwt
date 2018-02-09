package com.jwt.start;

import com.jwt.start.service.Audience;
import com.jwt.start.utils.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
/*	@Bean
	public FilterRegistrationBean basicFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBasicAuthorizeAttribute httpBasicFilter = new HTTPBasicAuthorizeAttribute();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/user/getuser");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}*/
/*
	@Bean
	public FilterRegistrationBean jwtFilterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/user/getusers");
		registrationBean.setFilter(httpBearerFilter);
		System.out.println("鉴权操作入口");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}*/

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(
				"/user/**");
		registrationBean.setFilter(filter);
		return registrationBean;
	}


}
