package org.jit.sose.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 导入druid数据源
 *
 * @author wangyue
 * @date 2019/7/12 17:48
 */
@Configuration
public class DruidConfig {

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druid() {
		// 创建一个数据源
		return new DruidDataSource();
	}

	/**
	 * 配置Druid的监控 1、配置一个管理后台
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> statViewServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<StatViewServlet>(
				new StatViewServlet(), "/druid/*");
		Map<String, String> initParams = new HashMap<>();
		// 设置后台访问用户名密码
		initParams.put("loginUsername", "druid");
		initParams.put("loginPassword", "123456");
		// 允许的访问
		initParams.put("allow", "");// 默认就是允许所有访问
		// 禁止的访问
//        initParams.put("deny","192.168.15.21");

		bean.setInitParameters(initParams);
		return bean;
	}

	/**
	 * 2、配置监控的filter
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<WebStatFilter> webstatFilter() {
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>();
		bean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<>();
		// 设置初始化参数，不拦截的请求或资源
		initParams.put("exclusions", "*.js,*.css,/druid/*");
		bean.setInitParameters(initParams);
		// 设置拦截的请求
		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
	}

}
