package top.wayne06.generalbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * global cross-domain configuration
 *
 * @author https://github.com/wayne06
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Enable cross-origin request handling for the specified path pattern.
        registry.addMapping("/**")
                // Allow send Cookie
                .allowCredentials(true)
                // Set the origins for which cross-origin requests are allowed from a browser.（must use patterns，otherwise * and allowCredentials may arise conflicts）
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
