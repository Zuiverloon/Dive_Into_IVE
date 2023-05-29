package org.zjy.diveintoive.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zjy.diveintoive.Service.CatcherService;

@Configuration
public class ResourceMappingConfig implements WebMvcConfigurer{

    @Autowired
    CatcherService catcherService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/ive/**").addResourceLocations("file:"+ catcherService.getPath());
    }
}
