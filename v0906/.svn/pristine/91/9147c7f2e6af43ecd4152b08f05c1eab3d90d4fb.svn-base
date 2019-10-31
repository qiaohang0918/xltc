package com.qigan.qiganshop.util.swagger;


import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.qigan.qiganshop.controller"})
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {

    Predicate<RequestHandler> predicate;

    @Bean
    public Docket frontendApi() {
        //apis : 指定扫描的包的路径
        return new Docket(DocumentationType.SWAGGER_2).groupName("APP 端接口调用  点击切换")
                .apiInfo(frontendApiInfo())
                .select()
                .paths(PathSelectors.none())
                .apis(RequestHandlerSelectors.basePackage(
                        "com.qigan.qiganshop.controller.frontend"))
                //.frontend
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket backstageApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {

            @Override
            public boolean apply(RequestHandler input) {
//                Class<?> declaringClass = input.declaringClass();
//                if (declaringClass == BasicErrorController.class)// 排除
//                    return false;
//                if(declaringClass.isAnnotationPresent(ApiOperation.class)) // 被注解的类
//                    return true;
//                if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
//                    return true;
                if (input.isAnnotatedWith(ApiOperation.class)) {
                    //只有添加了ApiOperation注解的method才在API中显示

                    return true;
                }
                return false;
            }
        };


        //apis : 指定扫描的包的路径
        return new Docket(DocumentationType.SWAGGER_2).groupName("WEB 端接口调用  点击切换")
                .apiInfo(backstageApiInfo())
                .select()
                .paths(PathSelectors.none())
                .apis(RequestHandlerSelectors.basePackage(
                        "com.qigan.qiganshop.controller.backstage"))
                //.frontend
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket payApi() {
        //apis : 指定扫描的包的路径
        return new Docket(DocumentationType.SWAGGER_2).groupName(" 支付接口调用  点击切换")
                .apiInfo(payApiInfo())
                .select()
                .paths(PathSelectors.none())
                .apis(RequestHandlerSelectors.basePackage(
                        "com.qigan.qiganshop.controller.utils"))
                //.frontend
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo frontendApiInfo() {
        return new ApiInfoBuilder()
                .title("邻里云商 App RestFul Api")
                .description("SSM 框架开发")
                .termsOfServiceUrl("http://www.xiletongcheng.com")
                .contact(new Contact("Double King", "http://www.xiletongcheng.com", ""))
                .version("1.0")
                .build()
                ;
    }

    private ApiInfo backstageApiInfo() {
        return new ApiInfoBuilder()
                .title("邻里云商 后台 RestFul Api")
                .description("SSM 框架开发")
                .termsOfServiceUrl("http://www.xiletongcheng.com")
                .contact(new Contact("Double King", "http://www.xiletongcheng.com", ""))
                .version("1.0")
                .build()
                ;
    }
    private ApiInfo payApiInfo() {
        return new ApiInfoBuilder()
                .title("邻里云商 后台 RestFul Api")
                .description("SSM 框架开发")
                .termsOfServiceUrl("http://www.xiletongcheng.com")
                .contact(new Contact("Double King", "http://www.xiletongcheng.com", ""))
                .version("1.0")
                .build()
                ;
    }

}