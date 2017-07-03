package com.tingyun.config;

import com.tingyun.domain.AppleDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tingyun on 2017/6/13.
 */
@Configuration
public class BeanConfiguation {

    @Bean
    public AppleDomain getApple(){
        AppleDomain apple=new AppleDomain();
        return apple;
    }
}
