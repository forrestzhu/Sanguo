package com.jayzero.games.sanguo.rest;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * SwaggerConfiguration
 * <p>
 * please refer to http://localhost:8080/swagger-ui/index.html for swagger page
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
@EnableOpenApi
@Configuration
@Slf4j
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Value("${swagger.enabled:true}")
    private Boolean swaggerEnabled;

    @Value("${swagger.application.name}")
    private String applicationName;

    @Value("${swagger.application.description}")
    private String applicationDescription;

    @Value("${swagger.application.version}")
    private String applicationVersion;

    @Value("${swagger.try-host}")
    private String tryHost;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")

            // 定义是否开启swagger，false为关闭，可以通过变量控制
            .enable(swaggerEnabled)

            // 将api的元信息设置为包含在json ResourceListing响应中。
            .apiInfo(apiInfo())

            // 接口调试地址
            .host(tryHost)

            // 选择哪些接口作为swagger的doc发布
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()

            // 支持的通讯协议集合
            .protocols(newHashSet("https", "http"))

            // 授权信息设置，必要的header token等认证信息
            .securitySchemes(securitySchemes())

            // 授权信息全局应用
            .securityContexts(securityContexts());
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(applicationName + " Api Doc")
            .description(applicationDescription)
            .contact(new Contact("Forrest Zhu", null, "jayzero@foxmail.com"))
            .version("Application Version: " + applicationVersion + ", Spring Boot Version: " + SpringBootVersion.getVersion())
            .build();
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
            SecurityContext.builder()
                .securityReferences(Collections.singletonList(
                    SecurityReference.builder()
                        .reference("BASE_TOKEN")
                        .scopes(new AuthorizationScope[]{new AuthorizationScope("global", "")})
                        .build()))
                .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
            addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
            .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
            .setViewName("forward:/swagger-ui/index.html");
    }
}
