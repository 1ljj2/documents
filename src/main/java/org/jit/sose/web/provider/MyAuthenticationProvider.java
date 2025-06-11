package org.jit.sose.web.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jit.sose.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认身份认证(用户名密码)<br>
 * 验证用户身份的合法性<br>
 * 根据用户输入的用户名查找用户信息，将查询到的密码和输入的密码比较
 *
 * @author wangyue
 * @date 2020-01-05 15:43:28
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    RedisService redisService;



    /*
     * AuthenticationProvider:用于认证的，可以通过实现这个接口来定制我们自己的认证逻辑
     */
    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 注入密码加密方式
     *
     * @return 加密工具
     */
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 进行身份认证
     *
     * @param authentication 账号密码
     * @return Authentication 通过账号密码查询出的用户信息
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String account = authentication.getName();
        System.out.println("执行到authenticate");
        System.out.println(account);
        String password = authentication.getCredentials().toString();
        System.out.println("mima"+password);
        UserDetails userDetails;
        Map<String, Object> redis;
        System.out.println("1进入登录");
        if (account.equals("weixin_login_function")) {
            System.out.println("2进入登录");
            System.out.println(redisService.get(password));
            Map<String,Object> map= new HashMap<>();
            map.put("stu",redisService.get(password));
            //1、使用JSONObject
          JSONObject jsonObject=new JSONObject(map);
            System.out.println(jsonObject);
            String user = jsonObject.getString("stu");
            JSONObject oneuser= JSON.parseObject(user);
            System.out.println(oneuser);
//            {"phone":"15651790519","openid":"oMTtI43dh0eyE9Av4wOpvmkoWt6A","sex":"男","id":13,"userName":"zhangjun","age":21}
//
            //User userInfo=(User) redisService.get(password);
          //  String userName= redisService.get(password).getString("userName");
            //System.out.println(userInfo);
           // String UUID = (String) redisService.get("password");
           // redis = (Map<String, Object>) redisService.get(password);//存放个人登录信息查询结果，测试时暂时注释掉
           // System.out.println(redis);
           // System.out.println((String) redis.get("openid"));
            // 获取封装用户信息的对象
           // userDetails = userDetailsService.loadUserByUsername((String) redis.get("openid"));
            userDetails = userDetailsService.loadUserByUsername(oneuser.getString("userName"));
            // 未查询到用户
            if (userDetails == null) {
                throw new UsernameNotFoundException("user:" + account + " is null");
            }
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            // 获取封装用户信息的对象
            userDetails = userDetailsService.loadUserByUsername(account);
            // 未查询到用户
            if (userDetails == null) {
                throw new UsernameNotFoundException("user:" + account + " is null");
            }

//            System.out.println("password:    " + password);
//            System.out.println("userDetails.getPassword():    " + userDetails.getPassword());
//            System.out.println("userDetails:    " + userDetails);
            // 进行密码的比对
            boolean isPasswordMatch = bCryptPasswordEncoder().matches(password, userDetails.getPassword());
            if (isPasswordMatch) {
                // 将权限信息也封装到Authentication中
                return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            } else {
                // 密码不匹配
                throw new BadCredentialsException("user:" + account + " password is not match");
            }
        }
//		// 获取封装用户信息的对象
//		userDetails = userDetailsService.loadUserByUsername(account);

//		// 未查询到用户
//		if (userDetails == null) {
//			throw new UsernameNotFoundException("user:" + account + " is null");
//		}
//
//		System.out.println("password:    "+password);
//		System.out.println("userDetails.getPassword():    "+userDetails.getPassword());
//		System.out.println("userDetails:    "+userDetails);
//		// 进行密码的比对
//		boolean isPasswordMatch = bCryptPasswordEncoder().matches(password, userDetails.getPassword());
//		if (isPasswordMatch) {
//			// 将权限信息也封装到Authentication中
//			return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//		} else {
//			// 密码不匹配
//			throw new BadCredentialsException("user:" + account + " password is not match");
//		}
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
