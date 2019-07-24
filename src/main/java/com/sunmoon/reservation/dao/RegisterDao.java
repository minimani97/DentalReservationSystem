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

import com.sunmoon.reservation.model.ClinicMapInfo;
import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.PatientInfo;

@Repository
public class RegisterDao {
	private static final Logger logger = LoggerFactory.getLogger(RegisterDao.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	// 병원 목록 가져오기
	public List<ClinicMapInfo> getClinicList() {
		
		String sqlStatement = "select c_code, c_name from clinic where certify='Y'";
		List<ClinicMapInfo> results = jdbcTemplate.query(sqlStatement, new RowMapper<ClinicMapInfo> () {
			
			@Override
			public ClinicMapInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				ClinicMapInfo info = new ClinicMapInfo();
				
				info.setC_code(rs.getString("c_code"));
				info.setC_name(rs.getString("c_name"));
				
				return info;
			}
		});
		
		return results;
				
	}
	
	// 중복된 아이디인지 검사
	public int isDuplicateId(String inputIdValue) {
		
		String sqlStatement = "select count(*) from login where id='"+inputIdValue+"'";
		int result = jdbcTemplate.queryForObject(sqlStatement, Integer.class);
		
		return result;
	}
	
	// 환자 회원가입
	public void patientUserRegister(PatientInfo info) {
		
		String sqlStatement1 = "insert into login (id, password, kindof) values (?,?,'P')";
		jdbcTemplate.update(sqlStatement1, info.getId(), info.getPassword());
		
		String sqlStatement2 = "insert into patient (id, p_name, birth, addr, p_tel, isLeader) values (?,?,?,?,?,'Y')";
		jdbcTemplate.update(sqlStatement2, info.getId(), info.getName(), info.getBirth(), info.getAddr(), info.getTel());
		
	}
	
	// 의료진 회원가입
	public void dentistUserRegister(DentistInfo info) {
		
		String sqlStatement1 = "insert into login (id, password, kindof) values (?,?,'D')";
		jdbcTemplate.update(sqlStatement1, info.getId(), info.getPassword());
		
		if(info.getProfile() == "") {
			String sqlStatement2 = "insert into dentist (c_code, d_name, d_tel, email, edu, career, id) values (?,?,?,?,?,?,?)";
			jdbcTemplate.update(sqlStatement2, info.getC_code(), info.getD_name(), info.getD_tel(), info.getEmail(), info.getEdu(), info.getCareer(), info.getId());
		} else {
			String sqlStatement2 = "insert into dentist (c_code, profile, d_name, d_tel, email, edu, career, id) values (?,?,?,?,?,?,?,?)";
			jdbcTemplate.update(sqlStatement2, info.getC_code(), info.getProfile(), info.getD_name(), info.getD_tel(), info.getEmail(), info.getEdu(), info.getCareer(), info.getId());
		}
		
		String getD_code = "select d_code from dentist where d_tel='"+info.getD_tel()+"'";
		int d_code = jdbcTemplate.queryForObject(getD_code, Integer.class);
		
		String fields = info.getF_codes();
		
		for(int i=0; i<fields.split(",").length; i++) {
			String field_code = fields.split(",")[i];
			
			String sqlStatement3 = "insert into dentist_field (c_code, d_code, d_field) values (?,?,?)";
			jdbcTemplate.update(sqlStatement3, info.getC_code(), d_code, field_code);
		}
	}
}
