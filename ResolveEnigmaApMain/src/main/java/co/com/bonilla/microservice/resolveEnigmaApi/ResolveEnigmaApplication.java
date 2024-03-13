package co.com.bonilla.microservice.resolveEnigmaApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "co.com.bonilla.microservice.resolveEnigmaApi", "co.com.bonilla.microservice.resolveEnigmaApi.api" , "co.com.bonilla.microservice.resolveEnigmaApi.config"})
public class ResolveEnigmaApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplication(ResolveEnigmaApplication.class).run(args);
    }

}
