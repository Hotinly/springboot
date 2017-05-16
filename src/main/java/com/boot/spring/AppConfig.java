package com.boot.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// @PropertySources({@PropertySource("classpath:config/data.properties"),
//@PropertySource(value = { "classpath:config/jdbcs.properties", "classpath:config/data.properties" }, ignoreResourceNotFound = true)
@PropertySource({"classpath:config/jdbc.properties","classpath:config/data.properties"})
public class AppConfig {
/**
 * Properties文件中若存在多个相同的key，则最后一个起作用。
 * 
 * refer to: http://www.tuicool.com/articles/nimEJjA
 */
}
