---
title: shiro jwt
---

# spring-boot-jwt
jwt做一个授权管理 api
#### 序言
- 半吊子一个，大家有什么好意见或是简介，务必issue下
- 项目地址： https://github.com/liliangbin/spring-boot-jwt
- 同样，感谢一位大佬，也是在他的项目基础上建立起来的。
- 他的项目地址 ：https://github.com/Smith-Cruise/Spring-Boot-Shiro
- 但是同样因为他在于数据库的连接上没有搞定，所以也就是有了这个例子
- 不讲具体方法，说说程序的逻辑。
##### 第一步，shiro ，jwt maven配置。

```xml
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.7.0</version>
		</dependency>
		<dependency>
			<groupId>org.apache.shiro</groupId>
			<artifactId>shiro-spring</artifactId>
			<version>1.3.2</version>
		</dependency>
```

##### 添加shiro俩个基类。
- shiroConfig

    - 这个类是shiro的主入口。。包含的是shiro框架使用的逻辑。
        - 使用逻辑。。请求从shiroConfig这个类进去，然后经过过滤器，，过滤器是自己重写的，JwtAuthenticationFilter 只有过滤没有报错才能进入myealm这地方。。
        - 而进去myRealm后，进行的是doGetAuthenticationInfo 。这个类主要的是判断密码和用户名是否正确，(因为我们使用的是jwt传递过来的是一个token，，所以最后校验的时候哪里填的是俩个token，如果觉得有问题可以看看最后返回的那个类的参数。。）
        - 当我们的密码校验完毕后，我们才是最后的登陆成功。。。我们才能使用他的权限使用。

    - 同样这个类也是对于url匹配关系的配置。。
         ```

        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");
        // 访问401和404页面不通过我们的Filter
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/login","anon");
        filterRuleMap.put("/name","anon");
        //当然也是可以在controller中通过注解配置。

- myRealm
    - 该类的主要作用是鉴权和授权。
    - doGetAuthorizationInfo  这个方法主要是用来获取用户的权限，和角色。
    - doGetAuthenticationInfo 这个方法的作用主要是用来登陆的



##### jwt，需要做什么。

- 在jwt中我们会使用到一个token类，作为中间的一个传输对象。。在shiro中同样是有一个，所以我们需要的是让这个token类 实现一个接口

        public class AccessToken implements AuthenticationToken

       //这个地方重载的俩个方法名字其实和myRealm里面最后密码验证时一样的，，所以我们在那个地方才传递的是来个相同的token
         @Override
            public Object getPrincipal() {
                return access_token;
            }

            @Override
            public Object getCredentials() {
                return access_token;
            }


#### 因为要做的是前后端分离，所以也就是有了现在的这个样子，，放弃session  cookie， 使用jwt鉴权。

- apache shiro 当然还有其他的功能，，，另外有一个关于shiro的例子，
- https://github.com/liliangbin/apache-shiro



