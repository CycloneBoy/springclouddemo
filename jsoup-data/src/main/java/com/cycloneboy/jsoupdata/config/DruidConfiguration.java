package com.cycloneboy.shiroadmin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @项目名称：
 * @类名称：DruidConfiguration
 * @类描述：Druid监控servelet和filter配置
 * @创建人：
 * @创建时间：
 * @version：
 */
@Configuration
public class DruidConfiguration {

	//把DruidDataSource添加到ioc容器中，并且prefix = "spring.datasource"，让application.yml里的数据源其他配置属性生效
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druid() {
		return new DruidDataSource();
	}

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("loginUsername", "admin");// 用户名
		initParameters.put("loginPassword", "123456");// 密码
		initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
		initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
		// initParameters.put("deny", "192.168.20.38");// IP黑名单
		// (存在共同时，deny优先于allow)
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		// @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//
		// 忽略资源
		filterRegistrationBean.addInitParameter("exclusions",
				"*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

}