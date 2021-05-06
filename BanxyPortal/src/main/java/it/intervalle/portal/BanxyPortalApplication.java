package it.intervalle.portal;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

 
import it.intervalle.portal.configuration.FileStorageProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class BanxyPortalApplication {


	public static void main(String[] args) {
		SpringApplication.run(BanxyPortalApplication.class, args);
	}

}
