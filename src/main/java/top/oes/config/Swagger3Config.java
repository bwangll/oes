package top.oes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author bwang
 * @version 1.0.0
 * @Description Swagger3Config
 * @CreateTime 2021/9/12 7:01 下午
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {

    @Value("${springfox.documentation.swagger-ui.enabled}")
    private boolean enabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(
            // 设置使用 OpenApi 3.0 规范
            DocumentationType.OAS_30)
            // 是否开启 Swagger
            .enable(enabled)
            // 配置项目基本信息
            .apiInfo(apiInfo())
            // 设置项目组名
            //.groupName("xxx组")
            // 选择那些路径和api会生成document
            .select()
            // 对所有api进行监控
            .apis(RequestHandlerSelectors.any())
            // 如果需要指定对某个包的接口进行监控，则可以配置如下
            //.apis(RequestHandlerSelectors.basePackage("mydlq.swagger.example.controller"))
            // 对所有路径进行监控
            .paths(PathSelectors.any())
            // 忽略以"/error"开头的路径,可以防止显示如404错误接口
            .paths(PathSelectors.regex("/error.*").negate())
            // 忽略以"/actuator"开头的路径
            .paths(PathSelectors.regex("/actuator.*").negate())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            // 文档标题
            .title("学生在线考试系统")
            // 文档描述
            .description("学生在线考试系统后台接口说明文档")
            // 文档版本
            .version("0.0.1")
            .contact(new Contact("bwang", "https://bwang.top", "1415086046@qq.com"))
            .build();
    }

}
