package com.afigueredo.datascraping.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}")
                                         String appDescription,
                                 @Value("${application-version}")
                                         String appVersion,
                                 @Value("${application-title}")
                                             String title,
                                 @Value("${application-contact-name}")
                                             String contactName,
                                 @Value("${application-contact-github}")
                                             String contactGithub,
                                 @Value("${application-contact-email}")
                                             String contactEmail
    ) {
        Contact contact = new Contact();
        contact.setUrl(contactGithub);
        contact.setName(contactName);
        contact.setEmail(contactEmail);

        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(appVersion)
                        .contact(contact)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().
                                name("Apache 2.0").
                                url("http://springdoc.org")));
    }

}
