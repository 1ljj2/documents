package org.jit.sose.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WeiXinConfig {

	/**
	 * 小程序appid
	 */
	public static String APPID= "wx06330c018ec1e8bf";
//	public static String APPID;

	/**
	 * 小程序app_secret
	 */
	public static String SECRET="f63ddc6633c414e9650cbedb14a30e91";
//	public static String SECRET;

	/**
	 * 资源文件路径
	 */
	private static String FILE_PROPERTIES = "resource/weiXin.properties";

//	public WeiXinConfig() {
//		Properties prop = PropertiesUtil.getProperties(FILE_PROPERTIES);
//		WeiXinConfig.APPID = prop.getProperty("APPID");
//		WeiXinConfig.SECRET = prop.getProperty("SECRET");
//		System.out.println(WeiXinConfig.APPID + "____" + WeiXinConfig.SECRET);
//	}

}
