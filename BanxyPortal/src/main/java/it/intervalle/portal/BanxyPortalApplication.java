package it.intervalle.portal;


 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import it.intervalle.portal.configuration.FileStorageProperties;
 



@SpringBootApplication
@OpenAPIDefinition(info= @Info(title = "appp",version = "2"))
@SecurityScheme(
        name = "basicAuth", // can be set to anything
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class BanxyPortalApplication {


	public static void main(String[] args) {
		SpringApplication.run(BanxyPortalApplication.class, args);
	}

}
