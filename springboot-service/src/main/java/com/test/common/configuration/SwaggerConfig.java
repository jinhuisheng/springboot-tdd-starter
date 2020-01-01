package com.test.common.configuration;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huisheng.jin
 * @version 2019/4/15.
 */
@Configuration
@EnableSwagger2
@Profile("!prod")
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        List<Parameter> pars = createHeaderParam();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                //************把消息头添加
                .globalOperationParameters(pars);
    }

    private List<Parameter> createHeaderParam() {
        //在配置好的配置类中增加此段代码即可
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //name表示名称，description表示描述
        ticketPar.name("Authorization").description("用户身份")
                .modelRef(new ModelRef("string")).parameterType("header")
                //required表示是否必填，defaultvalue表示默认值
                .required(false)
                .build();
        //添加完此处一定要把下边的带***的也加上否则不生效
        pars.add(ticketPar.build());
        return pars;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("收银台服务端-API")
                .description("收银台服务端-API")
                .description("使用问题请联系	jinhuisheng@wowoshijie.com")
                .termsOfServiceUrl("")
                .build();
    }
}
