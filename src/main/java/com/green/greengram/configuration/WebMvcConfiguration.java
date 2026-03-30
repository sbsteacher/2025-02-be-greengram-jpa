package com.green.greengram.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration //빈등록
public class WebMvcConfiguration implements WebMvcConfigurer {
    public final String fileUploadPath;

    public WebMvcConfiguration(@Value("${constants.file.directory}") String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:" + fileUploadPath);

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource resource = location.createRelative(resourcePath);

                        if(resource.exists() && resource.isReadable()) {
                            return resource;
                        }

                        return new ClassPathResource("/static/index.html");
                    }
                });
    }

}
