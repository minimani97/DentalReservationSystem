package com.sunmoon.reservation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sunmoon.reservation.model.AlreadyReservedTimeInfo;
import com.sunmoon.reservation.model.DentistInfo;

@Repository
public class ScheduleDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	// 같은 병원 의료진 리스트 가져오기
	public List<DentistInfo> loadDentistList(int c_code) {
		String sqlStatement = "select d_code, d_name from dentist where dentist.c_code=" + c_code;
		
		List<DentistInfo> results = jdbcTemplate.query(sqlStatement, new RowMapper<DentistInfo>() {
			
			@Override
			public DentistInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				DentistInfo info = new DentistInfo();
				
				info.setD_code(rs.getInt("d_code"));
				info.setD_name(rs.getString("d_name"));
				
				return info;
			}
		});
		return results;
	}
	
	// 스케줄 불러오기
	public List<AlreadyReservedTimeInfo> getMySchedule(String date, int d_code) {
		String sqlStatement = "select s_time, e_time, p_name from reservation where reservation.date='"+date+"' and reservation.d_code="+d_code;
		
		List<AlreadyReservedTimeInfo> results = jdbcTemplate.query(sqlStatement, new RowMapper<AlreadyReservedTimeInfo>() {
			
			@Override
			public AlreadyReservedTimeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AlreadyReservedTimeInfo info = new AlreadyReservedTimeInfo();
				
				info.setStartTime(rs.getString("s_time"));
				info.setEndTime(rs.getString("e_time"));
				info.setPatient_name(rs.getString("p_name"));
				
				return info;
			}
		});
		
		return results;
	}
}
