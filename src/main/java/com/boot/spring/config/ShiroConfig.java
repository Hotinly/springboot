package com.boot.spring.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.boot.spring.service.MyShiroRealm;

/**
 * 注入 ShiroFilterFactoryBean 和 SecurityManager
 * 
 * @author Holy
 * @date 2017年6月1日 上午11:11:03
 * @ref
 */
@Configuration
public class ShiroConfig {

	@Bean
	// @Autowired private SecurityManager securityManager; public
	// ShiroFilterFactoryBean shiroFilter(){
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		System.out.println(this.getClass().getName());

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager); // !必须设置
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		/*
		 * authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问; user:配置记住我或认证通过可以访问 ssl,
		 * roles, rest, port, perms, authcBasic
		 */
		// 过滤链定义，从上向下顺序执行，一般将 /**放在最下边
		filterChainDefinitionMap.put("/reg", "anon");  //anon 可以理解为不拦截
		filterChainDefinitionMap.put("/about", "anon");
		//在该url上加访问权限控制，对于没权限的用户，会跳转到403,说明它确实走了Unauthorized，但是直接在controller上的注解没搞定
		filterChainDefinitionMap.put("/userAdd", "authc, roles[admin]");
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	// Shiro的核心安全接口！
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());  //设置Realm(认证、授权)
		securityManager.setCacheManager(ehCacheManager());  //设置缓存管理器
		return securityManager;
	}

	// 认证、授权的内部实现
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		// myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());  //注入凭证匹配器  使用CredentialsMatcher加密算法来验证密文
		// myShiroRealm.setCacheManager(ehCacheManager());  //设置缓存管理器
		
		myShiroRealm.setCachingEnabled(true);  //启用缓存
		myShiroRealm.setAuthenticationCachingEnabled(true);  //启用身份验证缓存，即缓存用户信息
		myShiroRealm.setAuthenticationCacheName("authenticationCache");  //对应 xml文件中的 name
		myShiroRealm.setAuthorizationCachingEnabled(true);  //启用授权缓存，即缓存授权信息
		myShiroRealm.setAuthorizationCacheName("authorizationCache");
		
		return myShiroRealm;
	}

	// 凭证匹配器
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// hashedCredentialsMatcher.setHashAlgorithmName("md5"); //加密算法
		// hashedCredentialsMatcher.setHashIterations(2); //加密次数
		return hashedCredentialsMatcher;
	}

	// 开启shiro注解.使用代理方式;所以需要开启代码支持 需借助SpringAOP扫描使用Shiro注解的类 ?
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	// 缓存管理器，使用Ehcache实现。不加缓存的话，访问相应的权限页面都会重新走授权:doGetAuthorizationInfo(principals);
	@Bean
	public EhCacheManager ehCacheManager(){
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return ehCacheManager;
	}

	// 定制错误处理
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/403"));
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
				container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
			}
		};
	}


}
