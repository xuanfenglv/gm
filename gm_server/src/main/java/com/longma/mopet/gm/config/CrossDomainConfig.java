package com.longma.mopet.gm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author:Lvxingqing
 * @Description: ajax 跨域配置类（这个时不时的不好使，呵呵。主要靠KeepSessionFilter实现跨域）
 * @Date:Create in 13:07 2018/4/23
 * @Modified By:
 */
@Configuration
public class CrossDomainConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:8020") //受信任的源
                .allowedMethods("GET", "POST")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
