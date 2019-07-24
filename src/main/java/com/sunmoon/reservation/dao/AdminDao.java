package com.sunmoon.reservation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sunmoon.reservation.model.AdminInfo;

@Repository
public class AdminDao {
	private static final Logger logger = LoggerFactory.getLogger(ClinicInfoDao.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public List<AdminInfo> dentistInfoDao() {
		String sql = "select d_name, c_name, d_tel, email from dentist, clinic where dentist.c_code=clinic.c_code;";

		return jdbcTemplate.query(sql, new RowMapper<AdminInfo>() {

			@Override
			public AdminInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdminInfo info = new AdminInfo();

				info.setD_name(rs.getString("d_name"));
				info.setC_name(rs.getString("c_name"));
				info.setD_tel(rs.getString("d_tel"));
				info.setEmail(rs.getString("email"));
				

				return info;
			}

		});
	}

	public List<AdminInfo> PatientInfoDao() {
		String sql = "select id, p_name, birth, p_tel, addr from patient;";
		logger.info(sql);
		return jdbcTemplate.query(sql, new RowMapper<AdminInfo>() {

			@Override
			public AdminInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdminInfo info = new AdminInfo();

				info.setP_id(rs.getString("id"));
				info.setP_name(rs.getString("p_name"));
				info.setP_birth(rs.getString("birth"));
				info.setP_tel(rs.getString("p_tel"));
				info.setP_addr(rs.getString("addr"));
				
				logger.info("addr: "+rs.getString("addr"));

				return info;
			}

		});
	}

	public List<AdminInfo> ClinicInfoDao() {
		String sql = "select c_name, c_tel, c_address, certify from clinic;";

		return jdbcTemplate.query(sql, new RowMapper<AdminInfo>() {

			@Override
			public AdminInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdminInfo info = new AdminInfo();
				
				info.setC_name(rs.getString("c_name"));
				info.setC_tel(rs.getString("c_tel"));
				info.setC_address(rs.getString("c_address"));
				info.setCertify(rs.getString("certify"));
			
				return info;
			}

		});
	}

	
	
	public void CertifyDao(String c_name) {
		
		String sql = "update clinic set certify = 'Y' where c_name ='"+c_name+"';";
		
		jdbcTemplate.update(sql);

		
	}
}
