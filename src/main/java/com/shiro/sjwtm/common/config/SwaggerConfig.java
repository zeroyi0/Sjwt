package com.shiro.sjwtm.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shiro.sjwtm.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security())
                .globalOperationParameters(Arrays.asList(parameterBuilder()));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("shiro整合Jwt简单使用")
                .version("1.2.0")
                .build();
    }
    private List<ApiKey> security() {
        return newArrayList(
                new ApiKey("token", "token", "header")
        );
    }

    private Parameter parameterBuilder() {
        return new ParameterBuilder().parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("token") //参数名
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置

    }

}
