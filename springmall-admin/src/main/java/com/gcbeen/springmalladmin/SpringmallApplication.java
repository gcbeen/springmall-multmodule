package com.gcbeen.springmalladmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
// @EnableWebMvc
// @MapperScan("com.gcbeen.springmall.nosql.elasticsearch.repository")
// @EnableElasticsearchRepositories(basePackages = {"com.gcbeen.springmall.nosql.elasticsearch.repository"})
public class SpringmallApplication {

	// cms_*：内容管理模块相关表
	// oms_*：订单管理模块相关表
	// pms_*：商品模块相关表
	// sms_*：营销模块相关表
	// ums_*：会员模块相关表
	public static void main(String[] args) {
		SpringApplication.run(SpringmallApplication.class, args);
	}

}
