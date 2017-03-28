package com.theironyard.novauc;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(metaData())
                .pathMapping("/");
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "SSA's People Group",
                "RESTful API built for class assignment",
                "4.2",
                "use at your own risk",
                new Contact("Will C", "https://github.com/wcriss44", "wcriss44@gmail.com"),
                "Does anyone read this?",
                "https://www.google.com/imgres?imgurl=http://www.gstatic.com/tv/thumb/movieposters/8325380/p8325380_p_v8_ah.jpg&imgrefurl=http://google.com/search%3Ftbm%3Disch%26q%3DRabbit%2520Hole&h=1440&w=960&tbnid=J5ACrCQQXrJenM:&tbnh=160&tbnw=106&usg=__rt_KvHIz0dcjbmNep-ljX_lm7y8=&vet=10ahUKEwi5mJy6uvjSAhXD54MKHX76CMsQ_B0IczAK..i&docid=NmLPT9bE3PfyHM&itg=1&client=safari&sa=X&ved=0ahUKEwi5mJy6uvjSAhXD54MKHX76CMsQ_B0IczAK#h=1440&imgrc=J5ACrCQQXrJenM:&tbnh=160&tbnw=106&vet=10ahUKEwi5mJy6uvjSAhXD54MKHX76CMsQ_B0IczAK..i&w=960");
        return apiInfo;
    }
}
