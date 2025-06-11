package org.jit.sose.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxMaConfiguration {
    private static String appId="wx06330c018ec1e8bf";

    private static String secret="f63ddc6633c414e9650cbedb14a30e91";

//    @Value("${weixin.applet_appid}")
//    public void setAppId(String appId) {
//        WxMaConfiguration.appId = appId;
//    }
//
//    @Value("${weixin.applet_secret}")
//    public void setSecret(String secret) {
//        WxMaConfiguration.secret = secret;
//    }

    private static WxMaService wxMaService=null;

    @Bean
    public Object services(){
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(appId);
        config.setSecret(secret);
        wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);
        return Boolean.TRUE;
    }
    public static WxMaService getWxMaService(){
        return wxMaService;
    }
}
