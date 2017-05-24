package com.boot.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Holy
 * @date 2017年5月24日 下午2:37:12
 * @ref http://blog.didispace.com/springbootswagger2/
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket all() {
		return createDocket("*", "/.*");
	}

	@Bean
	public Docket module1() {
		return createDocket("swg");
	}

	private Docket createDocket(String groupName){
		return createDocket(groupName, "/"+groupName+"/.*");
	}
	private Docket createDocket(String groupName, String pathRegex){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName(groupName)
				.forCodeGeneration(true)
//				.genericModelSubstitutes(DeferredResult.class)
//				.genericModelSubstitutes(ResponseEntity.class)
//				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.regex(pathRegex))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("RESTful APIs")
				.description("Base on spring boot and swagger2")
//				.termsOfServiceUrl("http://blog.didispace.com/springbootswagger2/")
				.contact(new Contact("Holy", "", "hotinly@163.com"))
				.version("1.0")
				.build();
	}
}
