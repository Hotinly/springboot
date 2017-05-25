package com.boot.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StaticResConfig extends WebMvcConfigurerAdapter{

//	编码方式实现 (推荐使用配置方式实现)
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
//		super.addResourceHandlers(registry);
//	}
	
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.setUseSuffixPatternMatch(false).  //"/users" == "/users.*"
//                setUseTrailingSlashMatch(true);  //"/users" == "/users/"
//    }
}