package com.example.demo.common.configuration;



import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Customer;

//NoSpringIA
@Configuration
public class RepositoryRestConfigurerConf implements RepositoryRestConfigurer {

    
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Customer.class,Burger.class);
    }
}
