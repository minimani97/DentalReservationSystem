package com.sunmoon.reservation.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClinicInfoDao {
	private static final Logger logger = LoggerFactory.getLogger(ClinicInfoDao.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public void setInfoDao(String c_name, String c_tel, String addr_y, String addr_x, String c_license,
			String c_address) {

		logger.info("전!!화!!번!!!호!!!: " + c_tel);
		String sql = "insert into clinic (c_name, c_tel, addr_y, addr_x,  c_license, c_address) values (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, c_name, c_tel, addr_y, addr_x, c_license, c_address);
		
	}

}
