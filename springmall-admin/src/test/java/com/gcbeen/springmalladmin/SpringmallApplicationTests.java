package com.gcbeen.springmalladmin;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcbeen.springmalladmin.dao.PmsProductDao;
import com.gcbeen.springmalladmin.service.IPmsProductService;
import com.gcbeen.springmallgenerator.entity.PmsProduct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class SpringmallApplicationTests {

	@Autowired
	private PmsProductDao productDao;

	@Autowired
	private IPmsProductService productService;

	// 数据源
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void testSelect() {
		System.out.println(("----- productMapper selectAll method test ------"));
		List<PmsProduct> productList = productDao.selectList(null);
		Assertions.assertEquals(29, productList.size());
		Page<PmsProduct> page = new Page<>(1, 3);
		productDao.pageList(page, 1);
		productList.forEach(System.out::println);
	}

	@Test
	public void testService() {
		System.out.println(("----- productService list method test ------"));
		List<PmsProduct> productList = productService.list();
		Assertions.assertEquals(29, productList.size());
		productList.forEach(System.out::println);
	}

	@Test
	void contextLoads() throws SQLException {
		System.out.println("数据源：" + dataSource.getClass());
		System.out.println("数据库连接实例：" + dataSource.getConnection());
		// 访问数据库
		Integer i = jdbcTemplate.queryForObject("SELECT count(id) FROM `pms_product`", Integer.class);
		System.out.println(" pms_product 表中共有" + i + "条数据。");
	}

}
