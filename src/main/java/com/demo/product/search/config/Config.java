package com.demo.product.search.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.demo.product.search.repository")
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class Config {

}
