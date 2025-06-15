package org.jit.sose.web.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

	@Value("${swagger.enable}")
	private Boolean SWAGGER_ENABLE;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)// 去掉swagger默认的状态码
				.enable(SWAGGER_ENABLE) // 是否开启swagger
				.apiInfo(apiInfo()).groupName("mall-doc").select()
//                .apis(RequestHandlerSelectors.any())
//				.apis(RequestHandlerSelectors.basePackage("org.jit.sose"))// 扫描注解的配置，即你的API接口位置
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // 只扫描添加了注解的类
				.paths(PathSelectors.regex("^(?!login).*$")).build()
				// 配置security
				.securitySchemes(securitySchemes()).securityContexts(securityContexts());
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Mall swagger-api with spring-security").description("整合security的商城在线文档")
				.contact("jitsose").termsOfServiceUrl("http://127.0.0.1:8000").version("1.0.0")
//				.license("这是一个证书")
				.build();
	}

	private List<ApiKey> securitySchemes() {
		return newArrayList(new ApiKey("BearerToken", "Authorization", "header"));
	}

	private List<SecurityContext> securityContexts() {
		return newArrayList(SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("^(?!auth).*$")).build());
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(new SecurityReference("Authorization", authorizationScopes));
	}

}
