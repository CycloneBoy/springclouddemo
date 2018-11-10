package com.cycloneboy.shiromybatis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-11-10 01:37
 **/

@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "shiro", name = "swagger-open", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //这里采用包含注解的方式来确定要显示的接口
                .apis(RequestHandlerSelectors.basePackage("com.cycloneboy.shiromybatis.controller"))
                // 这里采用包扫描的方式来确定要显示的接口
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("shiro-mybatis")
                .description("Spring Boot中使用Swagger2构建RESTful APIs")
                .termsOfServiceUrl("https://cycloneboy.com")
                .contact(new Contact("CycloneBoy","https://cycloneboy.com","xxx@gmail.com"))
                .version("1.0")
                .build();
    }

}

